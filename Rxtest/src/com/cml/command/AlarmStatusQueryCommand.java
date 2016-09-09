package com.cml.command;

/**
 * 196L 5、查询故障报警器运行状态<br>
 * 261L 11、故障报警器回复运行状态
 *
 */
public class AlarmStatusQueryCommand extends BaseCommand {

	public String deviceId;
	public String queryTime;
	public String content;
	public String voltage;
	public String current;
	public String checksum;

	public AlarmStatusQueryCommand() {
		super(ALARM_STATUE_PACKAGE_ID);
	}

	@Override
	public boolean isSupport(String command) {
		return command.startsWith("5512");
	}

	@Override
	public boolean parse(String command) {
		try {
			// 特征字 操作码 设备号 查询时间 报文内容 电池电压 校验码
			deviceId = command.substring(4, 16);
			queryTime = command.substring(16, 32);
			content = command.substring(32, 48);

			// 报文内容说明：该字段共8位,高二位"08"代表查询报文信息,后六位代表当前的负荷电流值，其中前4为表示整数部分，后2位表示小数位；
			// 查询时间：如果报文内容特征字不是“08”，查询时间为故障发生时间
			// 如果报文内容特征字为 08，查询时间为当前时间
			//电池电压： 为当前的电池电压，两字节ASCII码。例 电池电压为3.6V   则 两字节为0x33 ,0x36

			voltage = command.substring(48, 52);
			checksum = command.substring(52, 54);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public String generateCommand() {
		// 特征字 操作码 设备号 校验码
		String checksumKey = "55" + "25" + deviceId;
		String checksumValue = checksum(checksumKey);
		String value = checksumKey + checksumValue;
		return value;
	}

}
