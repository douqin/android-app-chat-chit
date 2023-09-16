package com.douqin.chatchitVN.data.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    LiveData<List<MessageEntity>> getAllMessage();

    @Query("SELECT * FROM member")
    LiveData<List<MemberEntity>> getMems();

    @Query("SELECT message.createat FROM message ORDER BY message.createat DESC LIMIT 1")
    Date getTimeLastMessage();

    //    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(MessageEntity messageChat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<MessageEntity> messageChat);

    @Query("SELECT `member`.iduser FROM `group` join member ON `group`.idgroup = `member`.idgroup AND `member`.id = :idMember AND `group`.idgroup = :idgroup")
    int getIduserFromMessage(int idMember, int idgroup);
}
