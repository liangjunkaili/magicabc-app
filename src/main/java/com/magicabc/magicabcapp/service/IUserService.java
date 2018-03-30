package com.magicabc.magicabcapp.service;

import com.magicabc.magicabcapp.bean.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<User> queryUsers();
    int addUser(Map<String,Object> param);
    int updateUser(Map<String,Object> param);
    int deleteUser(String userphone);
}
