package com.gyh.controller;

import com.gyh.aspect.OpeLogInfo;
import com.gyh.bean.TUser;
import com.gyh.common.constant.CommonConstant;
import com.gyh.common.inteceptor.SkipLoginCheck;
import com.gyh.common.pojo.MessageResult;
import com.gyh.common.utils.CookiesUtils;
import com.gyh.common.utils.LoginUtils;
import com.gyh.service.UserService;
import com.gyh.utils.IDGenerator;
import com.gyh.utils.encrypt.MD5Util;
import com.gyh.utils.json.JacksonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loginController")
public class LoginController {
	private Logger log=Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;


	@Autowired(required = false)
	private RedisTemplate<String,String> redisSingle;


	@Autowired(required = false)
	private JedisCluster redisCluster;

	/*@Value("#{prop['jdbc.driver']}")
	private String driver;*/

	/**
	 * 获取登录盐值
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loginSalt" ,method = RequestMethod.GET)
	public MessageResult getLoginSalt(String userName){
		MessageResult result = new MessageResult();
		String redisKey = CommonConstant.reidsUserSalt + LoginUtils.getIpAddress() + CommonConstant.underline + userName;
		//若已存在盐值，说明同一个ip多次重复获取，禁止多次重复获取
		String salt_ = redisCluster.get(redisKey);
		if(StringUtils.isNotBlank(salt_)){
			result.setSuccess(false);
			result.setMsg("禁止重复获取");
			return result;
		}
		String salt = IDGenerator.getUUID();
		//有效期三分钟
		redisCluster.setex(redisKey,180,salt);

		result.setSuccess(true);
		result.setMsg("获取成功");
		result.setData(salt);
		return  result;
	}

	/**
	 * 用户登录
	 * @param request
	 * @param userName 用户名
	 * @param password 用户密码 = （明文密码Md5后+ 动态盐值）再次Md5
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping(value = "/dologin",method = RequestMethod.POST)
	@OpeLogInfo(node = "用户登录")
	public MessageResult dologin(HttpServletRequest request, HttpServletResponse response, String userName, String password){
		MessageResult result = new MessageResult();
		result.setSuccess(false);
		if(StringUtils.isEmpty(userName)){
			result.setSuccess(false);
			result.setMsg("请输入用户名");
			return result;
		}
		if(StringUtils.isEmpty(password)){
			result.setSuccess(false);
			result.setMsg("请输入密码");
			return result;
		}
		TUser user;
		try {
			user = userService.queryUserByUserName(userName);
			if(user!=null){
				if(user.getPassword().equals(MD5Util.string2MD5(password))){
					String loginUserID = CommonConstant.reidsLoginUserId + user.getId();
					String userToken= IDGenerator.getUUID();
					//如果已经登录，删除旧的登录信息，实现只可以一处登录
					String oldToken = redisCluster.get(loginUserID);
					if(StringUtils.isNotBlank(oldToken)){
						redisCluster.del(loginUserID);
						redisCluster.del(oldToken);
					}
					/*redisSingle.opsForValue().set(userToken, JacksonUtils.toJson(user),1200, TimeUnit.SECONDS);
					redisSingle.opsForValue().set(loginUserID, userToken,1200, TimeUnit.SECONDS);*/
					redisCluster.setex(userToken,1200, JacksonUtils.toJson(user));
					redisCluster.setex(loginUserID,1200,userToken);
					//记录在线用户
					redisCluster.rpush(CommonConstant.OnlineUsers,loginUserID);
					request.getSession().setAttribute(CommonConstant.loginUserToken,user);
					CookiesUtils.makeCookieLife(request,response,CommonConstant.loginUserToken,userToken,"/",1200);
					result.setData(userToken);
					result.setMsg("success");
					result.setOther(user);
					result.setSuccess(true);
				}else{
					result.setMsg("密码错误");
				}
				
			}else{
				result.setMsg("用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}	
		return result;
	}

	/**
	 * 用户退出登录，删除redis用户信息，从在线用户中移除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logout",method = RequestMethod.POST)
	@OpeLogInfo(node = "用户退出登录")
	public MessageResult logout(){
		MessageResult result = new MessageResult();
		String cookieToken = LoginUtils.getUserCookieToken();
		if(StringUtils.isNotBlank(cookieToken)){
			//删除登录用户信息
			redisCluster.del(cookieToken);
			//从在线列表删除用户
			redisCluster.lrem(CommonConstant.OnlineUsers,0,cookieToken);
			TUser loginUser = LoginUtils.getLoginUser();
			if(loginUser != null){
				redisCluster.del(CommonConstant.reidsLoginUserId + loginUser.getId());
			}
		}
		result.setMsg("退出登录成功");
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/onlineUsers",method = RequestMethod.GET)
	public MessageResult onlineUsers(){
		MessageResult result = new MessageResult();

		List<String> onlineUserIds = redisCluster.lrange(CommonConstant.OnlineUsers,0,-1);
		List<String> offlineUserIds = new ArrayList<String>();
		String userId = null;
		String useToken = null;
		String userJson = null;
		Map<String,String> userMap = new HashMap<String,String>();
		List<TUser> onlineUsers = new ArrayList<>();
		for (int i = 0; i < onlineUserIds.size(); i++) {
			userId = onlineUserIds.get(i);
			useToken = redisCluster.get(userId);
			userJson = redisCluster.get(useToken);
			if(StringUtils.isBlank(useToken) || StringUtils.isBlank(userJson)){
				//掉线用户
				offlineUserIds.add(userId);
			}else{
				userMap.put(userId,useToken);
				onlineUsers.add(JacksonUtils.fromJson(userJson,TUser.class));
			}
		}
		if(onlineUserIds != null){
			onlineUserIds.removeAll(offlineUserIds);
		}
		redisCluster.del(CommonConstant.OnlineUsers);
		for (int i = 0; i <onlineUserIds.size() ; i++) {
			userId = onlineUserIds.get(i);
			redisCluster.rpush(CommonConstant.OnlineUsers,userId);
		}
		result.setSuccess(true);
		result.setData(onlineUsers);
		return result;
	}
}
