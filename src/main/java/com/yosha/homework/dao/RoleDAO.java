package com.yosha.homework.dao;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class RoleDAO {
    //used to store roles
    private static final HashMap<String, Role> allRoles = new HashMap<String, Role>();

    public Role getRole(String roleCode){
        return allRoles.get(roleCode);
    }

    public void addRole(Role role) throws AuthCommonException {
        if(allRoles.size() >= ConstValues.MAX_ROLE_STORAGE_SIZE){
            throw new AuthCommonException("role recede storage size, please clear");
        }
        allRoles.putIfAbsent(role.getRoleCode(), role);
    }

    public void deleteRole(String roleCode){
        allRoles.remove(roleCode);
    }
}
