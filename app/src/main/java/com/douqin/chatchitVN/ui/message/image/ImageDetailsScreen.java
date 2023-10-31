package com.douqin.chatchitVN.ui.message.image;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.databinding.FragmentDetailsImageMessageBinding;
import com.douqin.chatchitVN.ui.message.MessageViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ImageDetailsScreen extends Fragment {

    FragmentDetailsImageMessageBinding fragmentDetailsImageMessageBinding;
    private MessageViewModel messageViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageViewModel = new ViewModelProvider(this.requireActivity()).get(MessageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentDetailsImageMessageBinding = FragmentDetailsImageMessageBinding.inflate(inflater);
        MessageChat groupChatWithMemberAndMessage = messageViewModel.getMessageOpenDetails().getValue();
        Observable.fromCallable(() -> {
                    assert groupChatWithMemberAndMessage != null;
                    return Glide.with(this.requireActivity())
                            .load(groupChatWithMemberAndMessage.content)
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
                        ImageDetailsScreen.this.fragmentDetailsImageMessageBinding.image.setImageDrawable(o);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Toast.makeText(ImageDetailsScreen.this.requireContext(), "Erorr load image", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return this.fragmentDetailsImageMessageBinding.getRoot();
    }

    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            ImageDetailsScreen.this.messageViewModel.openMessageInDetailsImageScreen(null);
            NavHostFragment.findNavController(ImageDetailsScreen.this).popBackStack();
        }
    };

}
