package com.douqin.chatchitVN.data.models.UI;

import com.douqin.chatchitVN.common.Identifiable;

import java.util.List;

public class MemberWithMessage implements Identifiable {
    public Member member;
    public List<MessageChat> messageChats;

    public MemberWithMessage(Member memberEntity, List<MessageChat> messageEntityList) {
        this.member = memberEntity;
        this.messageChats = messageEntityList;
    }

    @Override
    public long getId() {
        return member.id;
    }
}
