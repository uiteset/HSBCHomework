package com.yosha.homework.service.role.impl;

import com.yosha.homework.dao.RoleDAO;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.Role;
import com.yosha.homework.service.role.RoleInterface;
import com.yosha.homework.vo.role.RoleInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleInterface {

    @Autowired
    private RoleDAO roleDao;

    @Override
    public Role getRole(String roleCode) throws AuthCommonException {
        Role result = roleDao.getRole(roleCode);
        if(result == null){
            throw new AuthCommonException("role " + roleCode + " not exists");
        }
        return result;
    }

    @Override
    public void addRole(RoleInputVO role) throws AuthCommonException {
        if(roleDao.getRole(role.getRoleCode()) != null){
            throw new AuthCommonException("role " + role.getRoleCode() + " already exists.");
        }
        Role newRole = new Role();
        newRole.setRoleCode(role.getRoleCode());
        newRole.setRoleName(role.getRoleName());
        roleDao.addRole(newRole);
    }

    @Override
    public void deleteRole(String roleCode) throws AuthCommonException {
        if(roleDao.getRole(roleCode) == null){
            throw new AuthCommonException("role " + roleCode + " not exists.");
        }
        roleDao.deleteRole(roleCode);
    }
}
