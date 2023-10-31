package com.douqin.chatchitVN.data.models.UI;

import java.util.Date;

public class Story {
    public int idStory;
    public int idUserOwner;
    public String content;
    public Date createAt;

    public Story(int id,
                 int idUserOwner,
                 String content,
                 Date createAt) {
        this.content = content;
        this.idStory = id;
        this.idUserOwner = idUserOwner;
        this.createAt = createAt;
    }
}