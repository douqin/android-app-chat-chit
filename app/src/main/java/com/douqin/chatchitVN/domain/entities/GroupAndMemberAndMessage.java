package com.douqin.chatchitVN.domain.entities;

import com.douqin.chatchitVN.common.Identifiable;
import com.douqin.chatchitVN.data.models.UI.GroupChat;

public class GroupAndMemberAndMessage implements Identifiable {
    public GroupChat group;
    public MemberAndMessage memberWithMessages;

    public GroupAndMemberAndMessage(GroupChat group,
                                    MemberAndMessage memberWithMessages) {
        this.group = group;
        this.memberWithMessages = memberWithMessages;
    }

    @Override
    public long getId() {
        return this.group.idgroup;
    }
}