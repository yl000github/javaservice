package utils;
/**
 * 模仿log4j 做一个简单的日志系统
 * @author Yang
 *
 */
public class LogUtil {
	public static final String DIR="D:/logs/";
	public static void logDailyKeyPad(String msg){
		String now=DateUtil.getNowFormat("yy-MM-dd");
		String filename="log-key-"+now+".log";
		FileUtil.writeAdd(DIR+filename, msg);
	}
	public static void logDaily(String msg){
		String now=DateUtil.getNowFormat("yy-MM-dd");
		String filename="log-"+now+".log";
		FileUtil.writeAdd(DIR+filename, msg);
	}
}
