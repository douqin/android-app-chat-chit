package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.local.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.local.room.entity.UserEntity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MemberRemoteData {
    @SerializedName("iduser")
    public int idUser;

    @SerializedName("email")
    public String email;

    @SerializedName("phone")
    public String phone;

    @SerializedName("password")
    public String password;

    @SerializedName("lastname")
    public String lastName;

    @SerializedName("birthday")
    public Date birthday;

    @SerializedName("gender")
    public int gender;

    @SerializedName("avatar")
    public String avatar;

    @SerializedName("background")
    public String background;

    @SerializedName("firstname")
    public String firstName;

    @SerializedName("bio")
    public String bio;

    @SerializedName("username")
    public String username;

    @SerializedName("id")
    public int id;

    @SerializedName("idgroup")
    public int idGroup;

    @SerializedName("lastview")
    public Date lastView;

    @SerializedName("position")
    public int position;

    @SerializedName("status")
    public int status;

    @SerializedName("timejoin")
    public Date timeJoin;

    public String address;

    public MemberEntity getMemberEntity() {
        return new MemberEntity(
                this.id,
                this.idGroup,
                this.idUser,
                this.lastView,
                this.position,
                this.status,
                this.timeJoin
        );
    }

    public UserEntity getInformationUserEntity() {
        return new UserEntity(
                this.idUser,
                this.email,
                this.phone,
                this.lastName,
                this.firstName,
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
