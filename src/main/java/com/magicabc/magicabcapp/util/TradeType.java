package com.magicabc.magicabcapp.util;

public enum TradeType {
    JSAPI("公众号支付"),
    NATIVE("扫码支付"),
    APP("APP支付"),
    MWEB("H5支付");
    TradeType(String comment) {
    }
}
