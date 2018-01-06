package com.gyh.common.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyh.bean.TUser;
import com.gyh.common.constant.CommonConstant;
import com.gyh.common.pojo.MessageCode;
import com.gyh.common.pojo.MessageResult;
import com.gyh.common.utils.CookiesUtils;
import com.gyh.common.utils.LoginUtils;
import com.gyh.common.utils.WriteUtils;
import com.gyh.utils.encrypt.MD5Util;
import com.gyh.utils.json.JacksonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gyh.service.UserService;
import redis.clients.jedis.JedisCluster;

import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor{
	private Logger log=Logger.getLogger(LoginInterceptor.class);
	@Autowired(required = false)
	private JedisCluster redisCluster;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()	 *
	 * 可根据需要继续重写postHandle(显示视图前拦截)，postHandle(显示完视图后拦截)
	 */
	/**
	 * 登录拦截，权限校验
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		MessageResult result = new MessageResult();
		/**
		    1、Class.isAssignableFrom()作用与instanceof相似，
			 前者是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口
			 后者是用来判断一个对象实例是否是一个类或接口的或其子类子接口的实例
		 	2、HandlerMethod及子类主要用于封装方法调用相关信息
			 简单点说我们可以通过这个类对象获得被调用方法的各种信息，如方法名、参数类型等
		 */
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			//转码
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			Method method = ((HandlerMethod) handler).getMethod();
			//获得注解对象
			SkipLoginCheck skipLoginCheck = ((HandlerMethod) handler).getMethodAnnotation(SkipLoginCheck.class);
			//使用@SkipLoginCheck注解的方法，可以跳过登录判断
			if(skipLoginCheck != null){
				return true;
			}else{
				//拦截登录
				String cookieToken = LoginUtils.getUserCookieToken();
				if(StringUtils.isBlank(cookieToken)){
					noLoginWrite(result,response);
					return false;
				}
				//拼接sessionId，防止单独使用cookie中的用户令牌被别人得到后，造成危险，sessionId是用户浏览器与服务器的会话id，不同的会话id不同
				String sessionId = request.getSession().getId();
				String redisToken = MD5Util.string2MD5(sessionId + cookieToken);
				String userJson = redisCluster.get(redisToken);
				if(StringUtils.isBlank(userJson)){
					noLoginWrite(result,response);
					return false;
				}
				TUser loginUser = JacksonUtils.fromJson(userJson,TUser.class);
				if(loginUser == null){
					noLoginWrite(result,response);
					return false;
				}
				//TODO 权限校验
			}
			log.debug(method.getName());
		}
		return true;
	}

	/**
	 * 未登录信息输出给用户
	 * @param result
	 * @param response
	 */
	public void noLoginWrite(MessageResult result, HttpServletResponse response ){
		result.setSuccess(MessageCode.noLogin.getFlag());
		result.setMsg(MessageCode.noLogin.getMsg());
		result.setCode(MessageCode.noLogin.getCode());
		WriteUtils.renderObject(result, response);
	}

}
