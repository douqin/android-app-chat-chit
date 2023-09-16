package com.douqin.chatchitVN.data.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.database.room.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE iduser = :iduser")
    UserEntity getById(int iduser);

    @Query("SELECT * FROM user")
    LiveData<List<UserEntity>> getAll();

    @Query("SELECT * FROM user WHERE iduser IN (:userIds)")
    LiveData<List<UserEntity>> loadAllByIds(int[] userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> users);

    @Delete
    void delete(UserEntity user);
}