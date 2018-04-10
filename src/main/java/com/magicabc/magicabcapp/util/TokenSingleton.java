package com.magicabc.magicabcapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenSingleton {
    private static Logger logger = LoggerFactory.getLogger(TokenSingleton.class);
    private Map<String, String> map = new HashMap<>();
    private TokenSingleton() {
    }
    private static TokenSingleton single = null;
    public static TokenSingleton getInstance() {
        if (single == null) {
            single = new TokenSingleton();
        }
        return single;
    }
    public Map<String, String> getMap() {
        String time = map.get("time");
        String accessToken = map.get(ConstantUtil.access_token);
        Long nowDate = new Date().getTime();
        if (accessToken != null && time != null && nowDate - Long.parseLong(time) < 7000 * 1000) {
          logger.info("accessToken存在，且没有超时 ， 返回单例");
        } else {
            logger.info("accessToken 超时 ， 或者不存在 ， 重新获取");
            String access_token=getAccess_token();
            String jsapi_ticket = getJsapi_ticket(access_token);
            String api_ticket = getApi_ticket(access_token);
            map.put("time", nowDate + "");
            map.put(ConstantUtil.access_token, access_token);
            map.put(ConstantUtil.jsapi_ticket, jsapi_ticket);
            map.put(ConstantUtil.api_ticket, api_ticket);
        }
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    private static String getAccess_token(){
        String str = HttpRequest.sendGet(WXConfiguration.ACCESS_TOKEN);
        Map<String,Object> map = JsonUtil.getMapFromJson(str);
        return (String) map.get(ConstantUtil.access_token);
    }
    private static String getJsapi_ticket(String access_token){
        String str = HttpRequest.sendGet(WXConfiguration.jsapi_ticket.replace("ACCESS_TOKEN",access_token));
        Map<String,Object> map = JsonUtil.getMapFromJson(str);
        return (String) map.get("ticket");
    }
    private static String getApi_ticket(String access_token){
        String str = HttpRequest.sendGet(WXConfiguration.api_ticket.replace("ACCESS_TOKEN",access_token));
        Map<String,Object> map = JsonUtil.getMapFromJson(str);
        return (String) map.get("ticket");
    }
}