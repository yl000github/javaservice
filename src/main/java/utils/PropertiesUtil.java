package utils;

import java.io.IOException;
import java.util.Properties;


public class PropertiesUtil
{
	
	
	private static Properties prop = new Properties();

	public static String getValue(String proName,String key)
	{
		try
		{
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(proName));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return (String) prop.get(key);
	}
	public static String getValueD(String key){
		return getValue("application.properties", key);
	}
}
