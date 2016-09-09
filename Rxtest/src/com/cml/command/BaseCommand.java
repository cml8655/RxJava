package com.cml.command;

public abstract class BaseCommand {

	public static final String RELAY_SCAN_PACKAGE_ID = "01";
	public static final String RELAY_REBOOT_PACKAGE_ID = "02";

	public static final String ALARM_SCAN_PACKAGE_ID = "50";
	public static final String ALARM_UPDATE_PACKAGE_ID = "51";
	public static final String ALARM_STATUE_PACKAGE_ID = "52";
	public static final String ALARM_INIT_PACKAGE_ID = "53";
	public static final String ALARM_TIME_SCALE_PACKAGE_ID = "54";
	public static final String ALARM_REBOOT_PACKAGE_ID = "55";

	public static final String FROM_ID = "00000000000F";// 默认来源ID
	public static final String TARGET_ALL = "FFFFFFFFFFFF";// 查询全部

	public static final String END_CODE = "59";

	public String packageId;
	public String targetId;

	public BaseCommand(String packageId) {
		super();
		this.packageId = packageId;
	}

	public abstract boolean isSupport(String command);

	/**
	 * 将指令转换成bean
	 * 
	 * @param command
	 * @return
	 */
	public abstract boolean parse(String command);

	/**
	 * 生成指定的command
	 * 
	 * @return
	 */
	public abstract String generateCommand();

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

		String checksumValue = Integer.toHexString(value % 256);

		if (checksumValue.length() == 1) {
			return "0" + checksumValue;
		}

		return checksumValue;// 十进制转16进制
	}

	/**
	 * 确认指令
	 * 
	 * @param packageId
	 * @param fromId
	 * @param target
	 * @return
	 */
	public boolean isConfirmCommand(String command) {
		String targetCommand = "81" + packageId + FROM_ID + targetId + "00";
		return targetCommand.equals(command);
	}
}
