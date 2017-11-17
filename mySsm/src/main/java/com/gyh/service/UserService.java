package com.gyh.service;

import com.gyh.bean.TUser;

public interface UserService {
	
	/**
	 * 根据用户名查找唯一用户
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public TUser queryUserByUserName(String userName)throws Exception;
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int insertUser(TUser user)throws Exception;
}
