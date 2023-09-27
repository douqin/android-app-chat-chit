package com.douqin.chatchitVN.ui.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.douqin.chatchitVN.databinding.FragmentStoryBinding;

public class StoryView extends Fragment {
    FragmentStoryBinding storyScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return FragmentStoryBinding.inflate(inflater).getRoot();
    }
}