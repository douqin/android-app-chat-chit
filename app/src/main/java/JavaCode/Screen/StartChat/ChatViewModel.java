package JavaCode.Screen.StartChat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageChat;
import JavaCode.networklogic.ReadMessServer;
import JavaCode.networklogic.Service;

public class ChatViewModel extends AndroidViewModel implements iChat, iResultChat {
    private static final String TAG = "ChatViewModel";
    private  MutableLiveData<List<GroupChat>> mListGroupChat = new MutableLiveData<>();
    private  MutableLiveData<GroupChat> currentGr = new MutableLiveData<>();
    private  MutableLiveData<List<MessageChat>> mListMessage = new MutableLiveData<>(); // khong can

    @Override
    public LiveData<GroupChat> getCurrentGr() {
        return currentGr;
    }
    public ChatViewModel(@NonNull Application application) {
        super(application);
        ReadMessServer.setStartChat(this);
    }

    @Override
    public LiveData<List<GroupChat>> getListGroupChat() {
        return mListGroupChat;
    }

    @Override
    public LiveData<List<MessageChat>> getListMessage() {
        return mListMessage;
    }

    @Override
    public void loadListChatUser() {
        Service.gI().getListGrChat();
    }

    @Override
    public void openChat(GroupChat groupChat) {
        currentGr.setValue(groupChat);
        if(groupChat != null) {
            for (int i = 0; i < mListGroupChat.getValue().size(); i++) {
                if (groupChat.idGroup == mListGroupChat.getValue().get(i).idGroup) {
                    this.mListMessage.setValue(mListMessage.getValue());
                }
            }
        }
    }

    @Override
    public void addMessage(MessageChat messageChat) {
        int id = messageChat.groupChat.idGroup;
        for (int i = 0; i < mListGroupChat.getValue().size(); i++) {
            if (id == mListGroupChat.getValue().get(i).idGroup) {
                mListGroupChat.getValue().get(i).listChatMessage.add(messageChat);
                if (currentGr != null) {
                    if (id == currentGr.getValue().idGroup) {
                        mListMessage.setValue(mListGroupChat.getValue().get(i).listChatMessage);
                    }
                }
            }
        }
    }

    @Override
    public void addGroupChat(GroupChat groupChat) {
        Objects.requireNonNull(mListGroupChat.getValue()).add(groupChat);
    }

    @Override
    public void initListGroupChat(List<GroupChat> groupChats) {

        mListGroupChat.postValue(groupChats);
        Log.e(TAG,"xxx");
    }
}
