package JavaCode.DataLocal.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import JavaCode.DataLocal.Room.Entity.Relationship.GroupChatWithMessage;
import JavaCode.Model.GroupChat;

@Dao
public interface GroupChatDao {
    @Transaction
    @Query("SELECT * FROM groupchat WHERE idgroup = :idgroup LIMIT 1")
    LiveData<GroupChatWithMessage> getGroupChatWithMessage(int idgroup);

    @Query("SELECT * FROM groupchat")
    LiveData<List<GroupChat>> getListGroupChat();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroup(GroupChat groupChat);

}
