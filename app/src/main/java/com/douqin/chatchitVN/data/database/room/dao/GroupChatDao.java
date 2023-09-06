package com.douqin.chatchitVN.data.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;

import java.util.List;

@Dao
public interface GroupChatDao {

    @Query("SELECT * FROM `group`")
    LiveData<List<GroupEntity>> getListGroupChat();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroup(GroupEntity groupChat);

}
