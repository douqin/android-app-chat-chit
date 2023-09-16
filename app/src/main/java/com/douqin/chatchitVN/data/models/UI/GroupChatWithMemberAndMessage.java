package com.douqin.chatchitVN.data.models.UI;

import java.util.List;

public class GroupChatWithMemberAndMessage {

    public GroupChat group;
    public List<MemberWithMessage> memberWithMessages;

    public GroupChatWithMemberAndMessage(GroupChat group, List<MemberWithMessage> memberWithMessages) {
        this.group = group;
        this.memberWithMessages = memberWithMessages;
    }

}
