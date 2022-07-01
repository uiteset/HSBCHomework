package com.yosha.homework.vo.token;

import lombok.Data;

@Data
public class Token {
    private String username;
    private long timestamp;

    public Token(String username, long timestamp){
        this.username = username;
        this.timestamp = timestamp;
    }
}
