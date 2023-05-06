package JavaCode.LocalData.Room.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import JavaCode.LocalData.Room.Dao.GroupChatDao;
import JavaCode.LocalData.Room.Dao.MessageDao;
import JavaCode.LocalData.Room.Dao.MySelfDao;
import JavaCode.LocalData.Room.Dao.UserDao;
import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageChat;
import JavaCode.Model.User;

@Database(entities = {User.class,
        MessageChat.class,
        GroupChat.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract MySelfDao mySelfDao();

    public abstract MessageDao messageDao();

    public abstract GroupChatDao groupChatDao();

    public static String appDatabase = "AppDatabase.db";
    private static AppDatabase instance;
    private static final int NUMBER_OF_THREADS = 5;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase gI(Context c) {
        if (instance == null) {
            instance = Room.databaseBuilder(c, AppDatabase.class, appDatabase)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
