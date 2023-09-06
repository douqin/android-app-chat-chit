package com.douqin.chatchitVN.data.apis.dtos;

import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GroupChatDTO {
    @SerializedName("idgroup")
    public int idgroup;

    @SerializedName("name")
    public String name;

    @SerializedName("avatar")
    public String avatar;

    @SerializedName("status")
    public int status;

    @SerializedName("createAt")
    public Date createAt;

    @SerializedName("type")
    public int type;

    @SerializedName("role")
    public String role;

    @SerializedName("link")
    public String link;

    public GroupChatDTO(int idgroup, String name, String avatar, int status, Date createAt, int type, String link, String role) {
        this.idgroup = idgroup;
        this.name = name;
        this.avatar = avatar;
        this.status = status;
        this.createAt = createAt;
        this.type = type;
        this.link = link;
        this.role = role;
    }

    public GroupEntity toEntity() {
        return new GroupEntity(
                this.idgroup,
                this.name,
                this.createAt,
                this.status,
                this.avatar,
                this.type,
                this.link,
                this.role
        );
    }
}


