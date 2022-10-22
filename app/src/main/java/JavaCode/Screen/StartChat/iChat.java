package JavaCode.Screen.StartChat;

import androidx.lifecycle.LiveData;

import java.util.List;

import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageChat;

public interface iChat {
    LiveData<List<GroupChat>> getListGroupChat();
    LiveData<GroupChat> getCurrentGr();
    LiveData<List<MessageChat>> getListMessage();
    void loadListChatUser();
    void openChat(GroupChat groupChat);
}
