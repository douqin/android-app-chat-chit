package com.douqin.chatchitVN.data.models.UI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;
import java.util.List;

public class GroupChat {
    public GroupChat(int idgroup, String name, int status, String avatar, String link, int type, String role, Date createAt) {
        this.idgroup = idgroup;
        this.name = name;
        this.status = status;
        this.avatar = avatar;
        this.type = type;
        this.link = link;
        this.role = role;
        this.createAt = createAt;
    }

    public int idgroup;
    public String name;
    public int status;
    public String avatar;

    public int type;
    public String link;
    public String role;
    public Date createAt;
    public
    LiveData<List<MessageChat>> messageChatList = new MutableLiveData<>();

    public
    LiveData<List<User>> members = new MutableLiveData<>();

}
