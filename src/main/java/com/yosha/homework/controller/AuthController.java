package com.yosha.homework.controller;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.service.auth.impl.AuthService;
import com.yosha.homework.util.CommonResponse;
import com.yosha.homework.vo.auth.AuthGetRoleInputVO;
import com.yosha.homework.vo.auth.AuthInputVO;
import com.yosha.homework.vo.user.UserInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/doAuth")
    public CommonResponse auth(@RequestBody UserInputVO input) {
        try{
            input.selfCheck();
            return CommonResponse.success(authService.authenticate(input));
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/invalidate")
    public CommonResponse invalidate(@RequestBody AuthInputVO input) {
        try{
            input.selfCheck();
            authService.invalidate(input);
            return CommonResponse.success(null);
        }
        catch (AuthCommonException e){
            return CommonResponse.fail(e.getMessage());
        }
    }
}
