package com.douqin.chatchitVN.ui.message.adapter.message;

import android.view.View;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatRightVideoBinding;
import com.douqin.chatchitVN.ui.message.enums.MessageState;

public class ItChatRightVideoHolder extends MessageAdapter.ViewHolder {

    ItChatRightVideoBinding viewBinding;

    public ItChatRightVideoHolder(ItChatRightVideoBinding inflate) {
        super(inflate);
        this.viewBinding = inflate;
    }

    @Override
    public void bindView(MessageChat messageChat, User o, boolean isExistPreviousElement, boolean isExistNextElement) {
        super.bindView(messageChat, o, isExistPreviousElement, isExistNextElement);
//        viewBinding.message.setText("Loading Image ...");
        viewBinding.time.setText(messageChat.createdAt.toString());
        viewBinding.stateMessage.setVisibility(View.GONE);
        viewBinding.time.setVisibility(View.GONE);
        if (!isExistNextElement) {
            viewBinding.stateMessage.setVisibility(View.VISIBLE);
        }
        viewBinding.getRoot().setOnClickListener(v -> {
            if (viewBinding.time.getVisibility() == View.GONE) {
                viewBinding.time.setVisibility(View.VISIBLE);
                viewBinding.stateMessage.setVisibility(View.VISIBLE);
            } else {
                viewBinding.time.setVisibility(View.GONE);
                viewBinding.stateMessage.setVisibility(View.GONE);
            }
        });
        if (messageChat.status == MessageState.DEFAULT.getValue()) {
            this.viewBinding.stateMessage.setText("Received");
            MediaItem mediaItem = MediaItem.fromUri(messageChat.content);
            ExoPlayer player = new ExoPlayer.Builder(this.viewBinding.getRoot().getContext()).build();
            player.setMediaItem(mediaItem);
            this.viewBinding.videoMessage.setPlayer(player);
            player.prepare();
        } else if (messageChat.status == MessageState.SENDING.getValue()) {
            this.viewBinding.stateMessage.setText("Sending");
        } else if (messageChat.status == MessageState.DEL_BY_ADMIN.getValue()) {
            this.viewBinding.message.setText("Message has been deleted by admin");
        } else if (messageChat.status == MessageState.DEL_BY_OWNER.getValue()) {
            this.viewBinding.message.setText("Message has been revoked ");
        }
    }
}
