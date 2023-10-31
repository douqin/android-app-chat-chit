package com.douqin.chatchitVN.ui.message.adapter.message;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatLeftImgBinding;
import com.douqin.chatchitVN.ui.base.onDoubleClickListener;
import com.douqin.chatchitVN.ui.message.MessageViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItChatLeftImageHolder extends MessageAdapter.ViewHolder {
    @Override
    public void bindView(MessageChat messageChat, User o, boolean isExistPreviousElement, boolean isExistNextElement) {
        super.bindView(messageChat, o, isExistPreviousElement, isExistNextElement);
        this.itChatLeftImgBinding.imgMess.setMaxWidth(MotherCanvas.width);
        this.itChatLeftImgBinding.nameUserChat.setText(o.lastname);
        Observable.fromCallable(() -> {
                    return Glide.with(ItChatLeftImageHolder.this.itemView.getContext())
                            .load(messageChat.content)
                            .override(MotherCanvas.width / 2)
                            .fitCenter()
                            .submit()
                            .get();
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Drawable o) {
                        ItChatLeftImageHolder.this.itChatLeftImgBinding.imgMess.setImageDrawable(o);
                        ItChatLeftImageHolder.this.itChatLeftImgBinding.imgMess.setOnClickListener(new onDoubleClickListener(
                                () -> {
                                    ItChatLeftImageHolder.this.messageViewModel.openMessageInDetailsImageScreen(messageChat);
                                }
                        ));
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    ItChatLeftImgBinding itChatLeftImgBinding;

    MessageViewModel messageViewModel;

    public ItChatLeftImageHolder(@NonNull ItChatLeftImgBinding viewBinding, MessageViewModel messageViewModel) {
        super(viewBinding);
        this.itChatLeftImgBinding = viewBinding;
        this.messageViewModel = messageViewModel;
    }
}