package com.douqin.chatchitVN.ui.message;

import android.Manifest;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.common.NetworkUtils;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.models.MessageChat;
import com.douqin.chatchitVN.databinding.FragmentMessageBinding;
import com.douqin.chatchitVN.ui.message.adapter.MessageApdater;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;


public class MessageScreen extends Fragment {
    private MessageApdater messageApdater;
    private FragmentMessageBinding messageScr;
    LiveData<List<MessageChat>> message;

    public MessageScreen() {
    }

    private void initListener() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        messageScr = FragmentMessageBinding.inflate(inflater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        messageScr.messageContainer.setLayoutManager(linearLayoutManager);
        this.initListener();
        this.setOnClick();
        return messageScr.getRoot();
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
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        messageScr.sendBtn.setOnClickListener(v -> {
            if (NetworkUtils.isNetworkAvailable(requireActivity().getApplicationContext())) {
                if (!messageScr.textMess.getText().toString().equals("")) {
                    // TODO :
                    Log.e("chat", messageScr.textMess.getText().toString());
                    messageScr.textMess.setText("");
                }
            } else {
                Toast.makeText(requireContext(), "Hiện không có kết nối Internet", Toast.LENGTH_SHORT).show();
            }
        });
        messageScr.addImage.setOnClickListener(v -> MessageScreen.this.addImage());
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                final NavController navController = NavHostFragment.findNavController(MessageScreen.this);
                navController.popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    private void addImage() {
        showPermission();
    }

    public void showPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                pickImg();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(requireContext(), "Quyền truy cập bộ nhớ bị chặn \n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(" Nếu bạn hủy cho phép ứng dụng truy cập bộ nhớ \n\n Hãy bật nó tại [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void pickImg() {
        TedBottomPicker.with(requireActivity())
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
//                .setSelectedUriList(selectedUriList)
                .showMultiImage(uriList -> {

                });
    }

}