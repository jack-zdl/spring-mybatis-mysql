package com.bsg.api.util;

import org.springframework.util.DigestUtils;

/**
 * 加密处理工具类
 *
 * @author HCK
 *
 */
public class EncryptionUtils {

	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

	public static void main(String[] args) {
		System.out.println(getMD5("000000"));
	}
}
