package com.douqin.chatchitVN.data.local.room.dao;

import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.local.room.entity.RelationshipEntity;
import com.douqin.chatchitVN.data.local.room.entity.RequesterEntity;

import java.util.List;

@Dao
public interface RequesterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RequesterEntity> users);

    //    @Query("SELECT * FROM friend WHERE  LIKE :query")
    PagingSource<Integer, RelationshipEntity> pagingSource(String query);

    @Query("DELETE FROM friend")
    int clearAll();
}
