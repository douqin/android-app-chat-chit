package com.douqin.chatchitVN.data.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.database.room.entity.ReactionEntity;
import com.douqin.chatchitVN.data.database.room.entity.UserEntity;

import java.util.List;

@Dao
public interface ReactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ReactionEntity> reactEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReactionEntity reactEntity);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM reaction")
    LiveData<List<ReactionEntity>> getAllReaction();
}
