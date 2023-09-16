package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.database.room.entity.UserEntity;

import java.util.Date;

public class UserRemoteData {
    public int iduser;

    public String avatar;
    public String phone;
    public String lastname;
    public String address;
    public Date birthday;

    public int gender;

    public String email;

    public String background;
    public String firstname;
    public String bio;
    public String username;

    public UserRemoteData(int iduser, String avatar, String phone, String lastname, String address, Date birthday, int gender, String email, String background, String firstname, String bio, String username) {
        this.iduser = iduser;
        this.avatar = avatar;
        this.phone = phone;
        this.lastname = lastname;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.background = background;
        this.firstname = firstname;
        this.bio = bio;
        this.username = username;
    }

    public UserEntity toUserEntity() {
        return new UserEntity(
                this.iduser,
                this.email,
                this.phone,
                this.lastname,
                this.firstname,
                this.avatar,
                this.address,
                this.birthday,
                this.gender,
                this.background,
                this.bio,
                this.username
        );
    }

}
