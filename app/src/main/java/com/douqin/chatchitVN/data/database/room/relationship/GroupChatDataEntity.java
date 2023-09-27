package com.douqin.chatchitVN.data.database.room.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.models.UI.GroupChatWithMemberAndMessage;
import com.douqin.chatchitVN.data.models.UI.MemberWithMessage;

import java.util.ArrayList;
import java.util.List;

public class GroupChatDataEntity {
    @Embedded
    public GroupEntity group;
    @Relation(
            entity = MemberEntity.class,
            parentColumn = "idgroup",
            entityColumn = "idgroup"
    )
    public List<MemberWithMessageEntity> memberWithMessages;

    public GroupChatWithMemberAndMessage toModel() {
        List<MemberWithMessage> memberWithMessages1 = new ArrayList<>();
        for (MemberWithMessageEntity messageEntity : memberWithMessages) {
            memberWithMessages1.add(messageEntity.toModel());
        }
        return new GroupChatWithMemberAndMessage(this.group.toModel(), memberWithMessages1);
    }

}
