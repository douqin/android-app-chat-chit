package com.douqin.chatchitVN.data.repositories.login.dtos;

public class Token {
    public String accessToken;
    public String refreshToken;

    Token(
            String accessToken,
            String refreshToken
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
