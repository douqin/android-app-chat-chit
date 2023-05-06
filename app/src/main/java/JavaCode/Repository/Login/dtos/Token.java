package JavaCode.Repository.Login.dtos;

public class Token {
    String accessToken;
    String refreshToken;

    Token(
            String accessToken,
            String refreshToken
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
