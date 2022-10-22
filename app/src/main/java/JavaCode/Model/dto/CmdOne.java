package JavaCode.Model.dto;

import JavaCode.Model.User;

public class CmdOne {
    public String avatar;
    public String email;
    public int iduser;
    public String name;
    public String address;
    public String birth;
    public String refreshToken;
    public String accessToken;
    public User getUser(){
        return new User(avatar,email,iduser,name, address, birth);
    }
    public Token getToken(){
        return new Token(refreshToken,accessToken);
    }
}
