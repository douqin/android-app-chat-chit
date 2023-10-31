package com.douqin.chatchitVN.network.apis.RemoteData;

import com.douqin.chatchitVN.data.local.room.entity.ReactionEntity;

public class ReactionRemoteData {
    public ReactionRemoteData(int idReaction,

                              int idMessage,

                              int type,

                              int idMember) {
        this.idReaction = idReaction;
        this.idMember = idMember;
        this.idMessage = idMessage;
        this.type = type;
    }

    public int idReaction;

    public int idMessage;

    public int type;

    public int idMember;

    public ReactionEntity toEntity() {
        return new ReactionEntity(this.idReaction, this.idMessage, this.type, this.idMember);
    }
}
