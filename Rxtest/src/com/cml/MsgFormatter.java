package com.cml;

public class MsgFormatter {

	public static final String RELAY_PACKAGE_ID = "01";

	public static final String END_CODE = "59";
	public static final String CONFIRM_COMMAND_PREFIX = "81";

	public static void main(String[] args) {
		// System.out.println(restartRelay(1, "000001", "111111"));
		String scanCommand = scanRelays(RELAY_PACKAGE_ID, "0f0fff", "ffffffffffff");
		System.out.println("包号：" + getPackageId(scanCommand));
		System.out.println(format(scanCommand));
	}

	/**
	 * 获取返回信息的包号
	 * 
	 * @param msg
	 * @return
	 */
	public static final String getPackageId(String msg) {
		return msg.substring(2, 4);
	}

	/**
	 * 格式化输出
	 * 
	 * @param v
	 * @return
	 */
	public static final String format(String v) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < v.length(); i++) {
			result.append(v.charAt(i));
			if (i % 2 == 1) {
				result.append(" ");
			}
		}
		return result.toString();
	}

	public static final String checksum(String v) {

		String[] values = format(v).split(" ");
		int value = 0;
		for (String vv : values) {
			value += Integer.parseInt(vv, 16);
		}

		System.out.println("checksum:" + format(v) + ",value:" + Integer.toHexString(value % 256));

		return Integer.toHexString(value % 256);// 十进制转16进制
	}

	/**
	 * 确认指令
	 * 
	 * @param packageId
	 * @param fromId
	 * @param target
	 * @return
	 */
	public static String getConfirmCommand(String packageId, String fromId, String target) {
		String checksumKey = "81" + packageId + fromId + target + "00";
		String checksumValue = checksum(checksumKey);
		return checksumKey + checksumValue + END_CODE;
	}

	/**
	 * 2.2、查询中继参数报文
	 * 
	 * @param packageId
	 * @param from
	 * @param target
	 * @return
	 */
	public static String scanRelays(String packageId, String from, String target) {
		// 操作符 ,包号, 本机ID ,目的地址, 数据长度, 数据/控制符 ,8位校验和, 结束符
		String checksumKey = "A1" + packageId + from + target + "01";
		String checksumValue = checksum(checksumKey);

		String value = checksumKey + checksumValue + END_CODE;

		return value;
	}

	/**
	 * 3、中继返回查询参数报文返回分析
	 * 
	 * @param msg
	 * @return
	 */
	public static String getRealysMsg(String msg) {
		//
		// List<String> formatMsg = Arrays.asList(format(msg).split(" "));
		// System.out.println("getRealysMsg==>size:" + formatMsg.size());
		// if (formatMsg.size() != 59) {
		// throw new IllegalStateException("getRealysMsg size is not 59 but " +
		// formatMsg.size());
		// }

		return msg.substring(30, msg.length() - 4);
	}

	/**
	 * 中继重启报文
	 * 
	 * @param target
	 * @return
	 */
	public static String restartRelay(int packageId, String fromId, String target) {
		// 操作符, 包号 ,本机ID ,目的地址 ,数据长度, 数据/控制符 ,8位校验和, 结束符
		// String command = "A1" + packageId + fromId + target + 1 + "04" + 1 +
		// "59";
		// return format("A1", packageId, fromId, target, 1, "04", 1, END_CODE);
		return null;
	}

	/**
	 * 获取中继号D3,D2,D1 (0-FFE)
	 * 
	 * @param code
	 * @return
	 */
	public static String getRelayNo(String code) {
		return code.substring(8, 11);
	}
}
