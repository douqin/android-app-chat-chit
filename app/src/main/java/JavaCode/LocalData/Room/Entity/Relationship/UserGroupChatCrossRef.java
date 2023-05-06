package JavaCode.DataLocal.Room.Entity.Relationship;

import androidx.room.Entity;

@Entity(primaryKeys = {"iduser", "idgroup"})
public class UserGroupChatCrossRef {
    public int iduser;
    public int idgroup;
}
