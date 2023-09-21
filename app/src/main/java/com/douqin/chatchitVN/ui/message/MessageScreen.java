package com.douqin.chatchitVN.ui.message;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.common.NetworkUtils;
import com.douqin.chatchitVN.data.models.UI.GroupChatWithMemberAndMessage;
import com.douqin.chatchitVN.data.models.UI.MemberWithMessage;
import com.douqin.chatchitVN.data.models.UI.MessageChat;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.douqin.chatchitVN.databinding.FragmentMessageBinding;
import com.douqin.chatchitVN.ui.message.adapter.gift.GiftAdapter;
import com.douqin.chatchitVN.ui.message.adapter.message.MessageAdapter;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import gun0912.tedimagepicker.builder.TedImagePicker;


public class MessageScreen extends Fragment {
    private MessageViewModel messageViewModel;
    private MessageAdapter messageAdapter;
    private FragmentMessageBinding messageScr;
    LiveData<GroupChatWithMemberAndMessage> groupChatWithMemberAndMessageLiveData;

    LinearLayoutManager linearLayoutManager;


    private void initListener() {
        messageViewModel.getNewGif().observe(this.getViewLifecycleOwner(), gif -> {
            GiftAdapter giftAdapter;
            if (gif != null) {
                giftAdapter = new GiftAdapter(gif.results);
            } else {
                giftAdapter = new GiftAdapter(new ArrayList<>());
            }
            messageScr.giftContainer.setAdapter(giftAdapter);
        });
        this.messageAdapter = new MessageAdapter( messageViewModel, this.requireContext());
        this.messageScr.messageContainer.setAdapter(messageAdapter);
        this.messageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                MessageScreen.this.linearLayoutManager.smoothScrollToPosition(MessageScreen.this.messageScr.messageContainer, null, MessageScreen.this.messageAdapter.getItemCount());
            }
        });
        this.groupChatWithMemberAndMessageLiveData.observe(this.getViewLifecycleOwner(), groupChatWithMemberAndMessage -> {
            if (groupChatWithMemberAndMessage != null) {
                if (groupChatWithMemberAndMessage.memberWithMessages != null) {
                    List<MessageChat> mMessageChat = new ArrayList<>();
                    for (MemberWithMessage memberWithMessage : groupChatWithMemberAndMessage.memberWithMessages) {
                        mMessageChat.addAll(memberWithMessage.messageChats);
                    }
                    mMessageChat.sort(Comparator.comparing(messageChat -> messageChat.createdAt));
                    this.messageAdapter.submitList(mMessageChat);
                    this.messageScr.messageContainer.scrollToPosition(this.messageAdapter.getItemCount() - 1);
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        MessageScreenArgs args = MessageScreenArgs.fromBundle(getArguments());
        int idgroup = args.getIdgroup();
        messageViewModel = new ViewModelProvider(this.requireActivity()).get(MessageViewModel.class);
        groupChatWithMemberAndMessageLiveData = messageViewModel.getGrWithMemberAndMessage(idgroup);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        messageScr = FragmentMessageBinding.inflate(inflater);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        messageScr.messageContainer.setLayoutManager(linearLayoutManager);
        this.initBaseView();
        this.initListener();
        this.setOnClick();
        return messageScr.getRoot();
    }

    private void initBaseView() {
        groupChatWithMemberAndMessageLiveData.observe(this.getViewLifecycleOwner(), groupChatWithMemberAndMessage -> {
            if (groupChatWithMemberAndMessage != null) {
                this.messageScr.nameGr.setText(groupChatWithMemberAndMessage.group.name);
            } else this.messageScr.nameGr.setText("");
        });
        LinearLayout layout = this.messageScr.layoutGift;
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = (int) (MotherCanvas.height * 0.12);
        layout.setLayoutParams(params);
    }

    private void setOnClick() {
        messageScr.textMess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    messageScr.sendBtn.setBackground(ContextCompat.getDrawable(MessageScreen.this.requireContext(), R.drawable.ic_sendchat));
                } else {
                    messageScr.sendBtn.setBackground(ContextCompat.getDrawable(MessageScreen.this.requireContext(), R.drawable.ic_like));
                }
                if (charSequence.toString().contains("@gif ")) {
                    String[] str = charSequence.toString().split(" ");
                    if (str.length == 2 && !str[1].isEmpty()) {
                        messageViewModel.getGifFromKeyword(str[1]);
                        messageScr.layoutGift.setVisibility(View.VISIBLE);
                        GiftAdapter giftAdapter = new GiftAdapter(new ArrayList<>());
                        messageScr.giftContainer.setAdapter(giftAdapter);
                    }
                } else if (messageScr.layoutGift.getVisibility() == View.VISIBLE) {
                    GiftAdapter giftAdapter = new GiftAdapter(new ArrayList<>());
                    messageScr.layoutGift.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        messageScr.sendBtn.setOnClickListener(v -> {
            if (NetworkUtils.isNetworkAvailable(requireActivity().getApplicationContext())) {
                if (!messageScr.textMess.getText().toString().equals("")) {
                    messageViewModel.sentMessage(
                            Objects.requireNonNull(
                                    MessageScreen.this.groupChatWithMemberAndMessageLiveData.getValue()).group.idgroup, messageScr.textMess.getText().toString(),
                            MessageScreen.this.messageViewModel.getInformationMemberFromUser(MeManager.gI().getMySelf().idUser).id
                    );
                    messageScr.textMess.setText("");
                }
            } else {
                Toast.makeText(requireContext(), "Hiện không có kết nối Internet", Toast.LENGTH_SHORT).show();
            }
        });
        messageScr.addImage.setOnClickListener(v -> MessageScreen.this.addImage());
        messageScr.btnGif.setOnClickListener(v -> {
            if (messageScr.layoutGift.getVisibility() == View.GONE) {
                messageViewModel.getGifFromKeyword("trending");
                messageScr.layoutGift.setVisibility(View.VISIBLE);
            } else {
                GiftAdapter giftAdapter = new GiftAdapter(new ArrayList<>());
                messageScr.layoutGift.setVisibility(View.GONE);
            }
        });
        KeyboardVisibilityEvent.setEventListener(
                requireActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            MessageScreen.this.messageScr.addImage.setVisibility(View.GONE);
                            MessageScreen.this.messageScr.moreBtn.setVisibility(View.GONE);
                        } else {
                            MessageScreen.this.messageScr.addImage.setVisibility(View.VISIBLE);
                            MessageScreen.this.messageScr.moreBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void addImage() {
        pickImg();
    }

    public void showPermission() {
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                pickImg();
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(requireContext(), "Quyền truy cập bộ nhớ bị chặn \n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//
//        };
//        TedPermission.create()
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage(" Nếu bạn hủy cho phép ứng dụng truy cập bộ nhớ \n\n Hãy bật nó tại [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .check();
    }

    private void pickImg() {
        TedImagePicker.with(MessageScreen.this.requireActivity())
                .imageAndVideo()
                .startMultiImage(uriList -> {
                    for (Uri uri : uriList) {
                        try {
                            String type = MessageScreen.this.getMimeType(uri);
                            if (type != null) {
                                MessageScreen.this.messageViewModel.sentMessage(Objects.requireNonNull(this.groupChatWithMemberAndMessageLiveData.getValue()).group.idgroup, new File(Objects.requireNonNull(uri.getPath())), type,
                                        messageViewModel.getInformationMemberFromUser(MeManager.gI().getMySelf().idUser).id);
                            } else
                                Toast.makeText(MessageScreen.this.requireContext(), "Error load type of file", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(MessageScreen.this.requireContext(), "Error load path of file", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public String getMimeType(Uri uri) {
        String mimeType = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = requireActivity().getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

}