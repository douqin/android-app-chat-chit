package com.douqin.chatchitVN.data.models.UI;

import java.util.List;

public class MessageWithReaction {
    public MessageChat messageEntity;
    public List<Reaction> reactionEntityList;

    public MessageWithReaction(MessageChat messageChat,
                               List<Reaction> reactions) {
        this.messageEntity = messageChat;
        this.reactionEntityList = reactions;
    }
}
