package com.gyh.controller;

import com.gyh.bean.TUser;
import com.gyh.common.inteceptor.SkipAuthCheck;
import com.gyh.common.pojo.MessageResult;
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
	/*redisCluster.set("cluster", "hahaha");
	String a =redisCluster.get("cluster");*/

	@Value("#{prop['jdbc.driver']}")
	private String driver;

	private final String OnlineUsers = "ONLINEUSERS";

	//登录--支持手机和用户名登录
	@SkipAuthCheck 
	@ResponseBody
	@RequestMapping(value = "/dologin",method = RequestMethod.POST)
	public MessageResult dologin(HttpServletRequest request,HttpServletResponse response,String userName,String password){
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
					String loginUserID="loginUserId_"+user.getId()+"";
					String userToken= IDGenerator.getUUID();

					System.out.println(redisSingle+"<<<<<<<<<<<<");
					/*redisSingle.opsForValue().set(userToken, JacksonUtils.toJson(user),1200, TimeUnit.SECONDS);
					redisSingle.opsForValue().set(loginUserID, userToken,1200, TimeUnit.SECONDS);*/
					redisCluster.set(userToken, JacksonUtils.toJson(user));
					//设置过期时间
					redisCluster.expire(userToken,1200);
					redisCluster.set(loginUserID,userToken);
					//记录在线用户
					redisCluster.rpush(OnlineUsers,loginUserID);

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

	@ResponseBody
	@RequestMapping("/onlineUsers")
	public MessageResult onlineUsers(){
		MessageResult result = new MessageResult();

		List<String> onlineUserIds = redisCluster.lrange(OnlineUsers,0,-1);
		List<String> offlineUserIds = new ArrayList<String>();
		String userId = null;
		String useTroken = null;
		String userJson = null;
		Map<String,String> userMap = new HashMap<>();
		List<TUser> onlineUsers = new ArrayList<>();
		for (int i = 0; i < onlineUserIds.size(); i++) {
			userId = onlineUserIds.get(i);
			useTroken = redisCluster.get(userId);
			userJson = redisCluster.get(useTroken);
			if(StringUtils.isBlank(useTroken) && StringUtils.isBlank(userJson)){
				offlineUserIds.add(userId);
			}else{
				userMap.put(userId,useTroken);
				onlineUsers.add(JacksonUtils.fromJson(userJson,TUser.class));
			}
		}
		onlineUserIds.removeAll(offlineUserIds);
		redisCluster.del(OnlineUsers);
		for (int i = 0; i <onlineUserIds.size() ; i++) {
			userId = onlineUserIds.get(i);
			redisCluster.rpush(userId,userMap.get(userId));
		}
		result.setSuccess(true);
		result.setData(onlineUsers);
		return result;
	}
}
