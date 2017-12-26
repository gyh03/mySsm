package com.gyh.aspect;

import com.gyh.bean.TOpeLogs;
import com.gyh.bean.TUser;
import com.gyh.common.pojo.MessageResult;
import com.gyh.common.utils.LoginUtils;
import com.gyh.service.OpeLogService;
import com.gyh.utils.json.JacksonUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Aspect
public class LogAspect {
	private Logger log=Logger.getLogger(LogAspect.class);

	@Autowired
	private OpeLogService opeLogService;

	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点，使用@OpeLogInfo注解的，记录日志
	@Pointcut("@annotation(com.gyh.aspect.OpeLogInfo))")
	public void aspect(){
	}

	/*
	 * 配置后置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@AfterReturning(pointcut = "aspect()",returning = "result")
	public void afterReturning(JoinPoint joinPoint,MessageResult result) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//读取登录用户
		TUser loginUser = (TUser) request.getSession().getAttribute("loginUser");
		//获取用户请求方法的参数
		StringBuffer params = new StringBuffer();
		if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
			for ( int i = 0; i < joinPoint.getArgs().length; i++) {
				if(joinPoint.getArgs()[i] instanceof MultipartFile){
					continue;
				}
				if(joinPoint.getArgs()[i] instanceof HttpServletResponse){
					continue;
				}
				if(joinPoint.getArgs()[i] instanceof HttpServletRequest){
					continue;
				}
				try {
					params.append("第").append(i).append("个参数：").append(JacksonUtils.toJson(joinPoint.getArgs()[i])).append("；");
				} catch (Exception e) {
					continue;
				}
			}
		}
		try {
			//*========数据库日志=========*//
			Map<String,Object> annotation = getControllerMethodDescription(joinPoint);

			TOpeLogs log = new TOpeLogs();
			log.setCreatetime(new Date());
			if(annotation.get("node") !=null){
				log.setNode(annotation.get("node").toString());
			}
			log.setLoginIp(LoginUtils.getIpAddress());
			log.setLoginBrowser(request.getHeader("User-Agent"));
			log.setParamJson(params.toString());
			log.setResultJson(JacksonUtils.toJson(result));
			log.setResultFlag(result.isSuccess());
			log.setCreateUserId(loginUser.getId());
			// 数据库持久化记录
			opeLogService.saveOpeLog(log);
		}  catch (Exception e) {
			//记录本地异常日志
			e.printStackTrace();
			log.error("Aop记录日志报错:"+ e.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public  static Map<String,Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		//拦截的实体类
		Object target = joinPoint.getTarget();
		//拦截的方法名称
		String methodName = joinPoint.getSignature().getName();
		//拦截的方法参数类型
		Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
		Method method = target.getClass().getMethod(methodName, parameterTypes);
		OpeLogInfo ope = method.getAnnotation(OpeLogInfo.class);
		map.put("node", ope.node());
		return map;
	}


}
