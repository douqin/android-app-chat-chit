package JavaCode.Model.dto;

public class Token {
    public String refreshToken;
    public String accessToken;

    public Token(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
