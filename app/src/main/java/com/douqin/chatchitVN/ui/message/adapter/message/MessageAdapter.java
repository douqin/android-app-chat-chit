package com.douqin.chatchitVN.ui.message.adapter.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.douqin.chatchitVN.databinding.ItChatLeftBinding;
import com.douqin.chatchitVN.databinding.ItChatLeftImgBinding;
import com.douqin.chatchitVN.databinding.ItChatRightBinding;
import com.douqin.chatchitVN.databinding.ItChatRightImgBinding;
import com.douqin.chatchitVN.databinding.ItChatRightVideoBinding;
import com.douqin.chatchitVN.ui.base.ActionItem;
import com.douqin.chatchitVN.ui.base.QuickActionMenu;
import com.douqin.chatchitVN.ui.message.MessageViewModel;
import com.douqin.chatchitVN.ui.message.enums.MessageType;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public MessageAdapter(MessageViewModel messageViewModel, Context mContext) {
        this.messageViewModel = messageViewModel;
        this.mContext = mContext;
    }

    private final MessageViewModel messageViewModel;

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

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MessageAdapter.NOT_ME_TYPE_CHAT_STR:
                return new ItChatLeftHolder(ItChatLeftBinding.inflate(layoutInflater, parent, false));
            case MessageAdapter.NOT_ME_TYPE_CHAT_IMG:
                return new ItChatLeftImageHolder(ItChatLeftImgBinding.inflate(layoutInflater, parent, false), messageViewModel);
            case MessageAdapter.ME_TYPE_CHAT_IMG:
                return new ItChatRightImgHolder(ItChatRightImgBinding.inflate(layoutInflater, parent, false), messageViewModel);
            case MessageAdapter.ME_TYPE_CHAT_STR:
                return new ItChatRightHolder(ItChatRightBinding.inflate(layoutInflater, parent, false));
            case MessageAdapter.ME_TYPE_CHAT_VIDEO:
                return new ItChatRightVideoHolder(ItChatRightVideoBinding.inflate(layoutInflater, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageChat messageChat = this.listDiffer.getCurrentList().get(position);
        holder.bindView(messageChat, messageViewModel.getInformationMember(this.listDiffer.getCurrentList().get(position).idMember),
                false,
                !(position + 1 > this.listDiffer.getCurrentList().size() - 1));
    }

    @Override
    public int getItemViewType(int position) {
        if (messageViewModel.getInformationMember(this.listDiffer.getCurrentList().get(position).idMember).iduser == MeManager.gI().getMySelf().idUser) {
            this.listDiffer.getCurrentList().get(position).me = true;
        }
        switch (this.listDiffer.getCurrentList().get(position).type) {
            case MessageType.TEXT:
                if (this.listDiffer.getCurrentList().get(position).me) {
                    return MessageAdapter.ME_TYPE_CHAT_STR;
                }
                return MessageAdapter.NOT_ME_TYPE_CHAT_STR;
            case MessageType.IMAGE:
                if (this.listDiffer.getCurrentList().get(position).me) {
                    return MessageAdapter.ME_TYPE_CHAT_IMG;
                }
                return MessageAdapter.NOT_ME_TYPE_CHAT_IMG;
            case MessageType.VIDEO:
                if (this.listDiffer.getCurrentList().get(position).me) {
                    return MessageAdapter.ME_TYPE_CHAT_VIDEO;
                }
                return MessageAdapter.NOT_ME_TYPE_CHAT_VIDEO;
            case MessageType.GIF:
                if (this.listDiffer.getCurrentList().get(position).me) {
                    return MessageAdapter.ME_TYPE_CHAT_GIF;
                }
                return MessageAdapter.NOT_ME_TYPE_CHAT_GIF;
        }
        throw new RuntimeException("TYPE MESSAGE NOT DEFINE");
    }

    @Override
    public int getItemCount() {
        return this.listDiffer.getCurrentList().size();
    }

    public void submitList(List<MessageChat> newList) {
        this.listDiffer.submitList(newList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(ViewBinding inflate) {
            super(inflate.getRoot());
        }

        public void bindView(MessageChat messageChat, User o, boolean isExistPreviousElement, boolean isExistNextElement) {
            this.itemView.setOnLongClickListener(view -> {
                QuickActionMenu quickActionMenu = new QuickActionMenu(itemView.getContext(), view);
                quickActionMenu.addActionItem(new ActionItem(null, ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.emoji_love), ""));
                quickActionMenu.addActionItem(new ActionItem(null, ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.emoji_haha), ""));
                quickActionMenu.addActionItem(new ActionItem(null, ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.emoji_angry), ""));
                quickActionMenu.addActionItem(new ActionItem(null, ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.emoji_huhu), ""));
                quickActionMenu.showAtView();
                return false;
            });
        }
    }


    private DiffUtil.ItemCallback<MessageChat> a = new DiffUtil.ItemCallback<MessageChat>() {

        @Override
        public boolean areItemsTheSame(@NonNull MessageChat oldItem, @NonNull MessageChat newItem) {
            return oldItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MessageChat oldItem, @NonNull MessageChat newItem) {
            return oldItem.equals(newItem);
        }
    };
    private final AsyncListDiffer<MessageChat> listDiffer = new AsyncListDiffer<MessageChat>(this, a);
}
