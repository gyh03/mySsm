package com.gyh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyh.bean.User;
import com.gyh.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public long insert(User user) {
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
	
	public List<User> selectAll() {
		return userMapper.selectAll();
	}
	
	//登录
	public User login(Map map){
		return userMapper.loginByUandP(map);
	}
}
