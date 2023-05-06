package com.dxlampro.LocalData.Room.Dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import JavaCode.LocalData.Room.Dao.UserDao;
import JavaCode.Model.User;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
    private final RoomDatabase __db;

    private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

    private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

    public UserDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
            @Override
            public String createQuery() {
                return "INSERT OR ABORT INTO `user` (`iduser`,`avatar`,`email`,`name`,`address`,`birth`,`isMe`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, User value) {
                stmt.bindLong(1, value.iduser);
                if (value.avatar == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.avatar);
                }
                if (value.phone == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.phone);
                }
                if (value.name == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.name);
                }
                if (value.address == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.address);
                }
                if (value.birthday == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.birthday);
                }
                stmt.bindLong(7, value.isMe);
            }
        };
        this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
            @Override
            public String createQuery() {
                return "DELETE FROM `user` WHERE `iduser` = ?";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, User value) {
                stmt.bindLong(1, value.iduser);
            }
        };
    }

    @Override
    public void insertAll(final User... users) {
        __db.assertNotSuspendingTransaction();
        __db.beginTransaction();
        try {
            __insertionAdapterOfUser.insert(users);
            __db.setTransactionSuccessful();
        } finally {
            __db.endTransaction();
        }
    }

    @Override
    public void delete(final User user) {
        __db.assertNotSuspendingTransaction();
        __db.beginTransaction();
        try {
            __deletionAdapterOfUser.handle(user);
            __db.setTransactionSuccessful();
        } finally {
            __db.endTransaction();
        }
    }

    @Override
    public LiveData<List<User>> getAll() {
        final String _sql = "SELECT * FROM user";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return __db.getInvalidationTracker().createLiveData(new String[]{"user"}, false, new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfIduser = CursorUtil.getColumnIndexOrThrow(_cursor, "iduser");
                    final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
                    final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
                    final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
                    final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
                    final int _cursorIndexOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "birth");
                    final int _cursorIndexOfIsMe = CursorUtil.getColumnIndexOrThrow(_cursor, "isMe");
                    final List<User> _result = new ArrayList<User>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        final User _item;
                        final int _tmpIduser;
                        _tmpIduser = _cursor.getInt(_cursorIndexOfIduser);
                        final String _tmpAvatar;
                        if (_cursor.isNull(_cursorIndexOfAvatar)) {
                            _tmpAvatar = null;
                        } else {
                            _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
                        }
                        final String _tmpEmail;
                        if (_cursor.isNull(_cursorIndexOfEmail)) {
                            _tmpEmail = null;
                        } else {
                            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
                        }
                        final String _tmpName;
                        if (_cursor.isNull(_cursorIndexOfName)) {
                            _tmpName = null;
                        } else {
                            _tmpName = _cursor.getString(_cursorIndexOfName);
                        }
                        final String _tmpAddress;
                        if (_cursor.isNull(_cursorIndexOfAddress)) {
                            _tmpAddress = null;
                        } else {
                            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
                        }
                        final String _tmpBirth;
                        if (_cursor.isNull(_cursorIndexOfBirth)) {
                            _tmpBirth = null;
                        } else {
                            _tmpBirth = _cursor.getString(_cursorIndexOfBirth);
                        }
                        _item = new User(_tmpAvatar, _tmpEmail, _tmpIduser, _tmpName, _tmpAddress, _tmpBirth);
                        _item.isMe = _cursor.getInt(_cursorIndexOfIsMe);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            @Override
            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override
    public LiveData<List<User>> loadAllByIds(final int[] userIds) {
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT * FROM user WHERE iduser IN (");
        final int _inputSize = userIds.length;
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final int _argCount = 0 + _inputSize;
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (int _item : userIds) {
            _statement.bindLong(_argIndex, _item);
            _argIndex++;
        }
        return __db.getInvalidationTracker().createLiveData(new String[]{"user"}, false, new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfIduser = CursorUtil.getColumnIndexOrThrow(_cursor, "iduser");
                    final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
                    final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
                    final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
                    final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
                    final int _cursorIndexOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "birth");
                    final int _cursorIndexOfIsMe = CursorUtil.getColumnIndexOrThrow(_cursor, "isMe");
                    final List<User> _result = new ArrayList<User>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        final User _item_1;
                        final int _tmpIduser;
                        _tmpIduser = _cursor.getInt(_cursorIndexOfIduser);
                        final String _tmpAvatar;
                        if (_cursor.isNull(_cursorIndexOfAvatar)) {
                            _tmpAvatar = null;
                        } else {
                            _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
                        }
                        final String _tmpEmail;
                        if (_cursor.isNull(_cursorIndexOfEmail)) {
                            _tmpEmail = null;
                        } else {
                            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
                        }
                        final String _tmpName;
                        if (_cursor.isNull(_cursorIndexOfName)) {
                            _tmpName = null;
                        } else {
                            _tmpName = _cursor.getString(_cursorIndexOfName);
                        }
                        final String _tmpAddress;
                        if (_cursor.isNull(_cursorIndexOfAddress)) {
                            _tmpAddress = null;
                        } else {
                            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
                        }
                        final String _tmpBirth;
                        if (_cursor.isNull(_cursorIndexOfBirth)) {
                            _tmpBirth = null;
                        } else {
                            _tmpBirth = _cursor.getString(_cursorIndexOfBirth);
                        }
                        _item_1 = new User(_tmpAvatar, _tmpEmail, _tmpIduser, _tmpName, _tmpAddress, _tmpBirth);
                        _item_1.isMe = _cursor.getInt(_cursorIndexOfIsMe);
                        _result.add(_item_1);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            @Override
            protected void finalize() {
                _statement.release();
            }
        });
    }


    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
