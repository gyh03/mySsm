package com.gyh.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookiesUtils {

    /**
     * 创建一个无生命周期的cookie，即随着浏览器的关闭即消失的cookie
     * @param request
     * @param response
     */
    public static void makeCookieNoLife(HttpServletRequest request, HttpServletResponse response,String cName,String cValue){
        Cookie cookie = new Cookie(cName,cValue);
        response.addCookie(cookie);
    }

    /**
     * 下面建立一个有生命周期的cookie,可以设置他的生命周期
     * @param request
     * @param response
     * @param cName
     * @param cValue
     * @param path
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void makeCookieLife(HttpServletRequest request, HttpServletResponse response,String cName,String cValue,String path,int maxAge){
        Cookie cookie =  new Cookie(cName,cValue);
        if(maxAge > 0){
            cookie.setMaxAge(maxAge);
        }
        //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }
    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
