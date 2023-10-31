package com.douqin.chatchitVN.data.local.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.UI.Member;

import java.util.Date;

@Entity(tableName = "member")
public class MemberEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "idgroup")
    public int idGroup;

    @ColumnInfo(name = "iduser")
    public int idUser;

    @ColumnInfo(name = "lastview")
    public Date lastView;

    @ColumnInfo(name = "position")
    public int position;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "timejoin")
    public Date timeJoin;

    public MemberEntity(int id, int idGroup, int idUser, Date lastView, int position, int status, Date timeJoin) {
        this.id = id;
        this.idGroup = idGroup;
        this.idUser = idUser;
        this.lastView = lastView;
        this.position = position;
        this.status = status;
        this.timeJoin = timeJoin;
    }

    public Member toMemberModel() {
        return new Member(this.id,
                this.idGroup,
                this.idUser,
                this.lastView,
                this.position,
                this.status,
                this.timeJoin);
    }
}

