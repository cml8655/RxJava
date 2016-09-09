package com.cml.command;

/**
 * 报警器查询指令
 * 
 * @author team-lab
 *
 */
public class AlarmQueryCommand extends BaseCommand {

	public String deviceId;
	public String minDeviceId;
	public String maxDeviceId;
	public String preRelay;
	public String nextRelay;
	public String minBranch;
	public String maxBranch;
	public String resendTimes;
	public String workModel;
	public String time;
	public String sendDelay;
	public String voltage;// 电压

	public AlarmQueryCommand() {
		super(ALARM_SCAN_PACKAGE_ID);
	}

	@Override
	public boolean isSupport(String command) {
		return command.startsWith("B4" + packageId);
	}

	@Override
	public boolean parse(String command) {
		try {
			// 数据包
			String data = command.substring(30, command.length() - 4);

			// 设备id
			deviceId = data.substring(2, 14);
			// 设备最小值
			minDeviceId = data.substring(14, 16);
			// 设备最大值
			maxDeviceId = data.substring(16, 18);

			preRelay = data.substring(18, 22);
			nextRelay = data.substring(22, 26);
			minBranch = data.substring(26, 28);
			maxBranch = data.substring(28, 30);
			resendTimes = data.substring(30, 32);
			workModel = data.substring(32, 34);
			time = data.substring(34, 42);
			sendDelay = data.substring(42, 44);
			voltage = data.substring(44, 46);// 电压
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String generateCommand() {
		// 操作符 ,包号, 本机ID ,目的地址, 数据长度, 数据/控制符 ,8位校验和, 结束符
		String checksumKey = "A4" + packageId + FROM_ID + targetId + "01" + "03";
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue + END_CODE;
		return value;
	}

}
