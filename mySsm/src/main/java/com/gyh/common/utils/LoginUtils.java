package com.gyh.common.utils;

import com.gyh.bean.TUser;
import com.gyh.common.constant.CommonConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Component
public class LoginUtils {


    /**
     * 获取当前登录用户
     * @return
     */
    public static TUser getLoginUser(){
        TUser loginUser = null;
        HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        loginUser = (TUser) session.getAttribute(CommonConstant.loginUserToken);
        return loginUser;
    }

    /**
     * 获取用户cookie中的用户登录令牌
     * @return
     */
    public static String getUserCookieToken(){
        HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cookieToken = null;
        //获取用户cookie中的用户登录令牌;
        Cookie cook = CookiesUtils.getCookieByName(request,CommonConstant.loginUserToken);
        if (cook != null) {
            cookieToken = cook.getValue().toString();
        }
        return cookieToken;
    }

    /**
     * 获取对象的真实IP地址，如果使用nginx代理，直接获取的ip为nginx服务器的ip，使用以下程序可以获取到用户真是
     *
     */
    public static String getIpAddress() {
        HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_REAL_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
