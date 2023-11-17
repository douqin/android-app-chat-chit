package com.douqin.chatchitVN.data.local.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.network.apis.RemoteData.UserRemoteData;

import java.util.Date;

@Entity(tableName = "friend")
public class RequesterEntity {

    @PrimaryKey
    public int id;
    public UserEntity userEntity;
    public Date createAt;
    public RequesterEntity(   int id,
                              UserEntity user,
                           Date createAt){
        this.id = id;
        this.createAt = createAt;
        this.userEntity = user;
    }
}