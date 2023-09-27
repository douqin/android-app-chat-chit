package com.douqin.chatchitVN.network.apis.RemoteData;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class StoryRemoteData {
    int id;
    @SerializedName(value = "iduserowner")
    int idUserOwner;
    String content;
    @SerializedName(value = "createat")
    Date createAt;
}
