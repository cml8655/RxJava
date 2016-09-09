package com.cml.command;

/**
 * 4.2、故障报警器修改ID设备号
 *
 */
public class AlarmUpdateCommand extends BaseCommand {

	public String newDeviceId;
	public String deviceId;

	public AlarmUpdateCommand() {
		super(ALARM_UPDATE_PACKAGE_ID);
	}

	@Override
	public boolean isSupport(String command) {
		return command.substring(2, 4).equals(ALARM_UPDATE_PACKAGE_ID);
	}

	@Override
	public boolean parse(String command) {
		return isConfirmCommand(command);
	}

	@Override
	public String generateCommand() {
		// 字段描述 特征字 操作码 老设备号 新设备号 校验码 总长度(位)
		String checksumKey = "55" + "03" + deviceId + newDeviceId;
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue;
		return value;
	}

}
