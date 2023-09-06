package com.douqin.chatchitVN.ui.message.adapter;

import androidx.annotation.NonNull;

import com.douqin.chatchitVN.data.models.MessageChat;
import com.douqin.chatchitVN.databinding.ItChatRightImgBinding;

public class ItChatRightImgHolder extends MessageApdater.ViewHolder {
    ItChatRightImgBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, Object o, Object o1) {
        super.bindView(messageChat, o, o1);
//        ImageLoader imageLoader = Coil.imageLoader(viewBinding.getRoot().getContext());
//        ImageRequest request = new ImageRequest.Builder(viewBinding.getRoot().getContext())
//                .data("https://1.bp.blogspot.com/-iCnFX7eWVjs/XR9NQutHXcI/AAAAAAAAJ9k/ISWH3UXgJF8QJdsV6P9wh3agzOwOF_aYgCLcBGAs/s1600/cat-1285634_1920.png")
//                .crossfade(true)
//                .target(viewBinding.avatarMember)
//                .build();
//        imageLoader.enqueue(request);
    }

    public ItChatRightImgHolder(@NonNull ItChatRightImgBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}