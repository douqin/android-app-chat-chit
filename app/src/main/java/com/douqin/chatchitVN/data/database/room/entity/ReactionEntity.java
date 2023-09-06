package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reaction")
public class ReactionEntity {
    @PrimaryKey
    @ColumnInfo(name = "idreaction")
    public int idReaction;

    @ColumnInfo(name = "idmessage")
    public int idMessage;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "idmember")
    public int idMember;

}
