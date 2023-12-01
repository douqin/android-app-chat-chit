package com.douqin.chatchitVN.ui.group;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.databinding.ItGroupBinding;
import com.douqin.chatchitVN.domain.entities.GroupAndMemberAndMessage;
import com.douqin.chatchitVN.ui.group.event.iOnClickItemGroupChat;
import com.douqin.chatchitVN.ui.group.event.iOnLongClickItemGroupChat;
import com.douqin.chatchitVN.ui.message.enums.MessageType;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private final List<GroupAndMemberAndMessage> mGroupChat;
    private final iOnClickItemGroupChat onClickItemGroupChat;

    private final iOnLongClickItemGroupChat onLongClickItemGroupChat;
    GroupViewModel groupViewModel;

    public GroupAdapter(@NonNull List<GroupAndMemberAndMessage> mGroupChat, @NonNull iOnClickItemGroupChat onClickItemGroupChat, @NonNull iOnLongClickItemGroupChat onLongClickItemGroupChat, @NonNull GroupViewModel groupViewModel) {
        this.mGroupChat = mGroupChat;
        this.onClickItemGroupChat = onClickItemGroupChat;
        this.groupViewModel = groupViewModel;
        this.onLongClickItemGroupChat = onLongClickItemGroupChat;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new GroupAdapter.ViewHolder(ItGroupBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        GroupAndMemberAndMessage groupChat = mGroupChat.get(position);
        holder.bindView(groupChat, this.onClickItemGroupChat);
    }

    @Override
    public int getItemCount() {
        return mGroupChat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItGroupBinding inflate;

        public ViewHolder(ItGroupBinding inflate) {
            super(inflate.getRoot());
            this.inflate = inflate;
        }

        @SuppressLint("ResourceAsColor")
        public void bindView(GroupAndMemberAndMessage groupChatWithMemberAndMessage, iOnClickItemGroupChat onClickItemGroupChat) {
            this.inflate.nameGroup.setText(groupChatWithMemberAndMessage.group.name);
            this.inflate.layoutDetailsChat.setOnClickListener(v -> {
                onClickItemGroupChat.onClickItemGroupChat(groupChatWithMemberAndMessage.group);
            });
            this.inflate.layoutDetailsChat.setOnLongClickListener(v -> {
                onLongClickItemGroupChat.onClickItemGroupChat(groupChatWithMemberAndMessage.group);
                return true;
            });
            if (groupChatWithMemberAndMessage.memberWithMessages != null) {
                if (groupChatWithMemberAndMessage.memberWithMessages.messageChat.type == MessageType.TEXT) {
                    this.inflate.nameUser.setText(groupViewModel.getInformationMember(groupChatWithMemberAndMessage.memberWithMessages.member.id).lastname + ": ");
                    this.inflate.messageUser.setText(groupChatWithMemberAndMessage.memberWithMessages.messageChat.content);
                } else if (groupChatWithMemberAndMessage.memberWithMessages.messageChat.type == MessageType.VIDEO) {
                    this.inflate.nameUser.setText(groupViewModel.getInformationMember(groupChatWithMemberAndMessage.memberWithMessages.member.id).lastname + ": ");
                    this.inflate.messageUser.setText("Sent VIDEO");
                } else if (groupChatWithMemberAndMessage.memberWithMessages.messageChat.type == MessageType.GIF) {
                    this.inflate.nameUser.setText(groupViewModel.getInformationMember(groupChatWithMemberAndMessage.memberWithMessages.member.id).lastname + ": ");
                    this.inflate.messageUser.setText("Sent GIF");
                } else if (groupChatWithMemberAndMessage.memberWithMessages.messageChat.type == MessageType.IMAGE) {
                    this.inflate.nameUser.setText(groupViewModel.getInformationMember(groupChatWithMemberAndMessage.memberWithMessages.member.id).lastname + ": ");
                    this.inflate.messageUser.setText("Sent IMAGE");
                }
            } else {
                this.inflate.nameUser.setText("");
                this.inflate.messageUser.setText("Nothing in here, let's start conversation");
            }
        }
    }
}
