package com.yosha.homework.vo.user;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.util.InputChecker;
import lombok.Data;

@Data
public class UserNameAuthInputVO {
    private String username;
    private String token;

    public void selfCheck() throws AuthCommonException {
        InputChecker.stringCheck("username", username, true, true,
                ConstValues.INPUT_STRING_USER_NAME_MAX_LENGTH);
        InputChecker.stringCheck("token", token, true, false, -1);
    }
}
