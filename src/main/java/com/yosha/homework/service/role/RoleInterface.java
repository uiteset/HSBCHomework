package com.yosha.homework.service.role;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.model.Role;
import com.yosha.homework.vo.role.RoleInputVO;


public interface RoleInterface {
    public Role getRole(String roleCode) throws AuthCommonException;
    public void addRole(RoleInputVO role) throws AuthCommonException;
    public void deleteRole(String roleCode) throws AuthCommonException;
}
