package com.gyh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyh.bean.TUser;
import com.gyh.common.inteceptor.SkipAuthCheck;
import com.gyh.common.pojo.MessageCode;
import com.gyh.common.pojo.MessageResult;
import com.gyh.exception.InvalidCustomException;
import com.gyh.service.UserService;
import com.gyh.utils.BindingResultCheack;
import com.gyh.utils.encrypt.MD5Util;

@Controller
public class UserController {

	@Autowired
	private UserService userService;	
	/*	@Autowired
	private RedisClientTemplate redisClientTemplate;*/	
	/*@Autowired
	@Qualifier("mongoTemplate")
	protected MongoTemplate mongoTemplate;*/

	@RequestMapping(value="/user", method=RequestMethod.POST)
	@ResponseBody
	public Object insertUser(@Valid()TUser user,BindingResult bindingResult){
		MessageResult result = new MessageResult();
		//数据绑定结果
		result = BindingResultCheack.checkBindingResult(bindingResult,result);
		if(result.isSuccess() !=null && !result.isSuccess() ){
			return result;
		}
		try {
			user.setPassword(MD5Util.string2MD5(user.getPassword()));
			long id  = userService.insertUser(user);
			if(id > 0){
				result.setSuccess(MessageCode.success.getFlag());
				result.setMsg(MessageCode.success.getName());
				result.setCode(MessageCode.success.getCode());
			}
			result.setData(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value="/user", method=RequestMethod.PUT)
    @ResponseBody
    public Object updateUser(@Valid()TUser user,BindingResult bindingResult){
		MessageResult result = new MessageResult();
		result = BindingResultCheack.checkBindingResult(bindingResult,result);
		if(result.isSuccess() !=null && !result.isSuccess() ){
			return result;
		}
		try {
			if(user.getId() == null){
				throw new InvalidCustomException("ID不可为空");
			}
			if(user.getPassword()!=null){
				user.setPassword(MD5Util.string2MD5(user.getPassword()));
			}
			Integer update = userService.updateUser(user);
			if(update != null && update > 0){
				result.setSuccess(MessageCode.success.getFlag());
				result.setMsg(MessageCode.success.getName());
				result.setCode(MessageCode.success.getCode());
			}
			result.setData(update);
		} catch (Exception e) {
			result.setMsg(e.getMessage());
		}
		return result;
    }


	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUser(@PathVariable("id")Integer id){
		MessageResult result = new MessageResult();
		result.setSuccess(MessageCode.fail.getFlag());
		result.setMsg(MessageCode.fail.getName());
		result.setCode(MessageCode.fail.getCode());
		try {
			if(id == null){
				throw new InvalidCustomException("ID不可为空");
			}
			Integer del = userService.deleteUserById(id);
			if(del != null && del > 0){
				result.setSuccess(MessageCode.success.getFlag());
				result.setMsg(MessageCode.success.getName());
				result.setCode(MessageCode.success.getCode());
			}
			result.setData(del);
		} catch (Exception e) {
			result.setMsg(e.getMessage());
		}
		return result;
    }
	 
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Object getUser(@PathVariable("id")Integer id){
		MessageResult result = new MessageResult();
		result.setSuccess(MessageCode.fail.getFlag());
		result.setMsg(MessageCode.fail.getName());
		result.setCode(MessageCode.fail.getCode());
		try {
			if(id == null){
				throw new InvalidCustomException("ID不可为空");
			}
			TUser user = userService.queryUserById(id);
			result.setSuccess(MessageCode.success.getFlag());
			result.setMsg(MessageCode.success.getName());
			result.setCode(MessageCode.success.getCode());
			result.setData(user);
		} catch (Exception e) {
			result.setMsg(e.getMessage());
		}
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

	/*//分页测试
	@ResponseBody
	@RequestMapping("/testPage")
	public Object testPage(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> result=new HashMap<String, Object>();
		PageHelper.startPage(request);
		List<TUser> list=TUserService.selectAll();		
		PageHelper.addPages(result, list);
		result.put("msg", "success");
		result.put("data", list);
		return result;
	}*/
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
