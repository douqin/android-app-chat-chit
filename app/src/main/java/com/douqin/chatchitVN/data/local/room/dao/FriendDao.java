package com.douqin.chatchitVN.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.local.room.entity.FriendEntity;
import com.douqin.chatchitVN.data.local.room.entity.UserEntity;

import java.util.List;

@Dao
public interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FriendEntity> users);

//    @Query("SELECT * FROM friend WHERE  LIKE :query")
    PagingSource<Integer, FriendEntity> pagingSource(String query);

    @Query("DELETE FROM friend")
    int clearAll();
}
