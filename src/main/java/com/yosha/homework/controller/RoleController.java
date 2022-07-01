package com.yosha.homework.controller;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.util.CommonResponse;
import com.yosha.homework.service.role.impl.RoleService;
import com.yosha.homework.vo.role.RoleInputVO;
import com.yosha.homework.vo.role.RoleCodeInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/get")
    public CommonResponse get(@RequestBody RoleCodeInputVO input) {
        try{
            input.selfCheck();
            return CommonResponse.success(roleService.getRole(input.getRoleCode()));
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/add")
    public CommonResponse add(@RequestBody RoleInputVO inputRole) {
        try{
            inputRole.selfCheck();
            roleService.addRole(inputRole);
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody RoleCodeInputVO input) {
        try{
            input.selfCheck();
            roleService.deleteRole(input.getRoleCode());
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }
}
