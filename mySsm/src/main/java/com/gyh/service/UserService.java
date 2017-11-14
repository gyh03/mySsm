package com.gyh.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gyh.bean.User;
import com.gyh.mapper.UserMapper;
import com.gyh.util.CommonUtil;
import com.gyh.util.DateTool;

@Service("userService")
public class UserService {

	@Resource
	private UserMapper userMapper;
	
	public long insert(User user) {
		String now = DateTool.standardSdf.format(new Date());
		CommonUtil.setDefaultValue(user);
		user.setCreateTime(now);
		userMapper.insert(user);
		return user.getId();
	}
	
	public User selectByMobile(String mobile) {
		return userMapper.selectByMobile(mobile);
	}
	
	public User selectOne(long id) {
		return userMapper.selectOne(id);
	}
	
	public void delete (long id) {
		userMapper.delete(id);
	}
	
}
