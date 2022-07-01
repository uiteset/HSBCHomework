package com.yosha.homework.service.token;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.vo.auth.AuthInputVO;
import com.yosha.homework.vo.user.UserInputVO;


public interface TokenInterface {
    // generate one token for a user
    public String generateToken(String userName);
    // check if a token is still valid
    public void checkToken(String token) throws AuthCommonException;
}
