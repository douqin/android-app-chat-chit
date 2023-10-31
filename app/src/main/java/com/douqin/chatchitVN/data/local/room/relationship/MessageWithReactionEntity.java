package com.douqin.chatchitVN.data.local.room.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.local.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.local.room.entity.ReactionEntity;
import com.douqin.chatchitVN.data.models.UI.MessageChat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageWithReactionEntity {
    @Embedded
    public MessageEntity messageEntity;
    @Relation(

            parentColumn = "idmessage",
            entityColumn = "idmessage"
    )
    public List<ReactionEntity> reactionEntityList;

    public MessageChat toModel() {
        MessageChat messageChat = messageEntity.toMessageChat();
        if (reactionEntityList != null)
            messageChat.reactions = this.reactionEntityList.stream().map(
                    ReactionEntity::toModel
            ).collect(Collectors.toList());
        else messageChat.reactions = new ArrayList<>();
        return messageChat;
    }
}
