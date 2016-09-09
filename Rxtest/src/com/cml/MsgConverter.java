package com.cml;

public class MsgConverter {
	public static void main(String[] args) {
		String a = "1234567890abcdef";
		for (int i = 0; i < a.length(); i++) {
			System.out.print(Integer.toHexString(a.charAt(i)));
			if (i % 2 == 1) {
				System.out.print(" ");
			}
		}
		System.out.println("========================");
		byte[] b = a.getBytes();
		for (int i = 0; i < b.length; i++) {
			System.out.print((i & 0xf0) >> 4);
			if (i % 2 == 1) {
				System.out.print(" ");
			}
		}
		System.out.println("============");
		System.out.println(byte2HexStr(a.getBytes()));
		// 十六进制转成十进制
		System.out.println(Integer.valueOf("FF", 16).toString());
		// 十进制转成十六进制：
		System.out.println(Integer.toHexString(0xFF).toUpperCase());
	}

	/**
	 * bytes转换成十六进制字符串
	 */
	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}
}
