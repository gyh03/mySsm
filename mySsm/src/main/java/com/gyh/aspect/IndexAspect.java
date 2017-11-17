package com.gyh.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.gyh.common.pojo.MessageCode;


//@Component
@Aspect
public class IndexAspect {

	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.gyh.controller..*(..))")
	public void aspect(){
		System.err.println("aspect  。。。");
	}
	
	public IndexAspect(){
		System.out.println("aspect init...");
	}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@Around("aspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object response = null;

		try {
			/*Object[] args = pjp.getArgs();
			long startTime = System.currentTimeMillis();

			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Method method = signature.getMethod();
			Class<?> clazz = method.getDeclaringClass();
			response = pjp.proceed(args);
			
			System.out.println(response);
			Map<String, Object> castResponse;
			if (response == null) {
				response = new HashMap<String, Object>();
			}

			if (response instanceof Map) {
				castResponse = (Map<String, Object>) response;
				castResponse.put("cost", (double)(System.currentTimeMillis() - startTime) / 1000);
				castResponse.put("code", MessageCode.error.getCode());
			}*/
		} catch (Exception e) {
		}
		return response;
	}
	
}
