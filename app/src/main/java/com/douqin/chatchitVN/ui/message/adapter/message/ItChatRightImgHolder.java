package com.douqin.chatchitVN.ui.message.adapter.message;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.databinding.ItChatRightImgBinding;
import com.douqin.chatchitVN.ui.message.enums.MessageState;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItChatRightImgHolder extends MessageAdapter.ViewHolder {
    ItChatRightImgBinding viewBinding;

    @Override
    public void bindView(MessageChat messageChat, User o, Object o1) {
        super.bindView(messageChat, o, o1);
        viewBinding.message.setText("Loading Image");
        if(messageChat.status == MessageState.DEFAULT.getValue()){
            Observable.fromCallable(() -> Glide.with(ItChatRightImgHolder.this.itemView.getContext())
                            .load(messageChat.content)
                            .override(MotherCanvas.width / 2)
                            .fitCenter()
                            .submit()
                            .get()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Drawable>() {

                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull Drawable o) {
                            viewBinding.message.setVisibility(View.GONE);
                            viewBinding.imgMess.setVisibility(View.VISIBLE);
                            ItChatRightImgHolder.this.viewBinding.imgMess.setImageDrawable(o);
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            viewBinding.message.setText("Error when load image");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            this.viewBinding.stateMessage.setText("Received");
        }
        else if(messageChat.status == MessageState.SENDING.getValue()){
            this.viewBinding.stateMessage.setText("Sending");
        } else if(messageChat.status == MessageState.DEL_BY_ADMIN.getValue()){
            this.viewBinding.message.setText("Message has been deleted by admin");
        }else if(messageChat.status == MessageState.DEL_BY_OWNER.getValue()){
            this.viewBinding.message.setText("Message has been revoked ");
        }
    }

    public ItChatRightImgHolder(@NonNull ItChatRightImgBinding viewBinding) {
        super(viewBinding);
        this.viewBinding = viewBinding;
    }
}