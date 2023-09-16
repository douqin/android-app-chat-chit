package com.douqin.chatchitVN.domain.entities;

import com.douqin.chatchitVN.data.models.UI.GroupChat;
import com.douqin.chatchitVN.data.models.UI.MessageChat;

public class GroupWithLastMessage {
    public GroupChat groupChat;
    public MessageChat messageChat;

    public GroupWithLastMessage(GroupChat groupChat, MessageChat messageChat) {
        this.groupChat = groupChat;
        this.messageChat = messageChat;
    }
}
