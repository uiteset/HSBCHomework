package com.yosha.homework.service.user.impl;

import com.yosha.homework.dao.UserDAO;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.User;
import com.yosha.homework.service.user.UserInterface;
import com.yosha.homework.vo.user.UserInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserDAO userDao;

    @Override
    public User getUser(String username) throws AuthCommonException {
        User result = userDao.getUser(username);
        if(result == null){
            throw new AuthCommonException("user " + username + " not exists");
        }
        return result;
    }

    @Override
    public void addUser(UserInputVO user) throws AuthCommonException {
        if(userDao.getUser(user.getUsername()) != null){
            throw new AuthCommonException("user name " + user.getUsername() + " already exists.");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userDao.addUser(newUser);
    }

    @Override
    public void deleteUser(String username) throws AuthCommonException {
        if(userDao.getUser(username) == null){
            throw new AuthCommonException("user name " + username + " not exists.");
        }
        userDao.deleteUser(username);
    }
}
