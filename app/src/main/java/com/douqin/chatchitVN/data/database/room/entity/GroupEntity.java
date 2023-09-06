package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.GroupChat;

import java.util.Date;

@Entity(tableName = "group")
public class GroupEntity {

    @ColumnInfo(name = "idgroup")
    @PrimaryKey
    public int idGroup;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "createat")
    public Date createdAt;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "avatar")
    public String avatar;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "role")
    public String role;


    public GroupEntity(int idGroup, String name, Date createdAt, int status, String avatar, int type, String link, String role) {
        this.idGroup = idGroup;
        this.name = name;
        this.createdAt = createdAt;
        this.status = status;
        this.avatar = avatar;
        this.type = type;
        this.link = link;
        this.role = role;
    }

    public GroupChat toModel() {
        return new GroupChat(
                this.idGroup,
                this.name,
                this.status,
                this.avatar,
                this.link,
                this.type,
                this.role,
                this.createdAt
        );
    }
}

