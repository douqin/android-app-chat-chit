package com.douqin.chatchitVN.ui.message;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.data.database.room.database.AppDatabase;
import com.douqin.chatchitVN.data.models.UI.GroupChatWithMemberAndMessage;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.data.repositories.chat.GroupRepository;
import com.douqin.chatchitVN.data.repositories.chat.MessageRepository;
import com.douqin.chatchitVN.data.repositories.gif.GifRepository;
import com.douqin.chatchitVN.domain.GroupUserCase;
import com.douqin.chatchitVN.domain.MessageUserCase;
import com.douqin.chatchitVN.network.apis.RemoteData.TenorRemoteData;

import java.io.File;

public class MessageViewModel extends AndroidViewModel {

    private final MessageUserCase messageUserCase;

    private final GroupUserCase groupUserCase;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        messageUserCase = new MessageUserCase(new GroupRepository(AppDatabase.gI(application).groupChatDao(), AppDatabase.gI(application).memberDao(), AppDatabase.gI(application).userDao()), new MessageRepository(AppDatabase.gI(application).messageDao()), new GifRepository());
        groupUserCase = new GroupUserCase(new GroupRepository(AppDatabase.gI(application).groupChatDao(), AppDatabase.gI(application).memberDao(), AppDatabase.gI(application).userDao()), new MessageRepository(AppDatabase.gI(application).messageDao()));
    }

    public LiveData<GroupChatWithMemberAndMessage> getGrWithMemberAndMessage(int id) {
        return messageUserCase.getGrWithMemberAndMessage(id);
    }

    public User getInformationMember(int idMember) {
        return groupUserCase.getInformationMember(idMember);
    }

    public void getGifFromKeyword(String keyword) {
        this.messageUserCase.getGifFromKeyword(keyword);
    }

    public void sentMessage(int idgroup, File _message, String mimeType) {
        messageUserCase.sendMessage(idgroup, _message, mimeType);
    }

    public void sentMessage(int idgroup, String _message) {
        messageUserCase.sendMessage(idgroup, _message);
    }

    public LiveData<TenorRemoteData> getNewGif() {
        return this.messageUserCase.getNewGif();
    }
}
