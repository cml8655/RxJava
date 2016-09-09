package com.cml.command;

/**
 * 4.2、故障报警器修改ID设备号
 *
 */
public class AlarmUpdateCommand extends BaseCommand {

	public AlarmUpdateCommand() {
		super(ALARM_UPDATE_PACKAGE_ID);
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
		// TODO Auto-generated method stub
		return null;
	}

}
