package com.magicabc.magicabcapp.bean.ticket;

public class AuthUrl {
    private String s_pappid;
    private String order_id;
    private String source;
    private String redirect_url;
    private String ticket;
    private int money;
    private int timestamp;
    private int type;

    public String getS_pappid() {
        return s_pappid;
    }

    public void setS_pappid(String s_pappid) {
        this.s_pappid = s_pappid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
