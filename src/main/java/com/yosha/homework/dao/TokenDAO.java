package com.yosha.homework.dao;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class TokenDAO {
    // used to store tokens from authentication
    private static final HashMap<String, String> allTokens = new HashMap<String, String>();

    public boolean isTokenExists(String token) {
        return allTokens.containsKey(token);
    }

    public void addToken(String token) throws AuthCommonException {
        if(allTokens.size() >= ConstValues.MAX_TOKEN_STORAGE_SIZE){
            throw new AuthCommonException("token recede storage size, please clear");
        }
        allTokens.putIfAbsent(token, null);
    }

    public void deleteToken(String token){
        allTokens.remove(token);
    }
}
