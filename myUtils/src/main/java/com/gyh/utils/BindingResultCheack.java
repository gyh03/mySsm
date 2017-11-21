package com.gyh.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.gyh.common.pojo.BindingBean;
import com.gyh.common.pojo.MessageCode;
import com.gyh.common.pojo.MessageResult;

public class BindingResultCheack {

	/**
	 * 校验数据绑定结果
	 * @param bindingResult 数据格式化错误和数据校验错误 结果
	 * @param result 解析所有错误并返回
	 * @return
	 */
	public static MessageResult checkBindingResult(BindingResult bindingResult,MessageResult result) {
		List<BindingBean> bindingError = null;
		if(result == null){
			result = new MessageResult();
		}
		if (bindingResult.getErrorCount() > 0) {
			bindingError = new ArrayList<BindingBean>();
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (int i = 0; i < errors.size(); i++) {
				BindingBean bean = new BindingBean();
				FieldError error = errors.get(i);
				bean.setErrorMsg(error.getDefaultMessage());
				bean.setFiledName(error.getField());
				//TODO 日期格式解析
				/*if(error.getField().getClass().isAssignableFrom(Date.class)){
					bean.setErrorMsg("日期格式错误");
					bean.setFiledName(error.getField());
				}else {
					bean.setErrorMsg(error.getDefaultMessage());
					bean.setFiledName(error.getField());
				}*/
				bindingError.add(bean);
			}
			result.setMsg(MessageCode.bindingError.getName());
			result.setSuccess(MessageCode.bindingError.getFlag());
			result.setBindingSuccess(MessageCode.bindingError.getFlag());
			result.setCode(MessageCode.bindingError.getCode());
			result.setData(bindingError);
		}else{
			result.setBindingSuccess(MessageCode.bindingSuccess.getFlag());
		}
		return result;
	}
}
