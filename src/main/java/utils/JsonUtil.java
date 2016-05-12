package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class JsonUtil {
	private static Gson gson;
	private static Gson getGson(){
		if(gson==null){
			gson= new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		}
		return gson;
	}
	public static String ob2json(Object ob){
		Gson gson=getGson();
		return gson.toJson(ob);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object json2ob(String json,Class c){
		Gson gson=getGson();
		return gson.fromJson(json, c);
	}
	public static JsonElement json2je(String json){
		Gson gson=getGson();
		JsonElement j=gson.fromJson(json, JsonElement.class);
		return j;
	}
}
