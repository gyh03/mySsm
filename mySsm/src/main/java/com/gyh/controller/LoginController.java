package com.gyh.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyh.bean.TUser;
import com.gyh.common.inteceptor.SkipAuthCheck;
import com.gyh.common.pojo.MessageResult;
import com.gyh.service.UserService;
import com.gyh.utils.encrypt.MD5Util;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/loginController")
public class LoginController {
	private Logger log=Logger.getLogger(LoginController.class);
	
	@Autowired
	public UserService userService;
	/*@Autowired
	public RedisTemplate<String,String> redisSingle;
	//		System.out.println(redisSingle+"<<<<<<<<<<<<");
//		redisSingle.opsForValue().set("single", "jajaja",1200,TimeUnit.SECONDS);
//		redisCluster.set("cluster", "hahaha");
//		String a =redisCluster.get("cluster");
	*/
	@Autowired(required=false)
	public JedisCluster redisCluster;
	
	
	@Value("#{prop['jdbc.driver']}")
	public String driver;
	
	//登录--支持手机和用户名登录
	@SkipAuthCheck 
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin(HttpServletRequest request,HttpServletResponse response,String userName,String password){
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
					result.setSuccess(true);
					result.setData(user);
				}else{
					result.setMsg("密码错误");
				}
				
				/*String userid=user.getId()+"";
				String uuid=UUIDGenerator.generate(userid);
				redisClient.set(userid, uuid);
				redisClient.set(uuid, userid);
				
				result.put("user", user);
				result.put("msg", "success");
				result.put("uuid", redisClient.get(userid));*/
				
			}else{
				result.setMsg("用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}	
		return result;
	}

}
