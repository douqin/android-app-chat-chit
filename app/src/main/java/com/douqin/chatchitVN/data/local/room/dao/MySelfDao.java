package com.douqin.chatchitVN.data.local.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.local.room.entity.UserEntity;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MySelfDao {
    @Query("SELECT * FROM user WHERE user.isMe = 1 LIMIT 1 ")
    Observable<UserEntity> getMe();

    @Query("SELECT * FROM user WHERE user.isMe = 1 LIMIT 1 ")
    UserEntity getMe2();

    @Query("SELECT COUNT(*) FROM user WHERE user.isMe = 1")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setMe(UserEntity user);
}
