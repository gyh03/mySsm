package com.gyh.utils;

import java.util.UUID;

/**
 * @author gyh
 * 多线程安全获取uuid和当前时间
 */
public class IDGenerator {
	public static Long number=0l;
	/**
	 * 获取主键
	 * @return
	 */
	public static synchronized String getUUID(){
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	}
	
	/**
	 * 获取时间戳
	 * @return
	 */
	public static synchronized String getTimeLongStr(){
	    return System.currentTimeMillis()+"";
	}
	
	public static synchronized Long getNumberLongStr(){
	    if(number>Long.MAX_VALUE){
	    	number=0l;
	    }else{
	    	number=number+1;
	    }
	    return number;
	}

}
