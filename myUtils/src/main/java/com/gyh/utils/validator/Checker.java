package com.gyh.utils.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gyh.utils.date.DateOpeUtil;
import com.gyh.utils.date.DateUtil;


/**
 * 
 * 检验是否符合某一个规则。
 * 
 */

/**
 * @author gyh
 *
 */
/**
 * @author gyh
 *
 */
public class Checker
{

	public Checker()
	{
	}

	/**
	 * 是否为手机
	 * @param phone
	 * @return
	 */
	public static final boolean isPhoneNo(String phone){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(177))\\d{8}$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	/**
	 * 是否为email
	 * @param email
	 * @return
	 */
	public static final boolean isEmail(String email){
		Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");;
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 银行卡号验证
	 * 
	 * @param value
	 *            校验字符串
	 * @return true:是银行卡号,false:非银行卡号
	 */
	public static boolean checkBankCard(String value) {
		char bit = getBankCardCheckCode(value.substring(0, value.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return value.charAt(value.length() - 1) == bit;

	}

	/**
	 * 银行卡号验证
	 * 
	 * @param value
	 *            校验字符串
	 * @return 非'N':是银行卡号,'N':非银行卡号
	 */
	private static char getBankCardCheckCode(String value) {
		if (value == null || value.trim().length() == 0 || !value.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = value.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}
	/**
	 * 验证是否是中文字符（只校验字符）
	 * 
	 * @param value  校验字符串
	 * @return true:是中文,false:非中文
	 */
	public static boolean isChinese(String value) {
		String chinese = "[\u0391-\uFFE5]";
		if (value.matches(chinese)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证身份证号码
	 * 
	 * @param value
	 *            校验字符串
	 * @return 返回校验结果map
	 *         <p>
	 *         key:sex,value:1:男,0:女
	 *         </p>
	 *         <p>
	 *         key:errorMsg,value:错误提示信息
	 *         </p>
	 *         <p>
	 *         key:birthday,value:生日
	 *         </p>
	 *         <p>
	 *         key:success,value:true:成功,false:失败
	 *         </p>
	 * 
	 */
	public static Map<String, Object> isValidIdNo(String value) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", "false");
		Pattern p = Pattern.compile("^([\\d]{6})([\\d]{8})([\\d]{3})([\\d|x|X])$");
		Matcher match = p.matcher(value);
		if (match.matches()) {
			match.reset();
			// 取得生日
			while (match.find()) {
				String birthday = match.group(2);
				if (DateOpeUtil.parseDate(birthday) == null) {
					result.put("errorMsg", "生日不正确");
					return result;
				}
				result.put("birthday", DateOpeUtil.parseDate(birthday));
				// 取得性别
				String sex = match.group(3);
				result.put("sex", String.valueOf(Integer.parseInt(sex) % 2));
				// while (match.find()) {
				// for (int i = 0; i <= match.groupCount(); i++) {
				// System.out.println("索引(" + i + ")→" + match.group(i));
				// }
				// }
			}
			result.put("success", "true");
		} else {
			result.put("errorMsg", "身份证号码格式不正确");
		}
		return result;
	}
	/**
	 * 判断是否是正数
	 * 
	 * @param value
	 *            校验字符串
	 * @return true:正数,false:非正数
	 */
	public static boolean isPlusNum(String value) {
		if (StringUtils.isBlank(value))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(value);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 是否为整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			Integer.parseInt(str);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}

	/**
	 * 是否为正整数
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInteger(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			int i = Integer.parseInt(str);
			return i > 0;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	/**
	 * 是否为非负数
	 * @param str
	 * @return
	 */
	public static boolean isNonnegativeInteger(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			int i = Integer.parseInt(str);
			return i >= 0;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	/**
	 * 是否为Long类型
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			Long.parseLong(str);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	/**
	 * 是否为正整数
	 * @param str
	 * @return
	 */
	public static boolean isPositiveLong(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			long l = Long.parseLong(str);
			return l > 0L;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	/**
	 * 是否为非负数
	 * @param str
	 * @return
	 */
	public static boolean isNonnegativeLong(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			long l = Long.parseLong(str);
			return l >= 0L;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	/**
	 * 是否为浮点数
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			Double.parseDouble(str);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	/**
	 * 是否为正浮点数
	 * @param str
	 * @return
	 */
	public static boolean isPositiveDecimal(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			double d = Double.parseDouble(str);
			return d > 0.0D;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * 是否为非负浮点数
	 * @param str
	 * @return
	 */
	public static boolean isNonnegativeDecimal(String str)
	{
		if(StringUtils.isBlank(str))
			return false;
		try
		{
			double d = Double.parseDouble(str);
			return d >= 0.0D;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * 是否是"yyyy-MM-dd"的日期格式
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str)
	{
		try
		{
			java.util.Date date = DateUtil.getDate(str,DateUtil.DATAFORMAT_STR);
			String formatStr = DateUtil.dateToDateString(date,DateUtil.DATAFORMAT_STR);
			return formatStr.equals(str);
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	/**
	 * 是否为"HH:mm:ss"的时间格式
	 * @param str
	 * @return
	 */
	public static boolean isTime(String str)
	{
		String formatStr;
		try
		{
			java.util.Date t = DateUtil.getDate(str,DateUtil.TIME_DEFAULT_FORMAT);
			formatStr = DateUtil.dateToDateString(t,DateUtil.TIME_DEFAULT_FORMAT);
			return formatStr.equals(str);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * 是否为"yyyy-MM-dd HH:mm:ss.SSS"或"yyyy-MM-dd HH:mm:ss"的时间格式
	 * @param str
	 * @return
	 */
	public static boolean isTimestamp(String str)
	{
		try
		{
			if(str == null || str.length() < 19)
				return false;
			if(str.length() == 19 && !str.endsWith("."))
				str = (new StringBuilder(String.valueOf(str))).append(".").toString();
			for(; str.length() < 23; str = (new StringBuilder(String.valueOf(str))).append("0").toString());
			String formatStr;
			java.util.Date date = DateUtil.getDate(str,DateUtil.TIMESTAMP_FORMAT);
			formatStr = DateUtil.dateToDateString(date,DateUtil.TIMESTAMP_FORMAT);
			return formatStr.equals(str);
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	
	public static void main(String[] args) {
//		String d = "2017-10-20";
//		System.out.println(isTimestamp(d));
		String s = "哈";
		System.out.println(isChinese(s));
	}
}
