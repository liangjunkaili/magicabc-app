package com.magicabc.magicabcapp.util;

import java.util.Map;

public class WxPayUtil {

    //attach total_fee ip body openid
    public static String get_prepay_id(Map<String,String> param) throws Exception {
        String nonce_str = StringUtil.getGlobalId();
        String out_trade_no = StringUtil.getGlobalId();
        param.put("appid",WXConfiguration.APPID);
        param.put("mch_id",WXConfiguration.mch_id);
        param.put("device_info",WXConfiguration.device_info);
        param.put("nonce_str",nonce_str);
        param.put("out_trade_no",out_trade_no);
        param.put("notify_url",WXConfiguration.wxpay_success);
        param.put("trade_type",TradeType.JSAPI.toString());
        String tmp = EncryptUtil.sortForMap(param);
        String signTmp = tmp + "&key="+WXConfiguration.key;
        String sign = EncryptUtil.Encrypt(signTmp,"MD5").toUpperCase();
        String xmlStr = "<xml>"+
                "<appid>"+WXConfiguration.APPID+"</appid>"+
                "<attach>"+param.get("attach")+"</attach>"+
                "<body>"+param.get("body")+"</body>"+
                "<device_info>"+WXConfiguration.device_info+"</device_info>"+
                "<mch_id>"+WXConfiguration.mch_id+"</mch_id>"+
                "<nonce_str>"+nonce_str+"</nonce_str>"+
                "<notify_url>"+WXConfiguration.wxpay_success+"</notify_url>"+
                "<openid>"+param.get("openid")+"</openid>"+
                "<out_trade_no>"+out_trade_no+"</out_trade_no>"+
                "<spbill_create_ip>"+param.get("ip")+"</spbill_create_ip>"+
                "<total_fee>"+param.get("total_fee")+"</total_fee>"+
                "<trade_type>"+TradeType.JSAPI.toString()+"</trade_type>"+
                "<sign>"+sign+"</sign>"+
                "</xml>";
        String result = HttpRequest.sendPost(WXConfiguration.unifiedorder,xmlStr);
        return XmlUtil.xmlStrToMap(result).get("prepay_id");
    }

    //attach total_fee ip body scene_info
    public static String get_mweb_url(Map<String,String> param) throws Exception {
        String nonce_str = StringUtil.getGlobalId();
        String out_trade_no = StringUtil.getGlobalId();
        param.put("appid",WXConfiguration.APPID);
        param.put("mch_id",WXConfiguration.mch_id);
        param.put("sub_mch_id",WXConfiguration.sub_mch_id);
        param.put("nonce_str",nonce_str);
        param.put("out_trade_no",out_trade_no);
        param.put("notify_url",WXConfiguration.wxpay_success);
        param.put("trade_type",TradeType.MWEB.toString());
        String tmp = EncryptUtil.sortForMap(param);
        String signTmp = tmp + "&key="+WXConfiguration.key;
        String sign = EncryptUtil.Encrypt(signTmp,"MD5").toUpperCase();
        String xmlStr = "<xml>"+
                "<appid>"+WXConfiguration.APPID+"</appid>"+
                "<attach>"+param.get("attach")+"</attach>"+
                "<body>"+param.get("body")+"</body>"+
                "<mch_id>"+WXConfiguration.mch_id+"</mch_id>"+
                "<sub_mch_id>"+WXConfiguration.sub_mch_id+"</sub_mch_id>"+
                "<nonce_str>"+nonce_str+"</nonce_str>"+
                "<notify_url>"+WXConfiguration.wxpay_success+"</notify_url>"+
                "<scene_info>"+param.get("scene_info")+"</scene_info>"+
                "<out_trade_no>"+out_trade_no+"</out_trade_no>"+
                "<spbill_create_ip>"+param.get("ip")+"</spbill_create_ip>"+
                "<total_fee>"+param.get("total_fee")+"</total_fee>"+
                "<trade_type>"+TradeType.MWEB.toString()+"</trade_type>"+
                "<sign>"+sign+"</sign>"+
                "</xml>";
        String result = HttpRequest.sendPost(WXConfiguration.unifiedorder,xmlStr);
        return XmlUtil.xmlStrToMap(result).get("mweb_url");
    }
    //attach total_fee ip body product_id
    public static String get_code_url(Map<String,String> param) throws Exception {
        String nonce_str = StringUtil.getGlobalId();
        String out_trade_no = StringUtil.getGlobalId();
        param.put("appid",WXConfiguration.APPID);
        param.put("mch_id",WXConfiguration.mch_id);
        param.put("sub_mch_id",WXConfiguration.sub_mch_id);
        param.put("nonce_str",nonce_str);
        param.put("out_trade_no",out_trade_no);
        param.put("notify_url",WXConfiguration.wxpay_success);
        param.put("trade_type",TradeType.NATIVE.toString());
        String tmp = EncryptUtil.sortForMap(param);
        String signTmp = tmp + "&key="+WXConfiguration.key;
        String sign = EncryptUtil.Encrypt(signTmp,"MD5").toUpperCase();
        String xmlStr = "<xml>"+
                "<appid>"+WXConfiguration.APPID+"</appid>"+
                "<attach>"+param.get("attach")+"</attach>"+
                "<body>"+param.get("body")+"</body>"+
                "<mch_id>"+WXConfiguration.mch_id+"</mch_id>"+
                "<sub_mch_id>"+WXConfiguration.sub_mch_id+"</sub_mch_id>"+
                "<nonce_str>"+nonce_str+"</nonce_str>"+
                "<notify_url>"+WXConfiguration.wxpay_success+"</notify_url>"+
                "<product_id>"+param.get("product_id")+"</product_id>"+
                "<out_trade_no>"+out_trade_no+"</out_trade_no>"+
                "<spbill_create_ip>"+param.get("ip")+"</spbill_create_ip>"+
                "<total_fee>"+param.get("total_fee")+"</total_fee>"+
                "<trade_type>"+TradeType.NATIVE.toString()+"</trade_type>"+
                "<sign>"+sign+"</sign>"+
                "</xml>";
        String result = HttpRequest.sendPost(WXConfiguration.unifiedorder,xmlStr);
        return XmlUtil.xmlStrToMap(result).get("code_url");
    }
}
