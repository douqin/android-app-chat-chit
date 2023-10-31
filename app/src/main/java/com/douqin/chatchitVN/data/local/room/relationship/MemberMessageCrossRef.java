package com.douqin.chatchitVN.data.local.room.relationship;

import androidx.room.Entity;

@Entity(primaryKeys = {"id", "idMessage"})
public class MemberMessageCrossRef {
    public int id;
    public int idMessage;
}
