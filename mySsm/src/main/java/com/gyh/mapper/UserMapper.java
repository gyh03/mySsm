package com.gyh.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gyh.bean.User;

public interface UserMapper extends MapperI<User>{
	
	public User selectByMobile(@Param("mobile")String mobile);
	
	public User loginByUandP(Map map);

}
