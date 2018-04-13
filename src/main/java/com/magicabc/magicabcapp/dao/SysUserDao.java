package com.magicabc.magicabcapp.dao;

import com.magicabc.magicabcapp.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao {
    SysUser findByUseranme(String username);
}
