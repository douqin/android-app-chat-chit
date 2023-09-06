package com.douqin.chatchitVN.ui.message;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.douqin.chatchitVN.data.database.room.database.AppDatabase;
import com.douqin.chatchitVN.data.repositories.chat.MessageRepository;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository messageRepository;
    public MessageViewModel(@NonNull Application application) {
        super(application);
        messageRepository = new MessageRepository(AppDatabase.gI(application).messageDao());
    }

}
