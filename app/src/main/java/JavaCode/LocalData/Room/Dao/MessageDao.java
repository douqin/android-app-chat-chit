package JavaCode.DataLocal.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import JavaCode.Model.MessageChat;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messagechat")
    LiveData<List<MessageChat>> getListChat();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(MessageChat messageChat);
}
