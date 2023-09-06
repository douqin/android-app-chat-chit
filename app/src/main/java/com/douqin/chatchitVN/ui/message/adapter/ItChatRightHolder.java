package com.douqin.chatchitVN.ui.message.adapter;

import androidx.annotation.NonNull;

import com.douqin.chatchitVN.data.models.MessageChat;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;

public class ItChatRightHolder extends MessageApdater.ViewHolder {
    ItChatRightBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, Object o, Object o1) {
        super.bindView(messageChat, o, o1);
        viewBinding.message.setText(messageChat.content);
    }

    public ItChatRightHolder(@NonNull ItChatRightBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}
