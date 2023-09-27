package com.douqin.chatchitVN.ui.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.douqin.chatchitVN.databinding.FragmentStoryDetailsBinding;
import com.douqin.chatchitVN.ui.story.adapter.StoryDetailsAdapter;

public class StoryDetailsView extends Fragment {
    FragmentStoryDetailsBinding storyDetailsBinding;

    StoryDetailsAdapter storyDetailsAdapter;

    StoryViewModel storyViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storyViewModel = new ViewModelProvider(this.requireActivity()).get(StoryViewModel.class);
        storyDetailsBinding = FragmentStoryDetailsBinding.inflate(inflater);
        storyDetailsAdapter = new StoryDetailsAdapter();
        storyDetailsBinding.listStory.setAdapter(storyDetailsAdapter);
        return storyDetailsBinding.getRoot();
    }
}
