package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.local.room.entity.FriendEntity;
import com.douqin.chatchitVN.data.local.room.entity.RequesterEntity;

import java.util.Date;

public class InviteRemoteData {
    public int id;
    public Date createAt;

    public UserRemoteData user;

     public FriendEntity toFriendEntity(){
        return new FriendEntity(
                this.id, this.user.toUserEntity(), this.createAt
        );
    }

    public RequesterEntity toRequesterEntity(){
        return new RequesterEntity(
                this.id, this.user.toUserEntity(), this.createAt
        );
    }
}
