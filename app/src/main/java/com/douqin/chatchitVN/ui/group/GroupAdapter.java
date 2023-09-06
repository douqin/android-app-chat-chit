package com.douqin.chatchitVN.ui.group;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.data.models.GroupChat;
import com.douqin.chatchitVN.databinding.ItGroupBinding;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private final List<GroupChat> mGroupChat;
    private final iOnClickItemGroupChat onClickItemGroupChat;

    public GroupAdapter(List<GroupChat> mGroupChat, iOnClickItemGroupChat onClickItemGroupChat) {
        if (mGroupChat != null) {
            this.mGroupChat = mGroupChat;
        } else this.mGroupChat = new ArrayList<>();
        this.onClickItemGroupChat = onClickItemGroupChat;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new GroupAdapter.ViewHolder(ItGroupBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        GroupChat groupChat = mGroupChat.get(position);
        holder.bindView(groupChat, this.onClickItemGroupChat);
    }

    @Override
    public int getItemCount() {
        return mGroupChat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItGroupBinding inflate;

        public ViewHolder(ItGroupBinding inflate) {
            super(inflate.getRoot());
            this.inflate = inflate;
        }

        @SuppressLint("ResourceAsColor")
        public void bindView(GroupChat groupChat, iOnClickItemGroupChat onClickItemGroupChat) {
            this.inflate.nameGroup.setText(groupChat.name);
            this.inflate.nameUser.setText("");
            this.inflate.messageUser.setText("Loading Message ...");
            this.inflate.layoutDetailsChat.setOnClickListener(v -> {
                onClickItemGroupChat.onClickItemGroupChat(groupChat);
            });
//            if(groupChat.messageChatList.getValue() != null) {
//                if (groupChat.messageChatList.getValue().size() >= 1) {
//                    this.inflate.nameUser.setText(groupChat.messageChatList.getValue().get(groupChat.messageChatList.getValue().size() - 1).idMember);
//                    this.inflate.messageUser.setText(String.format("%s : %s", groupChat.members.getValue().get(groupChat.messageChatList.getValue().size() - 1).lastname, groupChat.messageChatList.getValue().get(groupChat.messageChatList.getValue().size() - 1).content));
//                } else {
//                    this.inflate.nameUser.setText("");
//                    this.inflate.messageUser.setText("Chưa có ai nhắn tin, Hãy vào bắt đầu nhắn tin nào");
//                }
//            }
        }
    }
}
