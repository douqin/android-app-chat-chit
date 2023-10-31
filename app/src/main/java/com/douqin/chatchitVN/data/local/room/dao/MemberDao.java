package com.douqin.chatchitVN.data.local.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.douqin.chatchitVN.data.local.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.local.room.entity.UserEntity;

import java.util.List;

@Dao
public interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(MemberEntity memberEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<MemberEntity> memberEntity);

    @Query("SELECT user.* FROM user JOIN member ON user.iduser == member.iduser AND member.id = :idMember")
    UserEntity getInformationMember(int idMember);

    @Query("SELECT member.* FROM user JOIN member ON user.iduser == member.iduser AND user.iduser= :iduser")
    MemberEntity getInformationMemberFromUser(int iduser);
}
