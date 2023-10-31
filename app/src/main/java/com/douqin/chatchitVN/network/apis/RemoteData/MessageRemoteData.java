package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.local.room.entity.MessageEntity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

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

    public List<ReactionRemoteData> reacts;

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
