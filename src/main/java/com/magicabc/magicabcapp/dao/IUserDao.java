package com.magicabc.magicabcapp.dao;

import com.magicabc.magicabcapp.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {
    public List<User> queryUsers();
}
