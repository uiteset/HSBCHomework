package com.yosha.homework.vo.user;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.util.InputChecker;
import lombok.Data;

@Data
public class UserInputVO {
    private String username;
    private String password;

    public void selfCheck() throws AuthCommonException {
        InputChecker.stringCheck("username", username, true, true,
                ConstValues.INPUT_STRING_USER_NAME_MAX_LENGTH);
        InputChecker.stringCheck("password", password, true, true,
                ConstValues.INPUT_STRING_PASSWORD_MAX_LENGTH);
    }
}
