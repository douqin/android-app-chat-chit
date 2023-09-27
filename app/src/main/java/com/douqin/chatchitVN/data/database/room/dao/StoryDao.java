package com.douqin.chatchitVN.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.douqin.chatchitVN.data.database.room.entity.StoryEntity;

import java.util.List;

@Dao
public interface StoryDao {
    @Insert
    void InsertAll(List<StoryEntity> entities);

    @Insert
    void Insert(StoryEntity entity);


}
