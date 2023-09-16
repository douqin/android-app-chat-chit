package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"id", "idMessage"})
public class MemberMessageCrossRef {
    public int id;
    public int idMessage;
}
