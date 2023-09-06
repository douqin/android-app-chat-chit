package com.douqin.chatchitVN.ui.group;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.data.models.GroupChat;
import com.douqin.chatchitVN.databinding.FragmentStartChatBinding;

import java.util.ArrayList;
import java.util.List;

public class GroupScreen extends Fragment {
    private static final String TAG = "StartChatView";
    private GroupAdapter groupAdapter;
    private FragmentStartChatBinding startChatBinding;

    private GroupViewModel groupViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        groupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        startChatBinding = FragmentStartChatBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(false);
        startChatBinding.listChat.setLayoutManager(linearLayoutManager);
        this.initListener();
        this.initOnClickView(startChatBinding);
        return startChatBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        syncWithServer();
        super.onCreate(savedInstanceState);
    }

    private void syncWithServer() {
        GroupScreenArgs args = GroupScreenArgs.fromBundle(getArguments());
        boolean isLoginNew = args.getIsStartFromScrLogin();
    }

    private void initListener() {
        groupAdapter = new GroupAdapter(groupViewModel.getListGroupChat().getValue(), groupChat -> {
            GroupScreen.this.openMessageOfGroup(groupChat.idgroup);
        });
        groupViewModel.getListGroupChat().observe(this.getViewLifecycleOwner(), groupChats -> {
            groupAdapter = new GroupAdapter(groupChats, groupChat -> {
                GroupScreen.this.openMessageOfGroup(groupChat.idgroup);
            });
        });
        startChatBinding.listChat.setAdapter(groupAdapter);

    }

    private void openMessageOfGroup(int idgroup){
        GroupScreenDirections.ActionScreenStartChatToScreenChat chatToScreenChat = GroupScreenDirections.actionScreenStartChatToScreenChat(idgroup);
        final NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(chatToScreenChat);
    }

    @SuppressLint({"ShowToast", "RestrictedApi"})
    private void initOnClickView(FragmentStartChatBinding startChatBinding) {
        MenuBuilder menuBuilder = new MenuBuilder(this.requireContext());
        MenuInflater menuInflater = new MenuInflater(this.getContext());
        menuInflater.inflate(R.menu.menu_add_more_startchat, menuBuilder);
        startChatBinding.addMore.setOnClickListener((View) -> {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.requireContext(), menuBuilder, View);
            menuPopupHelper.setForceShowIcon(true);
            menuBuilder.setCallback(new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.creategroupchat):
                            Toast.makeText(GroupScreen.this.getContext(), "Chức năng đang phát triển", Toast.LENGTH_SHORT);
                            return true;
                        case (R.id.historylogin):
                            Toast.makeText(GroupScreen.this.getContext(), "Chức năng đang phát triển...", Toast.LENGTH_SHORT);
                            return true;
                        case (R.id.callgroup):
                            Toast.makeText(GroupScreen.this.getContext(), "Chức năng đang phát triển 1", Toast.LENGTH_SHORT);
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
                GroupScreen.this.requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }
}

