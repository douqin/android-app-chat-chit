package com.douqin.chatchitVN.data.local.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "relationship")
public class RelationshipEntity {
    @PrimaryKey()
    public int id;
    @ColumnInfo(name = "iduser1")
    public int idUser1;

    @ColumnInfo(name = "iduser2")
    public int idUser2;

    @ColumnInfo(name = "relation")
    public String relation;

}
