package com.gyh.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.gyh.bean.User;
import com.gyh.common.inteceptor.SkipAuthCheck;
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
	public RedisTemplate<String,String> redisSingle;*/
	@Autowired
	public JedisCluster redisCluster;
	
	
	@Value("#{prop['jdbc.driver']}")
	public String driver;
	
	//登录--支持手机和用户名登录
	@SkipAuthCheck //排除拦截
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin(HttpServletRequest request,HttpServletResponse response,String loginname,String password){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> result=new HashMap<String, Object>();
		ModelAndView mv=new ModelAndView();
		if(StringUtils.isEmpty(loginname)){
			result.put("msg", "请输入用户名或手机号");
			mv.addObject("result", result);
		}
		if(StringUtils.isEmpty(password)){
			result.put("msg", "请输入密码");
			mv.addObject("result", result);
		}
		map.put("loginname", loginname);
		map.put("password", MD5Util.string2MD5(password));
//		System.out.println(redisSingle+"<<<<<<<<<<<<");
//		redisSingle.opsForValue().set("single", "jajaja",1200,TimeUnit.SECONDS);
//		redisCluster.set("cluster", "hahaha");
		String a =redisCluster.get("cluster");
		User user=userService.login(map);	
		
		if(user!=null){
			String userid=user.getId()+"";
			/*String uuid=UUIDGenerator.generate(userid);
			redisClient.set(userid, uuid);
			redisClient.set(uuid, userid);
			
			result.put("user", user);
			result.put("msg", "success");
			result.put("uuid", redisClient.get(userid));*/
			mv.addObject("result", result);
//			mv.setViewName("hello");
		}else{
			result.put("msg", "fail");
			mv.addObject("result", result);
		}
		result.put("msg", driver+"<>"+a);
		return result;
	}

}
