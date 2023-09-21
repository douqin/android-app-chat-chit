package com.douqin.chatchitVN.ui.group;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.data.database.room.database.AppDatabase;
import com.douqin.chatchitVN.data.models.UI.Member;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.data.repositories.chat.GroupRepository;
import com.douqin.chatchitVN.data.repositories.chat.MessageRepository;
import com.douqin.chatchitVN.domain.GroupUserCase;
import com.douqin.chatchitVN.domain.entities.GroupAndMemberAndMessage;

import java.util.List;

public class GroupViewModel extends AndroidViewModel {

    private final GroupUserCase groupUserCase;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        this.groupUserCase = new GroupUserCase(new GroupRepository(AppDatabase.gI(application).groupChatDao(), AppDatabase.gI(application).memberDao(), AppDatabase.gI(application).userDao()),
                new MessageRepository(AppDatabase.gI(application).messageDao()));
        this.groupUserCase.initBaseData();
    }

    public LiveData<List<GroupAndMemberAndMessage>> getListGroupChat() {
        return groupUserCase.getListGroupWithLastMessage();
    }

    public User getInformationMember(int idMember) {
        return this.groupUserCase.getInformationMember(idMember);
    }


}
