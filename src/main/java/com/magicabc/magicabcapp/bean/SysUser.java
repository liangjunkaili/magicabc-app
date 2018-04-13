package com.magicabc.magicabcapp.bean;

import java.util.List;

public class SysUser {

    private Long id;
    private String username;
    private String password;
//    private List<SysRole> roles;
//
//    public List<SysRole> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<SysRole> roles) {
//        this.roles = roles;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
