package com.douqin.chatchitVN.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.local.room.entity.ReactionEntity;
import com.douqin.chatchitVN.data.local.room.entity.RelationshipEntity;

import java.util.List;

@Dao
public interface RelationshipDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RelationshipEntity> reactEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RelationshipEntity reactEntity);

    @Delete
    void delete(RelationshipEntity user);

    @Query("SELECT * FROM reaction")
    LiveData<List<RelationshipEntity>> getAllReaction();
}
