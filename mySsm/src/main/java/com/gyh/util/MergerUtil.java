package com.gyh.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.gyh.exception.InvalidCustomException;
/*
 * @author chenlisong
 */
public class MergerUtil {
	/**
	 * 如果update的属性是空，则从src中取值再赋值到update属性中。
	 * @param src     	数据库中的数据
	 * @param update	要更改的数据
	 * @return			最终要update的数据
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws Exception
	 */
	public static Object merger(Object src,Object update) throws NoSuchMethodException, SecurityException {
		
		if(src == null){
			throw new InvalidCustomException("can not find object.");
		}
		
		Field[] fields = update.getClass().getDeclaredFields();
		
		if(fields == null || fields.length <= 0 ){
			throw new InvalidCustomException("input object has no field .");
		}
		
		for(Field field : fields){
			String getMethodName = "get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
			String setMethodName = "set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
			Method getMethod = update.getClass().getDeclaredMethod(getMethodName);
			Object updateObj = invokeObject(getMethod,update);
			
			if(updateObj == null){
				Object srcObj = invokeObject(getMethod,src);
				if(srcObj != null && srcObj.getClass() == Date.class){
					Date dateSrcObj = (Date)srcObj;
					Method setMethod = update.getClass().getDeclaredMethod(setMethodName,String.class);
					invokeObject(setMethod,update, DateTool.standardSdf.format(dateSrcObj));
				}else if(srcObj != null){
					Method setMethod = update.getClass().getDeclaredMethod(setMethodName,srcObj.getClass());
					invokeObject(setMethod,update, srcObj);
				}
			}
		}
		return update;
	}
	
	private static Object invokeObject(Method m,Object obj,Object ... params){
		Object result = null;
			try {
				result = m.invoke(obj,params);
			} catch (IllegalAccessException e) {
			} catch (IllegalArgumentException e) {
			} catch (InvocationTargetException e) {
			}
		return result;
	}
	
}
