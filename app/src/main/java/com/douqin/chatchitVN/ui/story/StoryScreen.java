package com.douqin.chatchitVN.ui.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.databinding.FragmentStoryBinding;
import com.douqin.chatchitVN.ui.story.adapter.StoryAdapter;

public class StoryScreen extends Fragment {
    FragmentStoryBinding storyScreen;

    private static StoryScreen instance = null;

    private StoryAdapter storyAdapter;

    StoryViewModel storyViewModel;

    public StoryScreen() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storyViewModel = new ViewModelProvider(this.requireActivity()).get(StoryViewModel.class);
        storyViewModel.initFromServer();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        storyAdapter = new StoryAdapter(story -> {
            storyViewModel.selectStory(story);
            final NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.storyDetailsView);
        });
        this.storyScreen = FragmentStoryBinding.inflate(inflater);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.requireContext(), 2);
        this.storyScreen.girdStory.setLayoutManager(layoutManager);
        this.storyScreen.girdStory.setAdapter(storyAdapter);
        storyViewModel.getStory().observe(this.getViewLifecycleOwner(), userWithListStories -> {
            storyAdapter.submitList(userWithListStories);
        });
//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                final NavController navCo = NavHostFragment.findNavController(StoryScreen.this);
//                navCo.popBackStack(R.id.groupMessage, false);
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return storyScreen.getRoot();
    }
}