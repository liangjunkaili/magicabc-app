package com.magicabc.magicabcapp.bean.coupon;

public class InvoiceInfo {
    private BaseInfo base_info;
    private String payee;
    private String type;

    public BaseInfo getBase_info() {
        return base_info;
    }

    public void setBase_info(BaseInfo base_info) {
        this.base_info = base_info;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
