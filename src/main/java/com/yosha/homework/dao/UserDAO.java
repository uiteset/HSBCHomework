package com.yosha.homework.dao;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDAO {
    //used to sotre users
    private static final HashMap<String, User> allUsers = new HashMap<String, User>();

    public User getUser(String username){
        return allUsers.get(username);
    }

    public void addUser(User user) throws AuthCommonException {
        if(allUsers.size() >= ConstValues.MAX_USER_STORAGE_SIZE){
            throw new AuthCommonException("user recede storage size, please clear");
        }
        allUsers.putIfAbsent(user.getUsername(), user);
    }

    public void deleteUser(String userName){
        allUsers.remove(userName);
    }
}
