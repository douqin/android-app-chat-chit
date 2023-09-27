package com.douqin.chatchitVN.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.douqin.chatchitVN.data.database.room.entity.UserEntity;
import java.util.List;

@Dao
public interface ReactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserEntity );
    @Delete
    void delete(UserEntity user);
}
