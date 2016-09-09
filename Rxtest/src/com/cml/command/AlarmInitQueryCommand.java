package com.cml.command;

/**
 * 204L 6、查询故障报警器初始化参数（查询故障报警器设置情况）
 *
 */
public class AlarmInitQueryCommand extends BaseCommand {

	public String deviceId;

	public AlarmInitQueryCommand() {
		super(ALARM_INIT_PACKAGE_ID);
	}

	@Override
	public boolean isSupport(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean parse(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateCommand() {
		// 特征字 操作码 设备号 校验码
		String checksumKey = "55" + "23" + deviceId;
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue;
		return value;
	}

}
