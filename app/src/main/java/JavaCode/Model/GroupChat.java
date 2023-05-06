package JavaCode.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "groupchat")
public class GroupChat {
    public GroupChat(int idgroup, String name, int status, String avatar) {
        this.idgroup = idgroup;
        this.name = name;
        this.status = status;
        this.avatar = avatar;
    }

    @PrimaryKey
    public int idgroup;
    public String name;
    public int status;
    public String avatar;

}
