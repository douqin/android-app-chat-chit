package com.douqin.chatchitVN.ui.relationship.invites;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.douqin.chatchitVN.databinding.FragmentInvitesBinding;

public class InvitesScreen extends Fragment {

    private FragmentInvitesBinding invitesBinding;

    private InvitesScreen(){}
    private static InvitesScreen _instance = new InvitesScreen();
    public static InvitesScreen gI(){
        return _instance;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.invitesBinding = FragmentInvitesBinding.inflate(inflater, container, false);
        return this.invitesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
