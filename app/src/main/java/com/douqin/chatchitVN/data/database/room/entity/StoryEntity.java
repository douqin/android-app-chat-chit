package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "story")
public class StoryEntity {
    @PrimaryKey(autoGenerate = false)
    int id;
    @ColumnInfo(name = "iduserowner")
    int idUserOwner;
    String content;

    @ColumnInfo(name = "createat")
    Date createAt;
}
