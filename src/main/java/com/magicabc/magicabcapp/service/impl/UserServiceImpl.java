package com.magicabc.magicabcapp.service.impl;

import com.magicabc.magicabcapp.bean.User;
import com.magicabc.magicabcapp.dao.IUserDao;
import com.magicabc.magicabcapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserDao userDao;
    @Override
    public List<User> queryUsers() {
        return userDao.queryUsers();
    }
}
