package JavaCode.Screen.StartChat;

import java.util.List;

import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageChat;
import JavaCode.network.Message;

public interface iResultChat {
    void addMessage(MessageChat messageChat);
    void addGroupChat(GroupChat groupChat);
    void initListGroupChat(List<GroupChat> groupChats);
}
