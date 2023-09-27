package com.douqin.chatchitVN.ui.message.adapter.message;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.Reaction;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;
import com.douqin.chatchitVN.ui.message.enums.MessageReactType;
import com.douqin.chatchitVN.ui.message.enums.MessageState;

public class ItChatRightHolder extends MessageAdapter.ViewHolder {
    ItChatRightBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, User o, boolean isExistPreviousElement, boolean isExistNextElement) {
        super.bindView(messageChat, o, isExistPreviousElement, isExistNextElement);
        viewBinding.message.setText(messageChat.content);
        viewBinding.time.setText(messageChat.createdAt.toString());
        viewBinding.stateMessage.setVisibility(View.GONE);
        viewBinding.time.setVisibility(View.GONE);
        if (messageChat.status == MessageState.DEFAULT.getValue()) {
            this.viewBinding.stateMessage.setText("Received");
        } else if (messageChat.status == MessageState.SENDING.getValue()) {
            this.viewBinding.stateMessage.setText("Sending");
        } else if (messageChat.status == MessageState.DEL_BY_ADMIN.getValue()) {
            this.viewBinding.message.setText("Message has been deleted by admin");
        } else if (messageChat.status == MessageState.DEL_BY_OWNER.getValue()) {
            this.viewBinding.message.setText("Message has been revoked ");
        }
        boolean haha = false;
        for (Reaction reaction : messageChat.reactions) {
            switch (reaction.type) {
                case MessageReactType.HAHA:
                    if (!haha) {
                        haha = true;
                        ImageView imageView = new ImageView(this.viewBinding.getRoot().getContext());
                        int dpValue = 14;
                        int sizeInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, this.viewBinding.getRoot().getContext().getResources().getDisplayMetrics());
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(sizeInPixels, sizeInPixels));
                        imageView.setImageDrawable(ContextCompat.getDrawable(this.viewBinding.containerEmoji.getContext(), R.drawable.emoji_haha));
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(sizeInPixels, sizeInPixels));
                        this.viewBinding.containerEmoji.addView(imageView);
                    }
                    break;
                case MessageReactType.HUHU:
                    break;
                case MessageReactType.LOVE:
                    break;
                case MessageReactType.WOW:
                    break;
                case MessageReactType.ANGRY:
                    break;
                case MessageReactType.LIKE:
                    break;
            }
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
    }

    public ItChatRightHolder(@NonNull ItChatRightBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}
