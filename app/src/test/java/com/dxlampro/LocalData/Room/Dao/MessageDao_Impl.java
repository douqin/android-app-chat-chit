package com.dxlampro.LocalData.Room.Dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import JavaCode.LocalData.Room.Dao.MessageDao;
import JavaCode.Model.MessageChat;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MessageDao_Impl implements MessageDao {
    private final RoomDatabase __db;

    private final EntityInsertionAdapter<MessageChat> __insertionAdapterOfMessageChat;

    public MessageDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMessageChat = new EntityInsertionAdapter<MessageChat>(__db) {
            @Override
            public String createQuery() {
                return "INSERT OR IGNORE INTO `messagechat` (`idchat`,`idgroup`,`iduser`,`message`,`typechat`,`timechat`,`status`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, MessageChat value) {
                stmt.bindLong(1, value.idchat);
                stmt.bindLong(2, value.idgroup);
                stmt.bindLong(3, value.iduser);
                if (value.message == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.message);
                }
                stmt.bindLong(5, value.typechat);
                if (value.timechat == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.timechat);
                }
                stmt.bindLong(7, value.status);
            }
        };
    }

    @Override
    public void Insert(final MessageChat messageChat) {
        __db.assertNotSuspendingTransaction();
        __db.beginTransaction();
        try {
            __insertionAdapterOfMessageChat.insert(messageChat);
            __db.setTransactionSuccessful();
        } finally {
            __db.endTransaction();
        }
    }

    @Override
    public LiveData<List<MessageChat>> getListChat() {
        final String _sql = "SELECT * FROM messagechat";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return __db.getInvalidationTracker().createLiveData(new String[]{"messagechat"}, false, new Callable<List<MessageChat>>() {
            @Override
            public List<MessageChat> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfIdchat = CursorUtil.getColumnIndexOrThrow(_cursor, "idchat");
                    final int _cursorIndexOfIdgroup = CursorUtil.getColumnIndexOrThrow(_cursor, "idgroup");
                    final int _cursorIndexOfIduser = CursorUtil.getColumnIndexOrThrow(_cursor, "iduser");
                    final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
                    final int _cursorIndexOfTypechat = CursorUtil.getColumnIndexOrThrow(_cursor, "typechat");
                    final int _cursorIndexOfTimechat = CursorUtil.getColumnIndexOrThrow(_cursor, "timechat");
                    final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
                    final List<MessageChat> _result = new ArrayList<MessageChat>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        final MessageChat _item;
                        _item = new MessageChat();
                        _item.idchat = _cursor.getInt(_cursorIndexOfIdchat);
                        _item.idgroup = _cursor.getInt(_cursorIndexOfIdgroup);
                        _item.iduser = _cursor.getInt(_cursorIndexOfIduser);
                        if (_cursor.isNull(_cursorIndexOfMessage)) {
                            _item.message = null;
                        } else {
                            _item.message = _cursor.getString(_cursorIndexOfMessage);
                        }
                        _item.typechat = _cursor.getInt(_cursorIndexOfTypechat);
                        if (_cursor.isNull(_cursorIndexOfTimechat)) {
                            _item.timechat = null;
                        } else {
                            _item.timechat = _cursor.getString(_cursorIndexOfTimechat);
                        }
                        _item.status = _cursor.getInt(_cursorIndexOfStatus);
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

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
