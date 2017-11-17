package com.gyh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyh.bean.TUser;
import com.gyh.common.inteceptor.SkipAuthCheck;
import com.gyh.common.pojo.MessageResult;
import com.gyh.common.validation.Validation;
import com.gyh.service.UserService;
import com.gyh.utils.encrypt.MD5Util;

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
	public Object post(TUser user){
		MessageResult result = new MessageResult();
		result.setSuccess(false);
		try {
			user = validation.getObject(user, new String[]{"username","mobile","password"});
			user.setPassword(MD5Util.string2MD5(user.getPassword()));
			long id  = userService.insertUser(user);
			result.setSuccess(true);
			result.setData(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/*@RequestMapping(value="/TUser", method=RequestMethod.PUT)
    @ResponseBody
    public Object put(@RequestBody String body){
		Map<String,Object> result = new HashMap<String, Object>();
		TUser TUser = validation.getObject(body, TUser.class, new String[]{"id"});
		TUser src = TUserService.selectOne(TUser.getId());
		if(src == null) {
			throw new CustomException("不存在的ID。");
		}
		try{
			//TUser = (TUser) MergerUtil.merger(src, TUser);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		TUser.setPassword(MD5Util.convertMD5(TUser.getPassword()));
		long id = TUserService.insert(TUser);
		result.put("id", id);
		return result;
    }*/


	/*	@RequestMapping(value="/TUser", method=RequestMethod.DELETE)
    @ResponseBody
    public Object get(@RequestParam Long id){
        Map<String, Object> result=new HashMap<String, Object>();
        TUserService.delete(id);
        return result;
    }
	 */
	/*@RequestMapping(value="/TUser", method=RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestParam String mobile,
    		@RequestParam String password){
        Map<String, Object> result=new HashMap<String, Object>();
        TUser TUser = TUserService.selectByMobile(mobile);
        if(TUser == null) {
        	throw new CustomException("找不到该手机号。");
        }
        if(!TUser.getPassword().equals(MD5Util.convertMD5(password))) {
        	throw new CustomException("密码错误。");
        }
        result.put("data", new TUserView(TUser));

        return result;
    }*/

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
