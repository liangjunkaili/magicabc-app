package com.magicabc.magicabcapp.bean.ticket;

public class Item {
    private String card_id;
    private String encrypt_code;
    private String reimburse_status;

    public String getReimburse_status() {
        return reimburse_status;
    }

    public void setReimburse_status(String reimburse_status) {
        this.reimburse_status = reimburse_status;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getEncrypt_code() {
        return encrypt_code;
    }

    public void setEncrypt_code(String encrypt_code) {
        this.encrypt_code = encrypt_code;
    }
}
