package com.gyh.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gyh.utils.date.DateUtil;


/**
 * 
 * 检验是否符合某一个规则。
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

}
