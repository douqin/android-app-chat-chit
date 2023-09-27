package com.douqin.chatchitVN.data.models.UI;

public class Reaction {

    public Reaction(int idReaction,

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
}
