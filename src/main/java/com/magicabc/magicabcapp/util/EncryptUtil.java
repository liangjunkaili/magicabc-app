package com.magicabc.magicabcapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class EncryptUtil {
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md;
        String strDes;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }
    //字典序
    public static String sortForMap(Map<String,String> param){
        StringBuilder result = new StringBuilder();
        Set<String> sets = param.keySet();
        List<String> list = new ArrayList<>(sets);
        Collections.sort(list);
        for (String s : list){
            result.append(s).append("=").append(param.get(s)).append("&");
        }
        return result.toString().substring(0,result.toString().length()-1);
    }
    //统一的微信支付 Sign 签名生成方法
    public static String getSign(Map<String,String> param){
        String tmp = EncryptUtil.sortForMap(param);
        String signTmp = tmp + "&key="+WXConfiguration.key;
        String sign = EncryptUtil.Encrypt(signTmp,"MD5").toUpperCase();
        return sign;
    }
}
