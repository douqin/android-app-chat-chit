package com.douqin.chatchitVN.data.database.room.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.models.UI.MemberWithMessage;
import com.douqin.chatchitVN.data.models.UI.MessageChat;

import java.util.ArrayList;
import java.util.List;

public class MemberWithMessageEntity {
    @Embedded
    public MemberEntity memberEntity;
    @Relation(
            entity = MessageEntity.class,
            parentColumn = "id",
            entityColumn = "idmember"
    )
    public List<MessageWithReactionEntity> messageEntityList;

    public MemberWithMessage toModel() {
        List<MessageChat> messageChats = new ArrayList<>();
        for (MessageWithReactionEntity messageEntity : messageEntityList) {
            messageChats.add(messageEntity.toModel());
        }
        return new MemberWithMessage(memberEntity.toMemberModel(), messageChats);
    }
}