package com.douqin.chatchitVN.ui.message.adapter.message;

import androidx.annotation.NonNull;

import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;
import com.douqin.chatchitVN.ui.message.enums.MessageState;

public class ItChatRightHolder extends MessageAdapter.ViewHolder {
    ItChatRightBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, User o, Object o1) {
        super.bindView(messageChat, o, o1);
        viewBinding.message.setText(messageChat.content);
        if(messageChat.status == MessageState.DEFAULT.getValue()){
            this.viewBinding.stateMessage.setText("Received");
        }
        else if(messageChat.status == MessageState.SENDING.getValue()){
            this.viewBinding.stateMessage.setText("Sending");
        } else if(messageChat.status == MessageState.DEL_BY_ADMIN.getValue()){
            this.viewBinding.message.setText("Message has been deleted by admin");
        }else if(messageChat.status == MessageState.DEL_BY_OWNER.getValue()){
            this.viewBinding.message.setText("Message has been revoked ");
        }
    }

    public ItChatRightHolder(@NonNull ItChatRightBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}
