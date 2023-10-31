package com.douqin.chatchitVN.data.local.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.UI.Story;

import java.util.Date;

@Entity(tableName = "story")
public class StoryEntity {

    public StoryEntity(int idStory, String content, Date createAt, int idUserOwner) {
        this.idStory = idStory;
        this.content = content;
        this.createAt = createAt;
        this.idUserOwner = idUserOwner;
    }

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idstory")
    public int idStory;
    @ColumnInfo(name = "iduserowner")
    public int idUserOwner;

    public String content;

    @ColumnInfo(name = "createat")
    public Date createAt;

    public Story toModel() {
        return new Story(idStory,
                idUserOwner,
                content,
                createAt);
    }
}
