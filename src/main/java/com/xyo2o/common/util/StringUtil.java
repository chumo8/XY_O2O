package com.xyo2o.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

public class StringUtil {
	/**
	 * 是否非空判断
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		return str!=null && ! "".equals(str.trim());
	}
	/**
	 * 正则表达式判断
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		 boolean flag = false;
	        try{
	                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	                Pattern regex = Pattern.compile(check);
	                Matcher matcher = regex.matcher(str);
	                flag = matcher.matches();
	            }catch(Exception e){
	                flag = false;
	            }
	        return flag;
	}
	/**
	 * 忽略大小写
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean ignoreCaseEquals(String str1,String str2){  
	      return str1 == null ? str2 == null :str1.equalsIgnoreCase(str2);  
	}  
	/**
	 * 替换date yyy
	 * @param
	 * @return
	 */
	public static String relpeceDate(String str){
		String mm=str.substring(0, 2);
		String dd=str.substring(3,5);
		String yyyy=str.substring(6,10);
		String hhhh=str.substring(10);
		String newDate=yyyy+"-"+mm+"-"+dd+hhhh;		
		return newDate;
	}
	
	/**
	 * 获取真实ip地址
	 * @param request
	 * @return
	 */
	public static String getRequestRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.contains(",")) {
			ip = ip.split(",")[0];
		}

		if (!checkIp(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIp(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIp(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (!checkIp(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 获取IP工具
	 * @param ip
	 * @return
	 */
	private static boolean checkIp(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)) {
			return false;
		}
		return true;
	}
	public static boolean isNotNullAndEmpty(String str){
		return str!=null && !"".equals(str.trim());
	}
	public static boolean isNullOrEmpty(String str){
		return str==null || "".equals(str.trim());
	}
	/**
	 * String转int 返回null则出错
	 * @param Number
	 * @return
	 */
	public static Integer toNumber(String Number){
		try{
			int number = Integer.parseInt(Number.trim());
			return number;
		}catch(Exception e){
			return null;
		}
		
	}
	
	/**
     * 验证手机号码
     * @param
     * @return
     */
    public static boolean isMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
                Pattern regex = Pattern.compile("0?(13|14|15|18|17)[0-9]{9}");
     
                Matcher matcher = regex.matcher(mobileNumber);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
    /**
     * MD5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	MessageDigest md5=MessageDigest.getInstance("MD5");
    	BASE64Encoder base64en = new BASE64Encoder();
    	return base64en.encode(md5.digest(str.getBytes("utf-8")));
    }
}
