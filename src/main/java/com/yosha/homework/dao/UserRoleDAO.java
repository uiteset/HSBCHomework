package com.yosha.homework.dao;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserRoleDAO {
    //used to store user-role relationships
    private static final HashMap<String, List<String>> allUserRoles = new HashMap<String, List<String>>();

    public List<String> getRolesForUser(String username) throws AuthCommonException {
        if(allUserRoles.size() >= ConstValues.MAX_USER_ROLE_STORAGE_SIZE){
            throw new AuthCommonException("user role recede storage size, please clear");
        }
        return allUserRoles.get(username);
    }

    public void setUserRole(String username, List<String> roleCodes){
        allUserRoles.put(username, roleCodes);
    }
}
