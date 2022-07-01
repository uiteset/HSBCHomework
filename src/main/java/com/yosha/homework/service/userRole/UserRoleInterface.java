package com.yosha.homework.service.userRole;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.User;
import com.yosha.homework.vo.user.UserInputVO;
import com.yosha.homework.vo.user.UserNameAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleInputVO;

import java.util.List;


public interface UserRoleInterface {
    // get a roles list for one user(string list)
    public List<String> getAllRolesForUser(UserNameAuthInputVO input) throws AuthCommonException;
    // check if a user has a role
    public Boolean checkUserRoleExists(UserRoleAuthInputVO input) throws AuthCommonException;
    // add a role to a user
    public void addUserRole(UserRoleInputVO input) throws AuthCommonException;
    // remove a role from a user
    public void deleteUserRole(UserRoleInputVO input) throws AuthCommonException;
}
