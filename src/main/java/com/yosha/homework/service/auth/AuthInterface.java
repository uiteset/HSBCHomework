package com.yosha.homework.service.auth;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.vo.auth.AuthInputVO;
import com.yosha.homework.vo.user.UserInputVO;


public interface AuthInterface {

    // give token by username and password
    public String authenticate(UserInputVO input) throws AuthCommonException;
    // disable one token
    public void invalidate(AuthInputVO input) throws AuthCommonException;
}
