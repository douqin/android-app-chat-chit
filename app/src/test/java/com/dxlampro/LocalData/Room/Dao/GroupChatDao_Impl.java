package com.dxlampro.LocalData.Room.Dao;

import android.database.Cursor;

import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
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

import JavaCode.LocalData.Room.Dao.GroupChatDao;
import JavaCode.LocalData.Room.Entity.Relationship.GroupChatWithListMessage;
import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageChat;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GroupChatDao_Impl implements GroupChatDao {
    private final RoomDatabase __db;

    private final EntityInsertionAdapter<GroupChat> __insertionAdapterOfGroupChat;

    public GroupChatDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfGroupChat = new EntityInsertionAdapter<GroupChat>(__db) {
            @Override
            public String createQuery() {
                return "INSERT OR IGNORE INTO `groupchat` (`idgroup`,`name`,`status`,`avatar`) VALUES (?,?,?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, GroupChat value) {
                stmt.bindLong(1, value.idgroup);
                if (value.name == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.name);
                }
                stmt.bindLong(3, value.status);
                if (value.avatar == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.avatar);
                }
            }
        };
    }

    @Override
    public void insertGroup(final GroupChat groupChat) {
        __db.assertNotSuspendingTransaction();
        __db.beginTransaction();
        try {
            __insertionAdapterOfGroupChat.insert(groupChat);
            __db.setTransactionSuccessful();
        } finally {
            __db.endTransaction();
        }
    }

    @Override
    public LiveData<GroupChatWithListMessage> getGroupChatWithMessage(final int idgroup) {
        final String _sql = "SELECT * FROM groupchat WHERE idgroup = ? LIMIT 1";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
        int _argIndex = 1;
        _statement.bindLong(_argIndex, idgroup);
        return __db.getInvalidationTracker().createLiveData(new String[]{"messagechat", "groupchat"}, true, new Callable<GroupChatWithListMessage>() {
            @Override
            public GroupChatWithListMessage call() throws Exception {
                __db.beginTransaction();
                try {
                    final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
                    try {
                        final int _cursorIndexOfIdgroup = CursorUtil.getColumnIndexOrThrow(_cursor, "idgroup");
                        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
                        final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
                        final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
                        final LongSparseArray<ArrayList<MessageChat>> _collectionListMessage = new LongSparseArray<ArrayList<MessageChat>>();
                        while (_cursor.moveToNext()) {
                            if (!_cursor.isNull(_cursorIndexOfIdgroup)) {
                                final long _tmpKey = _cursor.getLong(_cursorIndexOfIdgroup);
                                ArrayList<MessageChat> _tmpListMessageCollection = _collectionListMessage.get(_tmpKey);
                                if (_tmpListMessageCollection == null) {
                                    _tmpListMessageCollection = new ArrayList<MessageChat>();
                                    _collectionListMessage.put(_tmpKey, _tmpListMessageCollection);
                                }
                            }
                        }
                        _cursor.moveToPosition(-1);
                        __fetchRelationshipmessagechatAsJavaCodeModelMessageChat(_collectionListMessage);
                        final GroupChatWithListMessage _result;
                        if (_cursor.moveToFirst()) {
                            final GroupChat _tmpGroupChat;
                            if (!(_cursor.isNull(_cursorIndexOfIdgroup) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfStatus) && _cursor.isNull(_cursorIndexOfAvatar))) {
                                final int _tmpIdgroup;
                                _tmpIdgroup = _cursor.getInt(_cursorIndexOfIdgroup);
                                final String _tmpName;
                                if (_cursor.isNull(_cursorIndexOfName)) {
                                    _tmpName = null;
                                } else {
                                    _tmpName = _cursor.getString(_cursorIndexOfName);
                                }
                                final int _tmpStatus;
                                _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
                                final String _tmpAvatar;
                                if (_cursor.isNull(_cursorIndexOfAvatar)) {
                                    _tmpAvatar = null;
                                } else {
                                    _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
                                }
                                _tmpGroupChat = new GroupChat(_tmpIdgroup, _tmpName, _tmpStatus, _tmpAvatar);
                            } else {
                                _tmpGroupChat = null;
                            }
                            ArrayList<MessageChat> _tmpListMessageCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfIdgroup)) {
                                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfIdgroup);
                                _tmpListMessageCollection_1 = _collectionListMessage.get(_tmpKey_1);
                            }
                            if (_tmpListMessageCollection_1 == null) {
                                _tmpListMessageCollection_1 = new ArrayList<MessageChat>();
                            }
                            _result = new GroupChatWithListMessage();
                            _result.groupChat = _tmpGroupChat;
                            _result.listMessage = _tmpListMessageCollection_1;
                        } else {
                            _result = null;
                        }
                        __db.setTransactionSuccessful();
                        return _result;
                    } finally {
                        _cursor.close();
                    }
                } finally {
                    __db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override
    public LiveData<List<GroupChat>> getListGroupChat() {
        final String _sql = "SELECT * FROM groupchat";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        return __db.getInvalidationTracker().createLiveData(new String[]{"groupchat"}, false, new Callable<List<GroupChat>>() {
            @Override
            public List<GroupChat> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfIdgroup = CursorUtil.getColumnIndexOrThrow(_cursor, "idgroup");
                    final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
                    final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
                    final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
                    final List<GroupChat> _result = new ArrayList<GroupChat>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        final GroupChat _item;
                        final int _tmpIdgroup;
                        _tmpIdgroup = _cursor.getInt(_cursorIndexOfIdgroup);
                        final String _tmpName;
                        if (_cursor.isNull(_cursorIndexOfName)) {
                            _tmpName = null;
                        } else {
                            _tmpName = _cursor.getString(_cursorIndexOfName);
                        }
                        final int _tmpStatus;
                        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
                        final String _tmpAvatar;
                        if (_cursor.isNull(_cursorIndexOfAvatar)) {
                            _tmpAvatar = null;
                        } else {
                            _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
                        }
                        _item = new GroupChat(_tmpIdgroup, _tmpName, _tmpStatus, _tmpAvatar);
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

    private void __fetchRelationshipmessagechatAsJavaCodeModelMessageChat(
            final LongSparseArray<ArrayList<MessageChat>> _map) {
        if (_map.isEmpty()) {
            return;
        }
        // check if the size is too big, if so divide;
        if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
            LongSparseArray<ArrayList<MessageChat>> _tmpInnerMap = new LongSparseArray<ArrayList<MessageChat>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int _tmpIndex = 0;
            int _mapIndex = 0;
            final int _limit = _map.size();
            while (_mapIndex < _limit) {
                _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
                _mapIndex++;
                _tmpIndex++;
                if (_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
                    __fetchRelationshipmessagechatAsJavaCodeModelMessageChat(_tmpInnerMap);
                    _tmpInnerMap = new LongSparseArray<ArrayList<MessageChat>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    _tmpIndex = 0;
                }
            }
            if (_tmpIndex > 0) {
                __fetchRelationshipmessagechatAsJavaCodeModelMessageChat(_tmpInnerMap);
            }
            return;
        }
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT `idchat`,`idgroup`,`iduser`,`message`,`typechat`,`timechat`,`status` FROM `messagechat` WHERE `idgroup` IN (");
        final int _inputSize = _map.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final int _argCount = 0 + _inputSize;
        final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (int i = 0; i < _map.size(); i++) {
            long _item = _map.keyAt(i);
            _stmt.bindLong(_argIndex, _item);
            _argIndex++;
        }
        final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
        try {
            final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "idgroup");
            if (_itemKeyIndex == -1) {
                return;
            }
            final int _cursorIndexOfIdchat = 0;
            final int _cursorIndexOfIdgroup = 1;
            final int _cursorIndexOfIduser = 2;
            final int _cursorIndexOfMessage = 3;
            final int _cursorIndexOfTypechat = 4;
            final int _cursorIndexOfTimechat = 5;
            final int _cursorIndexOfStatus = 6;
            while (_cursor.moveToNext()) {
                if (!_cursor.isNull(_itemKeyIndex)) {
                    final long _tmpKey = _cursor.getLong(_itemKeyIndex);
                    ArrayList<MessageChat> _tmpRelation = _map.get(_tmpKey);
                    if (_tmpRelation != null) {
                        final MessageChat _item_1;
                        _item_1 = new MessageChat();
                        _item_1.idchat = _cursor.getInt(_cursorIndexOfIdchat);
                        _item_1.idgroup = _cursor.getInt(_cursorIndexOfIdgroup);
                        _item_1.iduser = _cursor.getInt(_cursorIndexOfIduser);
                        if (_cursor.isNull(_cursorIndexOfMessage)) {
                            _item_1.message = null;
                        } else {
                            _item_1.message = _cursor.getString(_cursorIndexOfMessage);
                        }
                        _item_1.typechat = _cursor.getInt(_cursorIndexOfTypechat);
                        if (_cursor.isNull(_cursorIndexOfTimechat)) {
                            _item_1.timechat = null;
                        } else {
                            _item_1.timechat = _cursor.getString(_cursorIndexOfTimechat);
                        }
                        _item_1.status = _cursor.getInt(_cursorIndexOfStatus);
                        _tmpRelation.add(_item_1);
                    }
                }
            }
        } finally {
            _cursor.close();
        }
    }
}
