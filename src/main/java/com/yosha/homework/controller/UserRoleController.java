package com.yosha.homework.controller;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.service.userRole.impl.UserRoleService;
import com.yosha.homework.util.CommonResponse;
import com.yosha.homework.vo.user.UserNameAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/add")
    public CommonResponse add(@RequestBody UserRoleInputVO input) {
        try{
            input.selfCheck();
            userRoleService.addUserRole(input);
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody UserRoleInputVO input) {
        try{
            input.selfCheck();
            userRoleService.deleteUserRole(input);
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/check")
    public CommonResponse checkUserRoleExists(@RequestBody UserRoleAuthInputVO input) {
        try{
            input.selfCheck();
            return CommonResponse.success(userRoleService.checkUserRoleExists(input));
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/allRoles")
    public CommonResponse getAllRoles(@RequestBody UserNameAuthInputVO input) {
        try{
            input.selfCheck();
            return CommonResponse.success(userRoleService.getAllRolesForUser(input));
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }
}
