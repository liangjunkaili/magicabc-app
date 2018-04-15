package com.magicabc.magicabcapp.service.impl;

import com.magicabc.magicabcapp.bean.SysRole;
import com.magicabc.magicabcapp.bean.SysUser;
import com.magicabc.magicabcapp.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService{
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = sysUserDao.findByUseranme(s);
        if (user==null){
            System.out.println("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        List<SysRole> roles = sysUserDao.findRolesById(user.getId());
        for(SysRole role:roles)
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}
