package com.yosha.homework.service.user;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.User;
import com.yosha.homework.vo.user.UserInputVO;


public interface UserInterface {
    public User getUser(String username) throws AuthCommonException;
    public void addUser(UserInputVO user) throws AuthCommonException;
    public void deleteUser(String username) throws AuthCommonException;
}
