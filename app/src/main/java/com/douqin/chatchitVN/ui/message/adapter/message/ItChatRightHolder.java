package com.douqin.chatchitVN.ui.message.adapter.message;

import android.view.View;
import androidx.annotation.NonNull;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;
import com.douqin.chatchitVN.ui.message.enums.MessageState;
import java.util.Date;

public class ItChatRightHolder extends MessageAdapter.ViewHolder {
    ItChatRightBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, User o, boolean isExistPreviousElement, boolean isExistNextElement) {
        super.bindView(messageChat, o, isExistPreviousElement, isExistNextElement);
        viewBinding.message.setText(messageChat.content);
        viewBinding.time.setText(messageChat.createdAt.toString());
        viewBinding.stateMessage.setVisibility(View.GONE);
        viewBinding.time.setVisibility(View.GONE);
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
        viewBinding.getRoot().setOnClickListener(v->{
            if(viewBinding.time.getVisibility() == View.GONE){
                viewBinding.time.setVisibility(View.VISIBLE);
                viewBinding.stateMessage.setVisibility(View.VISIBLE);
            } else {
                viewBinding.time.setVisibility(View.GONE);
                viewBinding.stateMessage.setVisibility(View.GONE);
            }
        });
    }

    public ItChatRightHolder(@NonNull ItChatRightBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}
