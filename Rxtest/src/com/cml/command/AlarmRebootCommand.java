package com.cml.command;

/**
 * 226L 9、报警器重启指令
 *
 */
public class AlarmRebootCommand extends BaseCommand {

	public String deviceId;

	public AlarmRebootCommand() {
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
		String checksumKey = "55" + "95" + deviceId;
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue;
		return value;
	}

}
