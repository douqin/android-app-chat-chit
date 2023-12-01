package com.douqin.chatchitVN.ui.relationship.friends;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.douqin.chatchitVN.databinding.FragmentFriendsBinding;
import com.douqin.chatchitVN.ui.relationship.friends.adapter.FriendAdapter;

public class FriendsScreen extends Fragment {

    private FragmentFriendsBinding fragmentFriendsBinding;

    private FriendsScreen(){}
    private static FriendsScreen _instance = new FriendsScreen();
    public static FriendsScreen gI(){
        return _instance;
    };

    private FriendViewModel friendViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentFriendsBinding = FragmentFriendsBinding.inflate(inflater, container, false);
        this.friendViewModel = new ViewModelProvider(this)
                .get(FriendViewModel.class);
        FriendAdapter friendAdapter = new FriendAdapter(null);
        this.fragmentFriendsBinding.listFriend.setAdapter(friendAdapter);
        return this.fragmentFriendsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
