package com.gyh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyh.bean.User;
import com.gyh.common.exception.CustomException;
import com.gyh.common.inteceptor.SkipAuthCheck;
import com.gyh.common.pagehelper.PageHelper;
import com.gyh.common.validation.Validation;
import com.gyh.service.UserService;
import com.gyh.utils.encrypt.MD5Util;
import com.gyh.view.UserView;

@Controller
public class UserController {

	@Autowired
	private Validation validation;	
	@Autowired
	private UserService userService;	
/*	@Autowired
	private RedisClientTemplate redisClientTemplate;*/	
	/*@Autowired
	@Qualifier("mongoTemplate")
	protected MongoTemplate mongoTemplate;*/
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody String body){
		Map<String,Object> result = new HashMap<String, Object>();
		User user = validation.getObject(body, User.class, new String[]{"mobile","password"});
		user.setPassword(MD5Util.convertMD5(user.getPassword()));
		long id = userService.insert(user);
		result.put("id", id);
		return result;
    }
	
	@RequestMapping(value="/user", method=RequestMethod.PUT)
    @ResponseBody
    public Object put(@RequestBody String body){
		Map<String,Object> result = new HashMap<String, Object>();
		User user = validation.getObject(body, User.class, new String[]{"id"});
		User src = userService.selectOne(user.getId());
		if(src == null) {
			throw new CustomException("不存在的ID。");
		}
		try{
			//user = (User) MergerUtil.merger(src, user);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		user.setPassword(MD5Util.convertMD5(user.getPassword()));
		long id = userService.insert(user);
		result.put("id", id);
		return result;
    }

	
	@RequestMapping(value="/user", method=RequestMethod.DELETE)
    @ResponseBody
    public Object get(@RequestParam Long id){
        Map<String, Object> result=new HashMap<String, Object>();
        userService.delete(id);
        return result;
    }
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestParam String mobile,
    		@RequestParam String password){
        Map<String, Object> result=new HashMap<String, Object>();
        User user = userService.selectByMobile(mobile);
        if(user == null) {
        	throw new CustomException("找不到该手机号。");
        }
        if(!user.getPassword().equals(MD5Util.convertMD5(password))) {
        	throw new CustomException("密码错误。");
        }
        result.put("data", new UserView(user));
        
        return result;
    }
	
	//redis 测试
	/*@ResponseBody
	@RequestMapping("/testRedis")
	public Object testRedis(HttpServletResponse response,String data){
		Map<String, Object> result=new HashMap<String, Object>();
//		redisClientTemplate.exists("aaa");
		redisClientTemplate.set("key", data);
		result.put("data", redisClientTemplate.get("key"));
		return result;
	}*/
	
	@SkipAuthCheck
	@ResponseBody //ResponseBody返回json
	@RequestMapping("/testJson")
	public Object testJson(String name,int age){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", name);
		map.put("age", age);
		return map;
	}
	
	//分页测试
	@ResponseBody
	@RequestMapping("/testPage")
	public Object testPage(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> result=new HashMap<String, Object>();
		PageHelper.startPage(request);
		List<User> list=userService.selectAll();		
		PageHelper.addPages(result, list);
		result.put("msg", "success");
		result.put("data", list);
		return result;
	}
	//mongodb测试
	/*@ResponseBody
	@RequestMapping("/testMongo")
	public Object testMongo(HttpServletRequest request,HttpServletResponse response){	
		Map<String, Object> result=new HashMap<String, Object>();
		String collectionName="mycol2";
		String queryJson="{\"title\" : \"MongoDB1\"}";
		DBCollection collection=mongoTemplate.getCollection(collectionName);
		//查询条件，返回指定的键
		DBObject query=(DBObject)JSON.parse(queryJson);
		//检索查看结果  
		DBObject reslutJson = collection.findOne(query);//单条数据
		DBCursor cursor=collection.find(query);//多条数据	
		List list=new ArrayList();
		while (cursor.hasNext()) {
			DBObject ob=cursor.next();
			list.add(ob);
		}
		result.put("msg", "success");
		result.put("data", list);
		return result;
	}*/
}
