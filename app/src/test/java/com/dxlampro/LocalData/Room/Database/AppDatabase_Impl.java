package com.dxlampro.LocalData.Room.Database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.dxlampro.LocalData.Room.Dao.GroupChatDao_Impl;
import com.dxlampro.LocalData.Room.Dao.MessageDao_Impl;
import com.dxlampro.LocalData.Room.Dao.UserDao_Impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import JavaCode.LocalData.Room.Dao.GroupChatDao;
import JavaCode.LocalData.Room.Dao.MessageDao;
import JavaCode.LocalData.Room.Dao.UserDao;
import JavaCode.LocalData.Room.Database.AppDatabase;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
    private volatile UserDao _userDao;

    private volatile MessageDao _messageDao;

    private volatile GroupChatDao _groupChatDao;

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
            @Override
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`iduser` INTEGER NOT NULL, `avatar` TEXT, `email` TEXT, `name` TEXT, `address` TEXT, `birth` TEXT, `isMe` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`iduser`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `messagechat` (`idchat` INTEGER NOT NULL, `idgroup` INTEGER NOT NULL, `iduser` INTEGER NOT NULL, `message` TEXT, `typechat` INTEGER NOT NULL, `timechat` TEXT, `status` INTEGER NOT NULL, PRIMARY KEY(`idchat`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `groupchat` (`idgroup` INTEGER NOT NULL, `name` TEXT, `status` INTEGER NOT NULL, `avatar` TEXT, PRIMARY KEY(`idgroup`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'dccdb36e44d94362e3eb3a4bbd7d46e4')");
            }

            @Override
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `user`");
                _db.execSQL("DROP TABLE IF EXISTS `messagechat`");
                _db.execSQL("DROP TABLE IF EXISTS `groupchat`");
                if (mCallbacks != null) {
                    for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
                        mCallbacks.get(_i).onDestructiveMigration(_db);
                    }
                }
            }

            @Override
            public void onCreate(SupportSQLiteDatabase _db) {
                if (mCallbacks != null) {
                    for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
                        mCallbacks.get(_i).onCreate(_db);
                    }
                }
            }

            @Override
            public void onOpen(SupportSQLiteDatabase _db) {
                mDatabase = _db;
                internalInitInvalidationTracker(_db);
                if (mCallbacks != null) {
                    for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
                        mCallbacks.get(_i).onOpen(_db);
                    }
                }
            }

            @Override
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(7);
                _columnsUser.put("iduser", new TableInfo.Column("iduser", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsUser.put("avatar", new TableInfo.Column("avatar", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsUser.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsUser.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsUser.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsUser.put("birth", new TableInfo.Column("birth", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsUser.put("isMe", new TableInfo.Column("isMe", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
                final TableInfo _existingUser = TableInfo.read(_db, "user");
                if (!_infoUser.equals(_existingUser)) {
                    return new RoomOpenHelper.ValidationResult(false, "user(JavaCode.Model.User).\n"
                            + " Expected:\n" + _infoUser + "\n"
                            + " Found:\n" + _existingUser);
                }
                final HashMap<String, TableInfo.Column> _columnsMessagechat = new HashMap<String, TableInfo.Column>(7);
                _columnsMessagechat.put("idchat", new TableInfo.Column("idchat", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMessagechat.put("idgroup", new TableInfo.Column("idgroup", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMessagechat.put("iduser", new TableInfo.Column("iduser", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMessagechat.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMessagechat.put("typechat", new TableInfo.Column("typechat", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMessagechat.put("timechat", new TableInfo.Column("timechat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMessagechat.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysMessagechat = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesMessagechat = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoMessagechat = new TableInfo("messagechat", _columnsMessagechat, _foreignKeysMessagechat, _indicesMessagechat);
                final TableInfo _existingMessagechat = TableInfo.read(_db, "messagechat");
                if (!_infoMessagechat.equals(_existingMessagechat)) {
                    return new RoomOpenHelper.ValidationResult(false, "messagechat(JavaCode.Model.MessageChat).\n"
                            + " Expected:\n" + _infoMessagechat + "\n"
                            + " Found:\n" + _existingMessagechat);
                }
                final HashMap<String, TableInfo.Column> _columnsGroupchat = new HashMap<String, TableInfo.Column>(4);
                _columnsGroupchat.put("idgroup", new TableInfo.Column("idgroup", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsGroupchat.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsGroupchat.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsGroupchat.put("avatar", new TableInfo.Column("avatar", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysGroupchat = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesGroupchat = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoGroupchat = new TableInfo("groupchat", _columnsGroupchat, _foreignKeysGroupchat, _indicesGroupchat);
                final TableInfo _existingGroupchat = TableInfo.read(_db, "groupchat");
                if (!_infoGroupchat.equals(_existingGroupchat)) {
                    return new RoomOpenHelper.ValidationResult(false, "groupchat(JavaCode.Model.GroupChat).\n"
                            + " Expected:\n" + _infoGroupchat + "\n"
                            + " Found:\n" + _existingGroupchat);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "dccdb36e44d94362e3eb3a4bbd7d46e4", "54f5425e180035a54a6008a897d3256a");
        final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
                .name(configuration.name)
                .callback(_openCallback)
                .build();
        final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
        HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user", "messagechat", "groupchat");
    }

    @Override
    public void clearAllTables() {
        super.assertNotMainThread();
        final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `user`");
            _db.execSQL("DELETE FROM `messagechat`");
            _db.execSQL("DELETE FROM `groupchat`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
        _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(MessageDao.class, MessageDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(GroupChatDao.class, GroupChatDao_Impl.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
        return _autoMigrationSpecsSet;
    }

    @Override
    public List<Migration> getAutoMigrations(
            @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList();
    }

    @Override
    public UserDao userDao() {
        if (_userDao != null) {
            return _userDao;
        } else {
            synchronized (this) {
                if (_userDao == null) {
                    _userDao = new UserDao_Impl(this);
                }
                return _userDao;
            }
        }
    }

    @Override
    public MessageDao messageDao() {
        if (_messageDao != null) {
            return _messageDao;
        } else {
            synchronized (this) {
                if (_messageDao == null) {
                    _messageDao = new MessageDao_Impl(this);
                }
                return _messageDao;
            }
        }
    }

    @Override
    public GroupChatDao groupChatDao() {
        if (_groupChatDao != null) {
            return _groupChatDao;
        } else {
            synchronized (this) {
                if (_groupChatDao == null) {
                    _groupChatDao = new GroupChatDao_Impl(this);
                }
                return _groupChatDao;
            }
        }
    }
}
