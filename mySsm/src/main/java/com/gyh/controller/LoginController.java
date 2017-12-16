package com.gyh.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyh.utils.IDGenerator;
import com.gyh.utils.json.JacksonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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


	@Autowired
	public RedisTemplate<String,String> redisSingle;


	@Autowired(required=false)
	public JedisCluster redisCluster;
	/*redisCluster.set("cluster", "hahaha");
	String a =redisCluster.get("cluster");*/

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
					String userid="loginUserId_"+user.getId()+"";
					String uuid= IDGenerator.getUUID();

					System.out.println(redisSingle+"<<<<<<<<<<<<");
					redisSingle.opsForValue().set(userid, JacksonUtils.toJson(user),1200, TimeUnit.SECONDS);
					redisSingle.opsForValue().set(uuid, userid,1200, TimeUnit.SECONDS);

					result.setData(uuid);
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

}
