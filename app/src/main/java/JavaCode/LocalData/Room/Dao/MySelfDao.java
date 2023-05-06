package JavaCode.LocalData.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import JavaCode.Model.User;

@Dao
public interface MySelfDao {
    @Query("SELECT * FROM user WHERE user.isMe = 1 LIMIT 1 ")
    LiveData<User> getMe();

    @Query("SELECT COUNT(*) FROM user WHERE user.isMe = 1")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setMe(User user);
}
