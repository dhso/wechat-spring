package com.minws.util;

public class AutoStringManager {
	/*
	 
	  AutoStringManager.random(false,8);
	  
	 */
	public static String random(boolean onlyNumber, int length) {
		String retStr = "";
		String strTable = onlyNumber ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
}
