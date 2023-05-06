package JavaCode.DataLocal.Room.Entity.Relationship;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import java.util.List;
import JavaCode.Model.GroupChat;
import JavaCode.Model.User;

public class UserWithGroupChat {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "iduser",
            entityColumn = "idgroup",
            associateBy = @Junction(UserGroupChatCrossRef.class)
    )
    public List<GroupChat> groupChats;
}
