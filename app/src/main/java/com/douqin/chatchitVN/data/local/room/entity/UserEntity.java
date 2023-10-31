package com.douqin.chatchitVN.data.local.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.UI.User;

import java.util.Date;

@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey
    @ColumnInfo(name = "iduser")
    public int idUser;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "phone")
    public String phone;


    @ColumnInfo(name = "lastname")
    public String lastName;

    @ColumnInfo(name = "firstname")
    public String firstName;

    @ColumnInfo(name = "isme")
    public boolean isMe = false;

    public String avatar;
    public String address;
    public Date birthday;
    public int gender;

    public String background;
    public String bio;
    public String username;

    public UserEntity(int idUser, String email, String phone, String lastName, String firstName, String avatar, String address, Date birthday, int gender, String background, String bio, String username) {
        this.idUser = idUser;
        this.email = email;
        this.phone = phone;
        this.lastName = lastName;
        this.firstName = firstName;
        this.avatar = avatar;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.background = background;
        this.bio = bio;
        this.username = username;
    }

    public User toModel() {
        return new User(
                this.idUser,
                this.avatar,
                this.phone,
                this.lastName,
                this.address,
                this.birthday,
                this.gender,
                this.email,
                this.background,
                this.firstName,
                this.bio,
                this.username
        );
    }
}
