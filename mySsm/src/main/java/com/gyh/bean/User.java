package com.gyh.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gyh.common.exception.CustomException;
import com.gyh.common.validation.IsString;
import com.gyh.utils.json.JacksonUtils;


public class User {

	private Long id;
	
	@IsString(minLength=11,maxLength=11)
	private String mobile;
	
	@IsString(minLength=1,maxLength=15)
	private String userName;
	
	@IsString(minLength=1,maxLength=20)
	private String password;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.createTime = sdf.parse(createTime);
		} catch (ParseException e) {
			throw new CustomException("invalid params.beginTime parse error.");
		}
	}

	public String toString() {
		try {
			return JacksonUtils.toJson(this);
		} catch (Exception e) {
		}
		return "json parse failed";
	}
}
