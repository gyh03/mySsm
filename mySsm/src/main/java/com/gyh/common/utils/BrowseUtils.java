package com.gyh.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowseUtils {
	private final static String rE11="rv:11";
	private final static String IE11="msie 11.";
	private final static String IE10="msie 10.";
    private final static String IE9="msie 9.";
    private final static String IE8="msie 8.";
    private final static String IE7="msie 7.";
    private final static String IE6="msie 6.";
    private final static String MAXTHON="maxthon";
    private final static String QQ="qqbrowser";
    private final static String GREEN="greenbrowser";
    private final static String SE360="360se";
    private final static String FIREFOX="firefox";
    private final static String OPERA="opera";
    private final static String CHROME="chrome";
    private final static String SAFARI="safari";
    private final static String LBBROWSER="lbbrowser";
    private final static String UBrowser="ubrowser";
    private final static String bidubrowser="bidubrowser";
    private final static String metasr="metasr";
    private final static String OTHER="其它";
     
     
    /**
     * 判断浏览器类型
     * @param userAgent
     * @return
     */
    public static String checkBrowse(String userAgent){

    	if(regex(LBBROWSER,userAgent.toLowerCase()))return "猎豹";
    	if(regex(UBrowser,userAgent.toLowerCase()))return "UC";
    	if(regex(bidubrowser,userAgent.toLowerCase()))return "百度";
    	if(regex(metasr,userAgent.toLowerCase()))return "搜狗";
        if(regex(OPERA, userAgent.toLowerCase()))return OPERA;
        if(regex(FIREFOX, userAgent.toLowerCase()))return FIREFOX;
        if(regex(SE360, userAgent.toLowerCase()))return SE360;
        if(regex(GREEN,userAgent.toLowerCase()))return GREEN;
        if(regex(QQ,userAgent.toLowerCase()))return QQ;
        if(regex(MAXTHON, userAgent.toLowerCase()))return "遨游";
        if(regex(rE11,userAgent.toLowerCase()))return "ie 11";
        if(regex(IE11,userAgent.toLowerCase()))return "ie 11";
        if(regex(IE10,userAgent.toLowerCase()))return "ie 10";
        if(regex(IE9,userAgent.toLowerCase()))return "ie 9";
        if(regex(IE8,userAgent.toLowerCase()))return "ie 8";
        if(regex(IE7,userAgent.toLowerCase()))return "ie 7";
        if(regex(IE6,userAgent.toLowerCase()))return "ie 6";
        if(regex(CHROME, userAgent.toLowerCase()))return CHROME;
        if(regex(SAFARI, userAgent.toLowerCase()))return SAFARI;
        return OTHER;
    }
    public static boolean regex(String regex,String str){
        Pattern p =Pattern.compile(regex,Pattern.MULTILINE);
        Matcher m=p.matcher(str);
        return m.find();
    }
     
    public static void main(String[] args) {
        /*String ie9    ="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)";
        String ie8    ="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322)";
        String ie7    ="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322)";
        String ie6    ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)";
        String aoyou  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; Maxthon 2.0)";
        String qq     ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322) QQBrowser/6.8.10793.201";
        String green  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; GreenBrowser)";
        String se360  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; 360SE)";
         
        String chrome ="Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.11 (KHTML, like Gecko) Chrome/9.0.570.0 Safari/534.11";               
        String safari ="Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8";
        String fireFox="Mozilla/5.0 (Windows NT 5.2; rv:7.0.1) Gecko/20100101 Firefox/7.0.1";
        String opera  ="Opera/9.80  (Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
        String other  ="(Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
        System.out.println(BrowseUtils.checkBrowse(ie9));
        System.out.println(BrowseUtils.checkBrowse(ie8));
        System.out.println(BrowseUtils.checkBrowse(ie7));
        System.out.println(BrowseUtils.checkBrowse(ie6));
        System.out.println(BrowseUtils.checkBrowse(aoyou));
        System.out.println(BrowseUtils.checkBrowse(qq));
        System.out.println(BrowseUtils.checkBrowse(green));
        System.out.println(BrowseUtils.checkBrowse(se360));
        System.out.println(BrowseUtils.checkBrowse(chrome));
        System.out.println(BrowseUtils.checkBrowse(safari));
        System.out.println(BrowseUtils.checkBrowse(fireFox));
        System.out.println(BrowseUtils.checkBrowse(opera));
        System.out.println(BrowseUtils.checkBrowse(other));
        Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36
        */
    	
    	String fireFox="Mozilla/5.0 (compatible; MSIE 9.1; Windows NT 6.1; Trident/5.0)";
    	 System.out.println(BrowseUtils.checkBrowse(fireFox));
    }
    

	
	public static String getUserAgent() {
        HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request == null){
            return "";
        }
        String agent = request.getHeader("User-Agent");
        return checkBrowse(agent);
	}
     
}