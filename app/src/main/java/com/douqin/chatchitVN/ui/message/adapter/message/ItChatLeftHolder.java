package com.douqin.chatchitVN.ui.message.adapter.message;

import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatLeftBinding;

public class ItChatLeftHolder extends MessageAdapter.ViewHolder {

    ItChatLeftBinding itChatLeftBinding;

    @Override
    public void bindView(MessageChat messageChat, User user, boolean isExistPreviousElement, boolean isExistNextElement) {
        super.bindView(messageChat, user , isExistPreviousElement, isExistNextElement);
        itChatLeftBinding.message.setText(messageChat.content);
        itChatLeftBinding.nameUserChat.setText(String.valueOf(user.lastname));
        /*
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) itChatLeftBinding.getRoot().getLayoutParams();
        layoutParams.bottomMargin = 10;
        itChatLeftBinding.getRoot().setLayoutParams(layoutParams);
         */
    }

    public ItChatLeftHolder(@NonNull ItChatLeftBinding inflate) {
        super(inflate);
        this.itChatLeftBinding = inflate;
    }
}