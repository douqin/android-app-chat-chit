package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MessageRemoteData {
    @SerializedName("content")
    public String content;

    @SerializedName("createat")
    public Date createAt;

    @SerializedName("idmessage")
    public int idMessage;

    @SerializedName("replyidmessage")
    public int replyIdMessage;

    @SerializedName("status")
    public int status;

    @SerializedName("type")
    public int type;

    @SerializedName("idmember")
    public int idMember;

    @SerializedName("ispin")
    public boolean isPin = false;

    public MessageEntity toEntity() {
        return new MessageEntity(
                idMessage,
                content,
                createAt,
                type,
                status,
                replyIdMessage,
                isPin, // Assuming you have set isPin somewhere in your DTO
                idMember
        );
    }
}
