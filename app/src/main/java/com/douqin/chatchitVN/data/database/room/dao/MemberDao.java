package com.douqin.chatchitVN.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;

@Dao
public interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(MemberEntity memberEntity);
}
