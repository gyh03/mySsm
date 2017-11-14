package com.gyh.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gyh.bean.User;

@Component
public interface UserMapper extends MapperI<User>{
	
	public User selectByMobile(@Param("mobile")String mobile);

}
