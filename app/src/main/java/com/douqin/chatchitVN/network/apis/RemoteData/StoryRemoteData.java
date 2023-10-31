package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.local.room.entity.StoryEntity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class StoryRemoteData {
    @SerializedName(value = "idstory")
    int idStory;
    @SerializedName(value = "iduserowner")
    int idUserOwner;
    String content;
    @SerializedName(value = "createat")
    Date createAt;

    public StoryEntity toEntity() {
        return new StoryEntity(this.idStory, this.content, this.createAt, this.idUserOwner);
    }
}
