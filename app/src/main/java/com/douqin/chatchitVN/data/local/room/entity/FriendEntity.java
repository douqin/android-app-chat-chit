package com.douqin.chatchitVN.data.local.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.network.apis.RemoteData.UserRemoteData;

import java.util.Date;

@Entity(tableName = "friend")
public class FriendEntity {

    @PrimaryKey
    public int id;
    public UserEntity userEntity;
    public Date createAt;

    public FriendEntity( int id,
     UserEntity user,
     Date createAt){
        this.id = id;
        this.createAt = createAt;
        this.userEntity = user;
    }
}
