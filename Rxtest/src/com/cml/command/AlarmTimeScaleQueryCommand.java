package com.cml.command;

/**
 * 212L 7、查询故障报警器设备时标
 *
 */
public class AlarmTimeScaleQueryCommand extends BaseCommand {

	public String deviceId;

	public AlarmTimeScaleQueryCommand() {
		super(ALARM_TIME_SCALE_PACKAGE_ID);
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
		String checksumKey = "55" + "24" + deviceId;
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue;
		return value;
	}

}
