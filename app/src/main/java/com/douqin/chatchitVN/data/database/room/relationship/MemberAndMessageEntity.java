package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.domain.entities.MemberAndMessage;

public class MemberAndMessageEntity {
    @Embedded
    public MemberEntity memberEntity;
    @Relation(
            parentColumn = "id",
            entityColumn = "idmember"
    )
    public MessageEntity messageEntity;

    public MemberAndMessage toModel() {
        if (this.messageEntity == null) {
            return null;
        }
        return new MemberAndMessage(this.memberEntity.toMemberModel(), this.messageEntity.toMessageChat());
    }
}
