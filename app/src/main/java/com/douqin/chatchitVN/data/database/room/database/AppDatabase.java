package com.douqin.chatchitVN.data.database.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.douqin.chatchitVN.common.DateConverter;
import com.douqin.chatchitVN.data.database.room.dao.GroupChatDao;
import com.douqin.chatchitVN.data.database.room.dao.MemberDao;
import com.douqin.chatchitVN.data.database.room.dao.MessageDao;
import com.douqin.chatchitVN.data.database.room.dao.MySelfDao;
import com.douqin.chatchitVN.data.database.room.dao.ReactionDao;
import com.douqin.chatchitVN.data.database.room.dao.UserDao;
import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.database.room.entity.ReactionEntity;
import com.douqin.chatchitVN.data.database.room.entity.RelationshipEntity;
import com.douqin.chatchitVN.data.database.room.entity.UserEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        UserEntity.class,
        GroupEntity.class,
        MessageEntity.class,
        MemberEntity.class,
        ReactionEntity.class,
        RelationshipEntity.class
}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract MySelfDao mySelfDao();

    public abstract ReactionDao reactionDao();

    public abstract MessageDao messageDao();

    public abstract GroupChatDao groupChatDao();

    public abstract MemberDao memberDao();

    public static String appDatabase = "AppDatabase.db";
    private static AppDatabase instance;
    private static final int NUMBER_OF_THREADS = 7;
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
