package com.douqin.chatchitVN.data.models.UI;

import com.douqin.chatchitVN.data.local.room.entity.UserEntity;

import java.util.Date;

public class Friend {
    public int id;
    public User user;
    public Date createAt;

    public Friend( int id,
                         User user,
                         Date createAt){
        this.id = id;
        this.createAt = createAt;
        this.user = user;
    }
}
