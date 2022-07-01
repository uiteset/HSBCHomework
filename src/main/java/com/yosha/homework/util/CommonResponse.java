package com.yosha.homework.util;

import com.yosha.homework.conf.ConstValues;
import lombok.Data;

@Data
public class CommonResponse {
    private int errorCode = ConstValues.SUCCESS;
    private String errorMessage = ConstValues.SUCCESS_LABEL;
    private Object data;

    public CommonResponse(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CommonResponse(Object data){
        this.data = data;
    }

    //generate success web response
    public static CommonResponse success(Object data){
        return new CommonResponse(data);
    }

    //generate failure web response
    public static CommonResponse fail(String message){
        return new CommonResponse(ConstValues.FAILURE, message);
    }
}
