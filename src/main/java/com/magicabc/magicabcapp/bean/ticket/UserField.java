package com.magicabc.magicabcapp.bean.ticket;

import java.util.List;

public class UserField {
    private int show_title;
    private int show_phone;
    private int show_email;
    private int require_phone;
    private int require_email;
    private List<CustomField> custom_field;

    public int getShow_title() {
        return show_title;
    }

    public void setShow_title(int show_title) {
        this.show_title = show_title;
    }

    public int getShow_phone() {
        return show_phone;
    }

    public void setShow_phone(int show_phone) {
        this.show_phone = show_phone;
    }

    public int getShow_email() {
        return show_email;
    }

    public void setShow_email(int show_email) {
        this.show_email = show_email;
    }

    public int getRequire_phone() {
        return require_phone;
    }

    public void setRequire_phone(int require_phone) {
        this.require_phone = require_phone;
    }

    public int getRequire_email() {
        return require_email;
    }

    public void setRequire_email(int require_email) {
        this.require_email = require_email;
    }

    public List<CustomField> getCustom_field() {
        return custom_field;
    }

    public void setCustom_field(List<CustomField> custom_field) {
        this.custom_field = custom_field;
    }
}
