package JavaCode.Screen.Chat;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dxlampro.appchat.databinding.FragmentChatscrBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import JavaCode.Model.MessageApdater;
import JavaCode.Screen.StartChat.ChatViewModel;
import JavaCode.Utils.ImageUtil;
import JavaCode.Utils.NetworkUtils;
import JavaCode.network.Session_ME;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;


public class ScreenChat extends Fragment {
    private MessageApdater messageApdater;
    private FragmentChatscrBinding chatscrBinding;
    private LinearLayoutManager linearLayoutManager;
    private ChatViewModel chatViewModel;

    public ScreenChat() {
    }

    private void initListener() {
        chatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
        chatViewModel.getListMess().observe(getViewLifecycleOwner(), groupChatWithMessage -> {
            messageApdater = new MessageApdater(groupChatWithMessage.listMessage, getContext());
            chatscrBinding.messChat12.setAdapter(messageApdater);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        chatscrBinding = FragmentChatscrBinding.inflate(inflater);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        chatscrBinding.messChat12.setLayoutManager(linearLayoutManager);
        this.initListener();
        this.setOnClick();
        return chatscrBinding.getRoot();
    }

    private void setOnClick() {
        chatscrBinding.imgSendChat.setOnClickListener(v -> {
            if (NetworkUtils.isNetworkAvailable(requireActivity().getApplicationContext())) {
                if (!chatscrBinding.textMess.getText().toString().equals("")) {
//                  Service.gI().chatRoom(chatscrBinding.textMess.getText().toString(), 2, mainViewModel.getCurrentGr().getValue().idgroup);
                    // TODO :
                    Log.e("chat", chatscrBinding.textMess.getText().toString());
                    chatscrBinding.textMess.setText("");
                }
            } else {
                Toast.makeText(requireContext(), "Hiện không có kết nối Internet", Toast.LENGTH_SHORT).show();
            }
        });
        chatscrBinding.addImage.setOnClickListener(v -> ScreenChat.this.addImage());
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                final NavController navController = NavHostFragment.findNavController(ScreenChat.this);
                chatViewModel.openChat(null);
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
                .showMultiImage(uriList ->{

                });
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        Log.e("x", Arrays.toString(byteBuffer.toByteArray()));
        return byteBuffer.toByteArray();
    }

}