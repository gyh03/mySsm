package com.gyh.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyh.bean.TUser;
import com.gyh.bean.TUserExample;
import com.gyh.exception.InvalidCustomException;
import com.gyh.mapper.TUserMapper;
import com.gyh.service.UserService;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.ws.api.pipe.Tube;

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
		//TODO 校验用户名是否重复
		return userMapper.insert(user);
	}

	@Override
	public Integer updateUser(TUser user) throws Exception {
		checkIsUser(user.getId());
		
		return userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 校验用户是否存在
	 * @param id
	 */
	public TUser checkIsUser(int id) throws Exception{
		TUser oldUser = userMapper.selectByPrimaryKey(id);
		if(oldUser == null){
			throw new InvalidCustomException("不存在的id");
		}
		if(oldUser.getDelfalg()!=null && oldUser.getDelfalg()){
			throw new InvalidCustomException("不存在的id");
		}
		return oldUser;
	}
	@Override
	public Integer deleteUserById(int id) throws Exception {
		checkIsUser(id);
		TUser user = new TUser();
		user.setId(id);
		user.setDelfalg(true);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public TUser queryUserById(int id) throws Exception {
		return checkIsUser(id);
	}
	
}