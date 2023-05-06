package JavaCode.LocalData.Room.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import JavaCode.Model.MessageChat;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    MessageChat getInforMessage();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(MessageChat messageChat);
}
