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

import com.dxlampro.appchat.Main;
import com.dxlampro.appchat.databinding.FragmentChatscrBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import JavaCode.Clib.ImageUtil;
import JavaCode.Model.GroupChat;
import JavaCode.Model.MessageApdater;
import JavaCode.Model.User;
import JavaCode.Screen.StartChat.ChatViewModel;
import JavaCode.Screen.StartChat.iChat;
import JavaCode.network.Session_ME;
import JavaCode.networklogic.Service;
import gun0912.tedbottompicker.TedBottomPicker;


public class ScreenChat extends Fragment{
    public GroupChat group;
    private int size;
    private MessageApdater messageApdater;
    private FragmentChatscrBinding chatscrBinding;
    private LinearLayoutManager linearLayoutManager;
    private iChat mainViewModel;
    public ScreenChat() {
    }

    private void initListener() {
        mainViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
        mainViewModel.getListMessage().observe(getViewLifecycleOwner(), groupChats -> {
            messageApdater = new MessageApdater(groupChats, getContext());
            chatscrBinding.messChat12.setAdapter(messageApdater);
        });
//        size = mMessageChat.size();
        messageApdater = new MessageApdater(mainViewModel.getListMessage().getValue(), getContext());
        chatscrBinding.messChat12.setAdapter(messageApdater);
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
        chatscrBinding.imgSendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Session_ME.gI().connected) {
                    if (!chatscrBinding.textMess.getText().toString().equals("")) {
                        Service.gI().chatRoom(chatscrBinding.textMess.getText().toString(), 2, mainViewModel.getCurrentGr().getValue().idGroup);
                        Log.e("chat", chatscrBinding.textMess.getText().toString());
//                        mMessageChat.add(new MessageChat(textMess.getText().toString(),User.gI(),null, MessageChat.TYPE_CHAT_STR));
                        chatscrBinding.textMess.setText("");
                        if (messageApdater != null) {
//                            User.gI().zMessageChat.add(new MessageChat(textMess.getText().toString(), MessageApdater.ME_STR)); cc
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Khong co ket noi den Server", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chatscrBinding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenChat.this.addImage();
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                final NavController navController = NavHostFragment.findNavController(ScreenChat.this);
                navController.popBackStack();
                mainViewModel.openChat(null);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }
    private void addImage() {
        showPermission();
    }
//    public void updateListChat() {
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        chatscrBinding.messChat12.post(() -> {
//            if (messageApdater != null && size < mMessageChat.size()) {
//                messageApdater.notifyItemRangeInserted(size, mMessageChat.size());
//                size = mMessageChat.size();
//                chatscrBinding.messChat12.scrollToPosition(mMessageChat.size() - 1);
//            }
//        });
//        lock.unlock();
//    }
    public void showPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                pickImg();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(requireContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
    private void pickImg() {
        TedBottomPicker.with(requireActivity())
                .show(uri -> {
                    try {
                        InputStream iStream = requireActivity().getContentResolver().openInputStream(uri);
                        byte[] inputData = getBytes(iStream);
                        Log.e("vv", String.valueOf(inputData.length));
                        String encodedString = Base64.encodeToString(inputData, Base64.NO_WRAP);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(), uri);
                        ImageUtil img = ImageUtil.buider(bitmap);
                        if (bitmap != null) {
//                            MessageChat messageChat = new MessageChat(encodedString,User.gI(),null,MessageChat.TYPE_CHAT_IMG);
//                            mMessageChat.add(messageChat);
                            Service.gI().chatRoom(encodedString, 1, mainViewModel.getCurrentGr().getValue().idGroup);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
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