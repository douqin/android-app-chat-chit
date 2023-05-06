package JavaCode.Repository.Chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import JavaCode.LocalData.Room.Dao.GroupChatDao;
import JavaCode.LocalData.Room.Entity.Relationship.GroupChatWithListMessage;
import JavaCode.Model.GroupChat;

public class GroupRepository {

    public LiveData<GroupChatWithListMessage> getListMessageOfGroup() {
        return listMessageOfGroup;
    }

    private LiveData<List<GroupChat>> listGroupChat;

    public LiveData<List<GroupChat>> getListGroupChat() {
        return listGroupChat;
    }

    private MutableLiveData<GroupChat> currentGr;
    private LiveData<GroupChatWithListMessage> listMessageOfGroup;

    public LiveData<GroupChat> getCurrentGr() {
        return currentGr;
    }

    private GroupChatDao groupChatDao;

    public GroupRepository(GroupChatDao groupChatDao) {
        this.groupChatDao = groupChatDao;
        this.registerEmitter();
        listGroupChat = groupChatDao.getListGroupChat();
        currentGr = new MutableLiveData<>(null);
        listMessageOfGroup = new MutableLiveData<>();
    }

    private void registerEmitter() {
        // TODO:
    }

    public void openChat(GroupChat groupChat) {
        currentGr.setValue(groupChat);
        if (groupChat != null) {
            listMessageOfGroup = groupChatDao.getGroupChatWithMessage(groupChat.idgroup);
        }
    }

    public void initDataFirstLogin() {

    }
}
