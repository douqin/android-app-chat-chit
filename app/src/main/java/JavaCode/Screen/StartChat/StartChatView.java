package JavaCode.Screen.StartChat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dxlampro.appchat.R;
import com.dxlampro.appchat.databinding.FragmentStartChatBinding;

import JavaCode.Model.GroupAdapter;
import JavaCode.Model.GroupChat;

public class StartChatView extends Fragment {
    private static String TAG = "StartChatView";
    private GroupAdapter chatApdater;
    private final int size = 0;
    private FragmentStartChatBinding startChatBinding;
    private iChat mainViewModel;

    @Override
    public void onDestroyView() {
        Log.e(TAG,"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        startChatBinding = FragmentStartChatBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(false);
        startChatBinding.listChat.setLayoutManager(linearLayoutManager);
        this.initListener();
        this.initOnClickView(startChatBinding);
        mainViewModel.loadListChatUser();
        return startChatBinding.getRoot();
    }

    private void initListener() {
        mainViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
        mainViewModel.getListGroupChat().observe(getViewLifecycleOwner(), groupChats -> {
            chatApdater = new GroupAdapter(groupChats, new iOnClickItemGroupChat() {
                @Override
                public void onClickItemGroupChat(GroupChat groupChat) {
                    mainViewModel.openChat(groupChat);
                }
            });
            startChatBinding.listChat.setAdapter(chatApdater);
            if(mainViewModel.getListGroupChat().getValue().size() != 0){
                startChatBinding.loadingListChat.setVisibility(View.GONE);
            }
        });
        mainViewModel.getCurrentGr().observe(getViewLifecycleOwner(),(groupChat)->{
            if(groupChat != null) {
                final NavController navController = NavHostFragment.findNavController(this);
                navController.navigate(R.id.action_screenStartChat_to_screenChat);
            }
        });
        chatApdater = new GroupAdapter(mainViewModel.getListGroupChat().getValue(), groupChat ->{
            mainViewModel.openChat(groupChat);
        });
        startChatBinding.listChat.setAdapter(chatApdater);
    }

    @SuppressLint({"ShowToast", "RestrictedApi"})
    private void initOnClickView(FragmentStartChatBinding startChatBinding) {
        MenuBuilder menuBuilder = new MenuBuilder(this.getContext());
        MenuInflater menuInflater = new MenuInflater(this.getContext());
        menuInflater.inflate(R.menu.menu_add_more_startchat, menuBuilder);
        startChatBinding.addMore.setOnClickListener((View) -> {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.getContext(), menuBuilder, View);
            menuPopupHelper.setForceShowIcon(true);
            menuBuilder.setCallback(new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.creategroupchat):
                            Toast.makeText(StartChatView.this.getContext().getApplicationContext(), "Chức năng đang phát triển", Toast.LENGTH_SHORT);
                            return true;
                        case (R.id.historylogin):
                            Toast.makeText(StartChatView.this.getContext().getApplicationContext(), "Chức năng đang phát triển...", Toast.LENGTH_SHORT);
                            return true;
                        case (R.id.callgroup):
                            Toast.makeText(StartChatView.this.getContext().getApplicationContext(), "Chức năng đang phát triển 1", Toast.LENGTH_SHORT);
                            return true;
                    }
                    return false;
                }

                @Override
                public void onMenuModeChange(@NonNull MenuBuilder menu) {

                }
            });
            menuPopupHelper.tryShow();
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                StartChatView.this.requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }
}

