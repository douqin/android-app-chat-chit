package com.douqin.chatchitVN.ui.message.adapter;

import androidx.annotation.NonNull;

import com.douqin.chatchitVN.data.models.MessageChat;
import com.douqin.chatchitVN.databinding.ItChatLeftBinding;

public class ItChatLeftHolder extends MessageApdater.ViewHolder {

    ItChatLeftBinding itChatLeftBinding;

    @Override
    public void bindView(MessageChat messageChat, Object o, Object o1) {
        super.bindView(messageChat, o, o1);
        itChatLeftBinding.message.setText(messageChat.content);
        itChatLeftBinding.nameUserChat.setText(String.valueOf(messageChat.idMember));
//        ImageLoader imageLoader = Coil.imageLoader(itChatLeftBinding.getRoot().getContext());
//        ImageRequest request = new ImageRequest.Builder(itChatLeftBinding.getRoot().getContext())
//                .data("https://1.bp.blogspot.com/-iCnFX7eWVjs/XR9NQutHXcI/AAAAAAAAJ9k/ISWH3UXgJF8QJdsV6P9wh3agzOwOF_aYgCLcBGAs/s1600/cat-1285634_1920.png")
//                .crossfade(true)
//                .target(itChatLeftBinding.avatarMember)
//                .build();
//        imageLoader.enqueue(request);
    }

    public ItChatLeftHolder(@NonNull ItChatLeftBinding inflate) {
        super(inflate);
        this.itChatLeftBinding = inflate;
    }
}