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
	public int insertUser(TUser user)throws Exception;
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer updateUser(TUser user)throws Exception;
	/**
	 * 根据id删除用户信息(伪删除)
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteUserById(int id)throws Exception;
	/**
	 * 根据id查找用户
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public TUser queryUserById(int id)throws Exception;
}
