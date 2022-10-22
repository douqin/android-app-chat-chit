package JavaCode.Model;

import java.util.ArrayList;
import java.util.List;

public class GroupChat {
    public GroupChat(int idGroup, String name, int status, String avatar, List<MessageChat> listChatMessage) {
        this.idGroup = idGroup;
        this.name = name;
        this.status = status;
        this.avatar = avatar;
        if (listChatMessage != null)
        this.listChatMessage = listChatMessage;
        else  this.listChatMessage = new ArrayList<>();
    }
    public GroupChat(int idGroup, String name, int status, String avatar) {
        this.idGroup = idGroup;
        this.name = name;
        this.status = status;
        this.avatar = avatar;
        this.listChatMessage = new ArrayList<>();
    }
    public int idGroup;
    public String name;
    public int status;
    public String avatar;
    public List<MessageChat> listChatMessage;

}
