package com.magicabc.magicabcapp.util;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {

	//把inputStream转化为String
	public static String inputToString(HttpServletRequest request){
		StringBuilder json = new StringBuilder();
	    String line;
	    try {
			request.setCharacterEncoding("utf-8");
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	//json字符串转化为Map
	public static Map<String,Object> getMapFromJson(String jsonString) {     
        JSONObject jsonObject = JSONObject.fromObject(jsonString);     
        Map<String,Object> map = new HashMap<>();
        for(Iterator iter = jsonObject.keys(); iter.hasNext();){     
            String key = (String)iter.next();     
            map.put(key, jsonObject.get(key));     
        }     
        return map;     
    }

    public static JsonConfig getJsonConfig(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object o, String s, Object o1) {
				return "".equals(o1)||o1==null;
			}
		});
		return jsonConfig;
	}
}