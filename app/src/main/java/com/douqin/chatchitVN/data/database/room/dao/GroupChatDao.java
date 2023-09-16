package com.douqin.chatchitVN.data.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.douqin.chatchitVN.data.database.room.entity.GroupAndMemberAndMessageEntity;
import com.douqin.chatchitVN.data.database.room.entity.GroupChatWithMemberAndMessageEntity;
import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.database.room.entity.MemberWithMessageEntity;

import java.util.List;

@Dao
public interface GroupChatDao {
    @Transaction()
    @Query("SELECT * FROM `group` WHERE `group`.idgroup == :id")
    LiveData<GroupChatWithMemberAndMessageEntity> getGrWithMemberAndMessage(int id);


    @Transaction()
    @Query("SELECT * FROM `member`")
    LiveData<List<MemberWithMessageEntity>> getTest();

    @Transaction()
    @Query("SELECT * FROM `group`")
    LiveData<List<GroupAndMemberAndMessageEntity>> getListGroupWithLastMessage();

    @Transaction()
    @Query("SELECT * FROM `group`")
    LiveData<List<GroupEntity>> getListGroupChat();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroup(GroupEntity groupChat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllGroup(List<GroupEntity> groupChats);

}
