package com.cml.command;

/**
 * 中继重启报文 2.3
 * 
 * @author team-lab
 *
 */
public class RelayRebootCommand extends BaseCommand {

	public RelayRebootCommand() {
		super(RELAY_REBOOT_PACKAGE_ID);
	}

	@Override
	public boolean isSupport(String command) {
		return packageId.equals(command.substring(2, 4));
	}

	@Override
	public boolean parse(String command) {
		return false;
	}

	@Override
	public String generateCommand() {
		// 操作符 ,包号, 本机ID ,目的地址, 数据长度, 数据/控制符 ,8位校验和, 结束符
		String checksumKey = "A1" + packageId + FROM_ID + targetId + "01" + "04";
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue + END_CODE;
		return value;
	}

}
