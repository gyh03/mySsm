package com.gyh.view;

import java.util.HashMap;

import com.gyh.bean.User;
import com.gyh.util.DateTool;

public class UserView extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public UserView(User user) {
		put("id", user.getId());
		put("mobile", user.getMobile());
		put("createTime", DateTool.standardSdf.format(user.getCreateTime()));
	}
	
}
