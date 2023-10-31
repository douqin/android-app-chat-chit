package com.douqin.chatchitVN.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.douqin.chatchitVN.data.local.room.entity.StoryEntity;
import com.douqin.chatchitVN.data.local.room.relationship.UserWithStoryRelation;

import java.util.List;

@Dao
public interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<StoryEntity> entities);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(StoryEntity entity);

    @Transaction
    @Query("SELECT * FROM user")
    LiveData<List<UserWithStoryRelation>> getStoryFriend();
}
