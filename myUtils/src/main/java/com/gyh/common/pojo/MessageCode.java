package com.gyh.common.pojo;

public enum MessageCode {
	
	error(false,"系统错误",4000),
	success(true,"操作成功",2000),
	fail(false,"操作失败",3000),
	bindingError(false,"数据绑定失败",3100),
	bindingSuccess(true,"数据绑定成功",2100),
	uploadError(false,"上传失败",3200),
	uploadSuccess(true,"上传成功",2200),
	noLogin(false,"未登录",3300)
    ;

	private String msg;
	private Boolean flag;
	private Integer  code;

	private MessageCode( Boolean flag,String msg, Integer code) {
		this.flag = flag;
		this.msg = msg;
		this.code = code;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
	
	
}
