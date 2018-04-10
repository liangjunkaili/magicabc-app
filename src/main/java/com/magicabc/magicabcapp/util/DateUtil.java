package com.magicabc.magicabcapp.util;

import java.util.Date;

public class DateUtil {

    public static Long getTimestamp(){
        return new Date().getTime();
    }
    public static int getTimestamp_10(){
        return (int) (new Date().getTime()/1000);
    }
}
