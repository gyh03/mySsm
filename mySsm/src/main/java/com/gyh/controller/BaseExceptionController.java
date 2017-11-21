package com.gyh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.gyh.common.pojo.MessageCode;
import com.gyh.common.pojo.MessageResult;
import com.gyh.exception.InvalidCustomException;

@ControllerAdvice
public class BaseExceptionController {
	
	@Value("#{prop['maxUploadSize']}")
	private Long maxUploadSize;
	/**
	 * 拦截Controller中抛出的自定义异常，并返回错误信息
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(InvalidCustomException.class)  
	@ResponseBody  
	public Object CustomExceptionMes(HttpServletRequest request, InvalidCustomException ex){
		MessageResult result = new MessageResult();
		result.setSuccess(MessageCode.error.getFlag());
		if(ex.getCode()!=null){
			result.setCode(ex.getCode());
		}else{
			result.setCode(MessageCode.error.getCode());
		}
		result.setMsg(ex.getMessage());
		return result;
	}
	
	/**
	 * 拦截Controller中抛出的所有异常，就近匹配，如果是InvalidCustomException异常则使用上面的方法，并返回错误信息
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)  
	@ResponseBody  
	public Object ExceptionMes(HttpServletRequest request, Exception ex){
		MessageResult result = new MessageResult();
		if(ex instanceof MaxUploadSizeExceededException){
			long max=0;
			if (maxUploadSize != null && maxUploadSize>0) {
				max = maxUploadSize / 1024;
			}
			result.setMsg("文件上传超过最大限制：" + max + "KB");
		}else{
			result.setMsg(ex.getMessage());
		}
		
		result.setCode(MessageCode.error.getCode());
		result.setSuccess(MessageCode.error.getFlag());
		return result;
	}
}
