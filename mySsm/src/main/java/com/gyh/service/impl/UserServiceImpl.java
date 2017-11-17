package com.gyh.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyh.bean.TUser;
import com.gyh.bean.TUserExample;
import com.gyh.exception.InvalidCustomException;
import com.gyh.mapper.TUserMapper;
import com.gyh.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TUserMapper userMapper;
	
	//登录
	@Override
	public TUser queryUserByUserName(String userName)throws Exception{
		TUser user = null;
		TUserExample example = new TUserExample();
		example.createCriteria().andUsernameEqualTo(userName);
		List<TUser> list = userMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)){
			if(list.size()>1){
				throw new InvalidCustomException(5000,"存在两个用户名相同的用户");
			}else{
				user = list.get(0);
			}
		}
		return user;
	}

	@Override
	public int insertUser(TUser user) throws Exception {
		return userMapper.insert(user);
	}
}
