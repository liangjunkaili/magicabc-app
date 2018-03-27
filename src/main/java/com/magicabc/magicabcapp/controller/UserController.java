package com.magicabc.magicabcapp.controller;

import com.magicabc.magicabcapp.bean.User;
import com.magicabc.magicabcapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping("/queryUsers")
    public void queryUsers(){
        List<User> userList = userService.queryUsers();
        for (User u:userList){
            System.out.println(u.getUsername()+"----"+u.getUserphone());
        }
    }

    @PostMapping("/addUser")
    public void addUser(){
        System.out.println("add User...");
    }
}
