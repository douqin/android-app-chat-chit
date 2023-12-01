package com.douqin.chatchitVN.data.local.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.douqin.chatchitVN.common.DateConverter;
import com.douqin.chatchitVN.data.local.room.dao.FriendDao;
import com.douqin.chatchitVN.data.local.room.dao.GroupChatDao;
import com.douqin.chatchitVN.data.local.room.dao.MemberDao;
import com.douqin.chatchitVN.data.local.room.dao.MessageDao;
import com.douqin.chatchitVN.data.local.room.dao.MySelfDao;
import com.douqin.chatchitVN.data.local.room.dao.ReactionDao;
import com.douqin.chatchitVN.data.local.room.dao.RequesterDao;
import com.douqin.chatchitVN.data.local.room.dao.StoryDao;
import com.douqin.chatchitVN.data.local.room.dao.UserDao;
import com.douqin.chatchitVN.data.local.room.entity.FriendEntity;
import com.douqin.chatchitVN.data.local.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.local.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.local.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.local.room.entity.ReactionEntity;
import com.douqin.chatchitVN.data.local.room.entity.RelationshipEntity;
import com.douqin.chatchitVN.data.local.room.entity.RequesterEntity;
import com.douqin.chatchitVN.data.local.room.entity.StoryEntity;
import com.douqin.chatchitVN.data.local.room.entity.UserEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        UserEntity.class,
        GroupEntity.class,
        MessageEntity.class,
        MemberEntity.class,
        ReactionEntity.class,
        RelationshipEntity.class,
        StoryEntity.class,
        FriendEntity.class,
        RequesterEntity.class
}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract FriendDao friendDao();
    public abstract RequesterDao requesterDao();

    public abstract MySelfDao mySelfDao();

    public abstract ReactionDao reactionDao();

    public abstract MessageDao messageDao();

    public abstract GroupChatDao groupChatDao();

    public abstract MemberDao memberDao();

    public abstract StoryDao storyDao();

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
