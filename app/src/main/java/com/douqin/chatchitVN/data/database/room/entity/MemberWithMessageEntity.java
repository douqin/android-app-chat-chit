package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.models.UI.MemberWithMessage;
import com.douqin.chatchitVN.data.models.UI.MessageChat;

import java.util.ArrayList;
import java.util.List;

public class MemberWithMessageEntity {
    @Embedded
    public MemberEntity memberEntity;
    @Relation(
            parentColumn = "id",
            entityColumn = "idmember"
    )
    public List<MessageEntity> messageEntityList;

    public MemberWithMessage toModel() {
        List<MessageChat> messageChats = new ArrayList<>();
        for (MessageEntity messageEntity : messageEntityList) {
            messageChats.add(messageEntity.toMessageChat());
        }
        return new MemberWithMessage(memberEntity.toMemberModel(), messageChats);
    }
}