package com.douqin.chatchitVN.ui.message.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.data.models.MessageChat;
import com.douqin.chatchitVN.databinding.ItChatLeftBinding;
import com.douqin.chatchitVN.databinding.ItChatLeftImgBinding;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;
import com.douqin.chatchitVN.databinding.ItChatRightImgBinding;
import com.douqin.chatchitVN.ui.base.ActionItem;
import com.douqin.chatchitVN.ui.base.QuickActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageApdater extends RecyclerView.Adapter<MessageApdater.ViewHolder> {
    private final List<MessageChat> mMessageChat;
    private final Context mContext;
    public final static int ME_TYPE_CHAT_IMG = 1;
    public final static int ME_TYPE_CHAT_STR = 2;
    public final static int ME_TYPE_CHAT_VIDEO = 3;
    public final static int ME_TYPE_CHAT_HYPER_LINK = 4;
    public final static int ME_TYPE_CHAT_GIF = 5;
    public final static int NOT_ME_TYPE_CHAT_IMG = 6;
    public final static int NOT_ME_TYPE_CHAT_STR = 7;
    public final static int NOT_ME_TYPE_CHAT_VIDEO = 8;
    public final static int NOT_ME_TYPE_CHAT_HYPER_LINK = 9;
    public final static int NOT_ME_TYPE_CHAT_GIF = 10;

    public MessageApdater(List<MessageChat> mMessageChat, Context mContext) {
        if (mMessageChat != null)
            this.mMessageChat = mMessageChat;
        else {
            this.mMessageChat = new ArrayList<>();
        }
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MessageApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MessageApdater.NOT_ME_TYPE_CHAT_STR:
                return new ItChatLeftHolder(ItChatLeftBinding.inflate(layoutInflater));
            case MessageApdater.NOT_ME_TYPE_CHAT_IMG:
                return new ItChatLeftImageHolder(ItChatLeftImgBinding.inflate(layoutInflater));
            case MessageApdater.ME_TYPE_CHAT_IMG:
                return new ItChatRightImgHolder(ItChatRightImgBinding.inflate(layoutInflater));
            case MessageApdater.ME_TYPE_CHAT_STR:
                return new ItChatRightHolder(ItChatRightBinding.inflate(layoutInflater));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageChat messageChat = Objects.requireNonNull(mMessageChat).get(position);
        holder.bindView(messageChat, new Object(), new Object());
    }

    @Override
    public int getItemViewType(int position) {
        //     TEXT = 0,
        //    IMAGE = 1,
        //    VIDEO = 2,
        //    GIF = 3
        //    ANOTHER_FILE = 4
        switch (mMessageChat.get(position).type) {
            case 0:
                if (mMessageChat.get(position).me) {
                    return MessageApdater.ME_TYPE_CHAT_STR;
                }
                return MessageApdater.NOT_ME_TYPE_CHAT_STR;
            case 1:
                if (mMessageChat.get(position).me) {
                    return MessageApdater.ME_TYPE_CHAT_IMG;
                }
                return MessageApdater.NOT_ME_TYPE_CHAT_IMG;
            case 2:
                if (mMessageChat.get(position).me) {
                    return MessageApdater.ME_TYPE_CHAT_VIDEO;
                }
                return MessageApdater.NOT_ME_TYPE_CHAT_VIDEO;
            case 3:
                if (mMessageChat.get(position).me) {
                    return MessageApdater.ME_TYPE_CHAT_GIF;
                }
                return MessageApdater.NOT_ME_TYPE_CHAT_GIF;
        }
        throw new RuntimeException("TYPE MESSAGE NOT DEFINE");
    }

    @Override
    public int getItemCount() {
        return mMessageChat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(ViewBinding inflate) {
            super(inflate.getRoot());
        }

        public void bindView(MessageChat messageChat, Object o, Object o1) {
            this.itemView.setOnLongClickListener(view -> {
                QuickActionMenu quickActionMenu = new QuickActionMenu(itemView.getContext());
                quickActionMenu.addActionItem(new ActionItem(null,ContextCompat.getDrawable(this.itemView.getContext(),R.drawable.ok_pepe_img), ""));
                quickActionMenu.addActionItem(new ActionItem(null,ContextCompat.getDrawable(this.itemView.getContext(),R.drawable.love), ""));
                quickActionMenu.addActionItem(new ActionItem(null,ContextCompat.getDrawable(this.itemView.getContext(),R.drawable.haha), ""));
                quickActionMenu.addActionItem(new ActionItem(null, ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.huuh),"" ));
                quickActionMenu.addActionItem(new ActionItem(null,ContextCompat.getDrawable(this.itemView.getContext(),R.drawable.angry), ""));
                quickActionMenu.show(this.itemView);
                return false;
            });
        }
    }
}
