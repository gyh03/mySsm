package com.gyh.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 方法计时器
 */
@Component("excuteTimeMonitor")
public class MethodTimeMonitoring implements MethodInterceptor {

    private final static Logger logger = Logger.getLogger(MethodTimeMonitoring.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
        StopWatch clock = new StopWatch();  
        clock.start(); //计时开始  
        Object result = null;  
        //监控的类名  
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();  
        //监控的方法名  
        String methodName = className + "." + invocation.getMethod().getName();  
        try {  
            //这个是我们监控的bean的执行并返回结果  
            result = invocation.proceed();  
        } catch (Throwable e) {  
            //监控的参数  
            Object[] objs = invocation.getArguments();  
            logger.error("方法：" + methodName + "参数:" + getString(objs)+"执行异常：", e);  
            throw e;  
        }  
        clock.stop(); //计时结束  
        if (logger.isInfoEnabled()) {  
            logger.info("[" + methodName + "] 执行时间:" + clock.getTime() + " ms；");
        }  
        return result;  
	}
	
	   /** 
     * 这个类主要是用于输出方法的参数 
     *  
     * @param objs 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public String getString(Object[] objs) {  
    	if(objs==null || objs.length==0) return "";
        StringBuffer stringBuffer = new StringBuffer();  
        for (int i = 0, len = objs.length; i < len; i++) {  
            if (objs[i] instanceof String) {  
                stringBuffer.append("String类型：" + objs[i].toString());  
            } else if (objs[i] instanceof Map) {  
                HashMap<String, Object> hashMap = (HashMap<String, Object>) objs[i];  
                HashMap<String, Object> map = hashMap;  
                Set<String> set = map.keySet();  
                stringBuffer.append("Map类型");  
                for (String str : set) {  
                    stringBuffer.append(str + "=" + map.get(str));  
                }  
            } else if (objs[i] instanceof Integer) {  
                stringBuffer.append("整数类型：");  
                stringBuffer.append(objs[i].toString());  
            } else {  
            	if(objs[i]!=null){
                    stringBuffer.append(objs[i].toString());
             	}
            }  
        }  
        return stringBuffer.toString();  
    }

}
