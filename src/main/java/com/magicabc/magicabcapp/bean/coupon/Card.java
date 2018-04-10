package com.magicabc.magicabcapp.bean.coupon;

public class Card {
    private String order_id;
    private String card_id;
    private String code;
    private String reimburse_status;
    private String appid;
    private CardExt card_ext;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReimburse_status() {
        return reimburse_status;
    }

    public void setReimburse_status(String reimburse_status) {
        this.reimburse_status = reimburse_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public CardExt getCard_ext() {
        return card_ext;
    }

    public void setCard_ext(CardExt card_ext) {
        this.card_ext = card_ext;
    }
}
