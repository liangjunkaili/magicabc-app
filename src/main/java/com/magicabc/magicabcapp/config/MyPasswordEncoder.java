package com.magicabc.magicabcapp.config;

import com.magicabc.magicabcapp.util.EncryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence arg0) {
        return EncryptUtil.Encrypt((String) arg0,"MD5");
    }

    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        return arg1.equals(EncryptUtil.Encrypt((String) arg0,"MD5"));
    }

}