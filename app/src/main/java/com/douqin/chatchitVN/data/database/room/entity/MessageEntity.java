package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.MessageChat;

import java.util.Date;

@Entity(tableName = "message")
public class MessageEntity {
    @PrimaryKey
    @ColumnInfo(name = "idmessage")
    public int idMessage;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "createat")
    public Date createdAt;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "replyidmessage")
    public int replyIdMessage;

    @ColumnInfo(name = "ispin")
    public boolean isPin;

    @ColumnInfo(name = "idmember")
    public int idMember;

    public MessageEntity(int idMessage, String content, Date createdAt, int type, int status, int replyIdMessage, boolean isPin, int idMember) {
        this.idMessage = idMessage;
        this.content = content;
        this.createdAt = createdAt;
        this.type = type;
        this.status = status;
        this.replyIdMessage = replyIdMessage;
        this.isPin = isPin;
        this.idMember = idMember;
    }

    public MessageChat toMessageChat() {
        return new MessageChat(
                idMessage,
                content,
                createdAt,
                type,
                status,
                replyIdMessage,
                isPin,
                idMember
        );
    }
}
