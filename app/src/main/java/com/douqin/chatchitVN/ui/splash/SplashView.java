package com.douqin.chatchitVN.ui.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.douqin.chatchitVN.databinding.FragmentSplashViewBinding;

public class SplashView extends Fragment {
    private FragmentSplashViewBinding splashViewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        splashViewBinding = FragmentSplashViewBinding.inflate(getLayoutInflater());
        NavController navController = NavHostFragment.findNavController(this);
        try {
            MeManager.gI().initializeDAO(requireActivity().getApplication());
            SplashViewDirections.ActionSplashViewToScreenStartChat action = SplashViewDirections.actionSplashViewToScreenStartChat();
            action.setIsStartFromScrLogin(false);
            navController.navigate(action);
        } catch (Exception e) {
            navController.navigate(R.id.loginView);
        }
        return splashViewBinding.getRoot();
    }
}
