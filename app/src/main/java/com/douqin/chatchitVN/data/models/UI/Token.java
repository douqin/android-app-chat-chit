package com.douqin.chatchitVN.data.models.UI;

public class Token {
    public String refreshToken;
    public String accessToken;

    public Token(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
