package com.yosha.homework.service.auth.impl;

import com.yosha.homework.dao.TokenDAO;
import com.yosha.homework.dao.UserDAO;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.User;
import com.yosha.homework.service.auth.AuthInterface;
import com.yosha.homework.service.token.impl.TokenSimpleService;
import com.yosha.homework.vo.auth.AuthInputVO;
import com.yosha.homework.vo.user.UserInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthInterface {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private TokenDAO tokenDao;
    @Autowired
    private TokenSimpleService token;

    @Override
    public String authenticate(UserInputVO input) throws AuthCommonException {
        User user = userDao.getUser(input.getUsername());
        if(user == null){
            throw new AuthCommonException("user name " + input.getUsername() + " not exists.");
        }
        if(!user.getPassword().equals(input.getPassword())){
            throw new AuthCommonException("username / password wrong");
        }
        String tokenResult = token.generateToken(input.getUsername());
        tokenDao.addToken(tokenResult);
        return tokenResult;
    }

    @Override
    public void invalidate(AuthInputVO input) throws AuthCommonException {
        if(!tokenDao.isTokenExists(input.getToken())){
            throw new AuthCommonException("token invalid");
        }
        tokenDao.deleteToken(input.getToken());
    }
}
