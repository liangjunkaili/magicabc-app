package com.magicabc.magicabcapp.dao;

import com.magicabc.magicabcapp.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {
    List<User> queryUsers();
    int addUser(Map<String,Object> param);
    int updateUser(Map<String,Object> param);
    int deleteUser(String userphone);
}
