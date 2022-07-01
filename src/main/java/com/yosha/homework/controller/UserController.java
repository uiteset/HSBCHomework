package com.yosha.homework.controller;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.util.CommonResponse;
import com.yosha.homework.service.user.impl.UserService;
import com.yosha.homework.vo.user.UserInputVO;
import com.yosha.homework.vo.user.UserNameInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/get")
    public CommonResponse get(@RequestBody UserNameInputVO input) {
        try{
            input.selfCheck();
            return CommonResponse.success(userService.getUser(input.getUsername()));
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/add")
    public CommonResponse add(@RequestBody UserInputVO inputUser) {
        try{
            inputUser.selfCheck();
            userService.addUser(inputUser);
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody UserNameInputVO input) {
        try{
            input.selfCheck();
            userService.deleteUser(input.getUsername());
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }
}
