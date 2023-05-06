package JavaCode.LocalData.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import JavaCode.LocalData.Room.Entity.Relationship.GroupChatWithListMessage;
import JavaCode.Model.GroupChat;

@Dao
public interface GroupChatDao {
    @Transaction
    @Query("SELECT * FROM groupchat WHERE idgroup = :idgroup")
    LiveData<GroupChatWithListMessage> getGroupChatWithMessage(int idgroup);

    @Query("SELECT * FROM groupchat")
    LiveData<List<GroupChat>> getListGroupChat();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroup(GroupChat groupChat);

}
