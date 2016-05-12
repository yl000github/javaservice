package utils;

public class StringUtil {
	/**
	 * 非空反true
	 */

	public static boolean checkValid(String src){
		if(src!=null&&!src.trim().isEmpty()) return true;
		else return false;
	}
}
