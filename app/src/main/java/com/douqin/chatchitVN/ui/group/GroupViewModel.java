package com.douqin.chatchitVN.ui.group;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.data.database.room.database.AppDatabase;
import com.douqin.chatchitVN.data.models.GroupChat;
import com.douqin.chatchitVN.data.repositories.chat.GroupRepository;

import java.util.List;

public class GroupViewModel extends AndroidViewModel {
    private GroupRepository groupRepository;
    private LiveData<List<GroupChat>> mListGroupChat;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        groupRepository = new GroupRepository(AppDatabase.gI(application).groupChatDao(), AppDatabase.gI(application).memberDao());
    }
    public LiveData<List<GroupChat>> getListGroupChat() {
        return mListGroupChat;
    }

}
