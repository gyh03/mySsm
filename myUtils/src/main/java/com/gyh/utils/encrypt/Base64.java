package com.gyh.utils.encrypt;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * BASE64解密
 * 
 */
public abstract class Base64 {

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) throws Exception {
		return  new String((new BASE64Decoder()).decodeBuffer(key));
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key.getBytes());
	}

	/**
	 * 字符串倒叙
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String reverse(String s)throws Exception{
		return new StringBuffer(s).reverse().toString();
	}
	
	public static void main(String[] args) throws Exception {
		String a="111111";
		String md5=new String(MD5Util.string2MD5(a));//MD5密码
		System.out.println(md5);
		String base64 = encryptBASE64(reverse(md5));
		System.out.println(base64);//base64再次加密后
		System.out.println(reverse(decryptBASE64(base64)));//base64解密后
	}
}
