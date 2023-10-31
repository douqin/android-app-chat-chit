package com.douqin.chatchitVN.data.local.room.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.local.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.local.room.entity.MemberEntity;
import com.douqin.chatchitVN.domain.entities.GroupAndMemberAndMessage;

public class GroupAndMemberAndMessageEntity {
    @Embedded
    public GroupEntity group;
    @Relation(
            entity = MemberEntity.class,
            parentColumn = "idgroup",
            entityColumn = "idgroup"
    )
    public MemberAndMessageEntity memberWithMessages;

    public GroupAndMemberAndMessage toModel() {
        if (this.memberWithMessages == null)
            return new GroupAndMemberAndMessage(this.group.toModel(), null);
        else
            return new GroupAndMemberAndMessage(this.group.toModel(), this.memberWithMessages.toModel());
    }
}
