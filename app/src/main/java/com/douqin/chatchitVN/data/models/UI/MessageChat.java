package com.douqin.chatchitVN.data.models.UI;

import com.douqin.chatchitVN.common.Identifiable;

import java.util.Date;
import java.util.Objects;

public class MessageChat implements Identifiable {

    public boolean me = false;

    public int idMessage;

    public String content;

    public Date createdAt;

    public int type;

    public int status;

    public int replyIdMessage;

    public boolean isPin;

    public int idMember;

    public final static int TYPE_CHAT_IMG = 1;
    public final static int TYPE_CHAT_STR = 2;
    public final static int TYPE_CHAT_VIDEO = 3;
    public final static int TYPE_CHAT_HYPER_LINK = 4;
    public final static int TYPE_CHAT_GIF = 5;

    public MessageChat(int idMessage, String content, Date createdAt, int type, int status, int replyIdMessage, boolean isPin, int idMember) {
        this.idMessage = idMessage;
        this.content = content;
        this.createdAt = createdAt;
        this.type = type;
        this.status = status;
        this.replyIdMessage = replyIdMessage;
        this.isPin = isPin;
        this.idMember = idMember;
    }

    @Override
    public long getId() {
        return this.idMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageChat otherMessage = (MessageChat) obj;
        return idMessage == otherMessage.idMessage && Objects.equals(content, otherMessage.content) && Objects.equals(createdAt, otherMessage.createdAt) && type == otherMessage.type && status == otherMessage.status && replyIdMessage == otherMessage.replyIdMessage && isPin == otherMessage.isPin && idMember == otherMessage.idMember;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, content, createdAt, type, status, replyIdMessage, isPin, idMember);
    }
}
