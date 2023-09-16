package com.douqin.chatchitVN.ui.message.adapter.message;

import androidx.annotation.NonNull;

import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;

public class ItChatRightHolder extends MessageAdapter.ViewHolder {
    ItChatRightBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, User o, Object o1) {
        super.bindView(messageChat, o, o1);
        viewBinding.message.setText(messageChat.content);
    }

    public ItChatRightHolder(@NonNull ItChatRightBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}
