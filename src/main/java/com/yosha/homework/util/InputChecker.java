package com.yosha.homework.util;

import com.yosha.homework.exception.AuthCommonException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

    // check String attribute input value
    // input:
    //   attrName: attribute name
    //   value: attribute value to check
    //   nullCheck: need null value check
    //   strickValue: need pattern check, only allow specific characters
    //   maxLenght: -1 not check, 0 and positive check max length
    public static void stringCheck(String attrName, String value, boolean nullCheck, boolean strickValue,
                                   int maxLength) throws AuthCommonException {
        if(nullCheck && value == null){
            throw new AuthCommonException(attrName + " can not be null");
        }
        if(maxLength >= 0 && value.length() > maxLength){
            throw new AuthCommonException(attrName + " length can not be bigger than " + maxLength);
        }
        if(strickValue){
            Pattern pattern = Pattern.compile("^[0-9a-zA-Z_-]*$");
            Matcher matcher = pattern.matcher(value);
            if(!matcher.matches()) {
                throw new AuthCommonException(attrName + " can only contain letter, number, dash");
            }
        }
    }
}
