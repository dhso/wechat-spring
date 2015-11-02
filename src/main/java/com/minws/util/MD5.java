package com.minws.util;

import java.security.MessageDigest;

public class MD5 {
	public static final String getMD5(String str) throws Exception{
		/** 获取MD5加密的对象 */
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		/** 加密 */
		md5.update(str.getBytes());
		/** 得到加密后的字节数组 */
		byte[] md5Bytes = md5.digest();
		/** 把16转化成32位 */
		String res = "";
		for (int i = 0; i < md5Bytes.length; i++){
			int temp = md5Bytes[i] & 0xff;
			if (temp <= 0xf){ // 小于15的数字 转化成16进制前面加"0"
				res += 0;
			}
			res += Integer.toHexString(temp);
		}
		return res;
	}
}
