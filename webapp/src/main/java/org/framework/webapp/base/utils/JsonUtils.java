package org.framework.webapp.base.utils;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	public static Map<String, String> jsonToMap(String jsonStr){
		GsonBuilder builder=new GsonBuilder();
		Gson gson=builder.create();
		
		Map<String, String> result=gson.fromJson(jsonStr, new TypeToken<Map<String, String>>(){}.getType());
		
		return result;
	}
	
	public static String mapToJson(Map<String, String> map){
		GsonBuilder builder=new GsonBuilder();
		Gson gson=builder.create();
		String result=gson.toJson(map);
		return result;
	}
	
	public static <T> T jsonToTarget(String jsonStr){
		GsonBuilder builder=new GsonBuilder();
		Gson gson=builder.create();
		T result=gson.fromJson(jsonStr, new TypeToken<T>(){}.getType());
		return result;
	}
	
	public static String objectToJson(Object obj){
		GsonBuilder builder=new GsonBuilder();
		Gson gson=builder.create();
		String result=gson.toJson(obj);
		return result;
	}
	
	public static JsonElement jsonToJsonElement(String jsonStr){
		JsonParser parser=new JsonParser();
		JsonElement je=parser.parse(jsonStr);
		return je;
	}
	
	
}
