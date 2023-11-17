package com.douqin.chatchitVN.ui.relationship.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.douqin.chatchitVN.ui.relationship.invites.InvitesScreen;
import com.douqin.chatchitVN.ui.relationship.friends.FriendsScreen;

public class FriendsContainerPageAdapter extends FragmentStateAdapter {

    public FriendsContainerPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FriendsContainerPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return FriendsScreen.gI();
            case 1:
                return InvitesScreen.gI();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
