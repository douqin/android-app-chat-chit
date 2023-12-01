package com.douqin.chatchitVN.ui.relationship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.douqin.chatchitVN.databinding.FragmentFriendContainerBinding;
import com.douqin.chatchitVN.ui.relationship.invites.adapter.InvitesPageAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class FriendScreenContainer extends Fragment {

    FragmentFriendContainerBinding fragmentFriendBinding;

    InvitesPageAdapter friendsPageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentFriendBinding = FragmentFriendContainerBinding.inflate(inflater, container, false);
        this.friendsPageAdapter = new InvitesPageAdapter(getChildFragmentManager(), getLifecycle());
        this.fragmentFriendBinding.viewpager.setAdapter(this.friendsPageAdapter);
        return this.fragmentFriendBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new TabLayoutMediator(this.fragmentFriendBinding.tabs, this.fragmentFriendBinding.viewpager,
                (tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText("Friend");
                            break;
                        case 1:
                            tab.setText("Invite Friend");
                            break;
                    }
                }
        ).attach();
        super.onViewCreated(view, savedInstanceState);
    }
}
