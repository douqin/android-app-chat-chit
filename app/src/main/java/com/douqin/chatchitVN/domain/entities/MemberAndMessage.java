package com.douqin.chatchitVN.domain.entities;

import com.douqin.chatchitVN.data.models.UI.Member;
import com.douqin.chatchitVN.data.models.UI.MessageChat;

public class MemberAndMessage {

    public Member member;
    public MessageChat messageChat;

    public MemberAndMessage(Member member,
                            MessageChat messageChat) {
        this.member = member;
        this.messageChat = messageChat;
    }
}