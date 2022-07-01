package com.yosha.homework.vo.auth;

import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.util.InputChecker;
import lombok.Data;

@Data
public class AuthInputVO {
    private String token;

    public void selfCheck() throws AuthCommonException {
        InputChecker.stringCheck("token", token, true, false, -1);
    }
}
