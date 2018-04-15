package com.magicabc.magicabcapp.dao;

import com.magicabc.magicabcapp.bean.SysRole;
import com.magicabc.magicabcapp.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {
    SysUser findByUseranme(String username);
    List<SysRole> findRolesById(Long user_id);
}
