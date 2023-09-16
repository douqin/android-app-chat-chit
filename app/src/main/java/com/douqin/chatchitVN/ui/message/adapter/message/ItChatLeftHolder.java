package com.douqin.chatchitVN.ui.message.adapter.message;

import androidx.annotation.NonNull;

import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatLeftBinding;

public class ItChatLeftHolder extends MessageAdapter.ViewHolder {

    ItChatLeftBinding itChatLeftBinding;

    @Override
    public void bindView(MessageChat messageChat, User user, Object o1) {
        super.bindView(messageChat, user, o1);
        itChatLeftBinding.message.setText(messageChat.content);
        itChatLeftBinding.nameUserChat.setText(String.valueOf(user.lastname));
    }

    public ItChatLeftHolder(@NonNull ItChatLeftBinding inflate) {
        super(inflate);
        this.itChatLeftBinding = inflate;
    }
}