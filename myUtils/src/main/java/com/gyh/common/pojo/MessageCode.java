package com.gyh.common.pojo;

public enum MessageCode {
	
	error(false,"系统错误",4000),
	success(true,"操作成功",2000),
	fail(false,"操作失败",3000),
	bindingError(false,"数据绑定失败",3100),
	bindingSuccess(true,"数据绑定成功",2100),
	uploadError(false,"上传失败",3200),
	uploadSuccess(true,"上传成功",2200)
    ;

	private String name;
	private Boolean flag;
	private Integer  code;

	private MessageCode( Boolean flag,String name, Integer code) {
		this.flag = flag;
		this.name = name;
		this.code = code;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
	
	
}
