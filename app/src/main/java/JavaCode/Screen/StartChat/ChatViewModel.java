package JavaCode.Screen.StartChat;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import JavaCode.LocalData.Room.Database.AppDatabase;
import JavaCode.LocalData.Room.Entity.Relationship.GroupChatWithListMessage;
import JavaCode.Model.GroupChat;
import JavaCode.Repository.Chat.GroupRepository;
import JavaCode.Repository.Chat.MessageRepository;

public class ChatViewModel extends AndroidViewModel {
    private static final String TAG = "ChatViewModel";
    private GroupRepository groupRepository;
    private MessageRepository messageRepository;
    private LiveData<List<GroupChat>> mListGroupChat;
    private LiveData<GroupChat> currentGr;
    private LiveData<GroupChatWithListMessage> listMessageOfGroup;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        groupRepository = new GroupRepository(AppDatabase.gI(application).groupChatDao());
        messageRepository = new MessageRepository(AppDatabase.gI(application).messageDao());
        mListGroupChat = groupRepository.getListGroupChat();
        currentGr = groupRepository.getCurrentGr();
        listMessageOfGroup = groupRepository.getListMessageOfGroup();
    }

    public LiveData<List<GroupChat>> getListGroupChat() {
        return mListGroupChat;
    }

    public LiveData<GroupChat> getCurrentGr() {
        return currentGr;
    }

    public void openChat(GroupChat groupChat) {
        groupRepository.openChat(groupChat);
    }

    public LiveData<GroupChatWithListMessage> getListMess() {
        return listMessageOfGroup;
    }

    public void initDataFirstLogin() {
        groupRepository.initDataFirstLogin();
    }
}
