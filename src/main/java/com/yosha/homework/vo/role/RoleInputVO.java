package com.yosha.homework.vo.role;

import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.exception.AuthCommonException;
import com.yosha.homework.util.InputChecker;
import lombok.Data;

@Data
public class RoleInputVO {
    private String roleCode;
    private String RoleName;

    public void selfCheck() throws AuthCommonException {
        InputChecker.stringCheck("roleCode", roleCode, true, true,
                ConstValues.INPUT_STRING_ROLE_MAX_LENGTH);
        InputChecker.stringCheck("RoleName", RoleName, true, true,
                ConstValues.INPUT_STRING_ROLE_MAX_LENGTH);
    }
}
