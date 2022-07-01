package com.yosha.homework.service.token.impl;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.dao.TokenDAO;
import com.yosha.homework.dao.UserDAO;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.service.token.TokenInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenSimpleService implements TokenInterface {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private TokenDAO tokenDao;

    // simplest way to generate a token
    // method: name + delimiter + milli timestamp, then reverse
    @Override
    public String generateToken(String userName) {
        StringBuilder result = new StringBuilder(userName);
        result.append(ConstValues.TOKEN_DELIMITER);
        result.append(System.currentTimeMillis());
        return result.reverse().toString();
    }

    // check if a token is valid based on how generateToken func generates the token
    @Override
    public void checkToken(String token) throws AuthCommonException {
        if(!tokenDao.isTokenExists(token)){
            throw new AuthCommonException("无效token，请重新auth");
        }
        String[] result = new StringBuilder(token).reverse().toString().split(ConstValues.TOKEN_DELIMITER);
        if(result.length != 2){
            throw new AuthCommonException("无效token，请重新auth");
        }
        if(userDao.getUser(result[0]) == null){
            throw new AuthCommonException("无效token，请重新auth");
        }
        long now = System.currentTimeMillis();
        if(now - Long.parseLong(result[1]) >= ConstValues.TOKEN_DURATION){
            throw new AuthCommonException("token过期，请重新auth");
        }
    }
}
