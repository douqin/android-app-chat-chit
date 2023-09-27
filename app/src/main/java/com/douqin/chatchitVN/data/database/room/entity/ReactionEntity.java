package com.douqin.chatchitVN.data.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.douqin.chatchitVN.data.models.UI.Reaction;

@Entity(tableName = "reaction")
public class ReactionEntity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idreaction")
    public int idReaction;

    @ColumnInfo(name = "idmessage")
    public int idMessage;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "idmember")
    public int idMember;


    public ReactionEntity(int idReaction,

                          int idMessage,

                          int type,

                          int idMember) {
        this.idReaction = idReaction;
        this.idMember = idMember;
        this.idMessage = idMessage;
        this.type = type;
    }

    public Reaction toModel() {
        return new Reaction(this.idReaction, this.idMessage, this.type, this.idMember);
    }
}
