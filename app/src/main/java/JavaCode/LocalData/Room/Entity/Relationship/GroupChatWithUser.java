package JavaCode.LocalData.Room.Entity.Relationship;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import JavaCode.Model.GroupChat;
import JavaCode.Model.User;

public class GroupChatWithUser {
    @Embedded
    public GroupChat groupChat;
    @Relation(
            parentColumn = "idgroup",
            entityColumn = "iduser",
            associateBy = @Junction(UserGroupChatCrossRef.class)
    )
    public List<User> users;
}
