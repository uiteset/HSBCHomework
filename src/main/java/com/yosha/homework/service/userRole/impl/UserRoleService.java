package com.yosha.homework.service.userRole.impl;

import com.yosha.homework.dao.RoleDAO;
import com.yosha.homework.dao.UserDAO;
import com.yosha.homework.dao.UserRoleDAO;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.service.token.impl.TokenSimpleService;
import com.yosha.homework.service.userRole.UserRoleInterface;
import com.yosha.homework.vo.user.UserNameAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService implements UserRoleInterface {

    @Autowired
    private UserRoleDAO userRoleDao;
    @Autowired
    private RoleDAO roleDao;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private TokenSimpleService tokenService;

    private void checkUserNameInput(String username) throws AuthCommonException {
        if(userDao.getUser(username) == null){
            throw new AuthCommonException("user " + username + " not exists.");
        }
    }

    private void checkRoleInput(String roleCode) throws AuthCommonException {
        if(roleDao.getRole(roleCode) == null){
            throw new AuthCommonException("role code " + roleCode + " not exists.");
        }
    }

    public List<String> getAllRolesForUser(UserNameAuthInputVO input) throws AuthCommonException {
        tokenService.checkToken(input.getToken());
        this.checkUserNameInput(input.getUsername());
        return this.getRolesForUser(input.getUsername());
    }

    public Boolean checkUserRoleExists(UserRoleAuthInputVO input) throws AuthCommonException {
        tokenService.checkToken(input.getToken());
        this.checkUserNameInput(input.getUsername());
        this.checkRoleInput(input.getRoleCode());
        List<String> roles = this.getRolesForUser(input.getUsername());
        return roles.contains(input.getRoleCode());
    }

    private List<String> getRolesForUser(String username) throws AuthCommonException {
        List<String> roles = userRoleDao.getRolesForUser(username);
        if(roles == null){
            roles = new ArrayList<String>();
        }
        return roles;
    }

    @Override
    public void addUserRole(UserRoleInputVO input) throws AuthCommonException {
        this.checkUserNameInput(input.getUsername());
        this.checkRoleInput(input.getRoleCode());
        List<String> roles = this.getRolesForUser(input.getUsername());
        if(roles.contains(input.getRoleCode())){
            throw new AuthCommonException("user name " + input.getUsername() + " already has role " + input.getRoleCode());
        }
        roles.add(input.getRoleCode());
        userRoleDao.setUserRole(input.getUsername(), roles);
    }

    @Override
    public void deleteUserRole(UserRoleInputVO input) throws AuthCommonException {
        this.checkUserNameInput(input.getUsername());
        this.checkRoleInput(input.getRoleCode());
        List<String> roles = this.getRolesForUser(input.getUsername());
        if(!roles.contains(input.getRoleCode())){
            throw new AuthCommonException("user name " + input.getUsername() + " does not have role " + input.getRoleCode());
        }
        roles.remove(input.getRoleCode());
        userRoleDao.setUserRole(input.getUsername(), roles);
    }
}
