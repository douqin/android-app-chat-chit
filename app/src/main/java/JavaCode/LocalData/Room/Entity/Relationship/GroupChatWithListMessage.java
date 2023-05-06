package JavaCode.LocalData.Room.Entity.Relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageChat;

public class GroupChatWithListMessage {
    @Embedded
    public GroupChat groupChat;
    @Relation(
            parentColumn = "idgroup",
            entityColumn = "idgroup"
    )
    public List<MessageChat> listMessage;
}