package com.douqin.chatchitVN.ui.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.databinding.FragmentStoryBinding;
import com.douqin.chatchitVN.ui.story.adapter.StoryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoryScreen extends Fragment {
    FragmentStoryBinding storyScreen;

    private static StoryScreen instance = null;

    private StoryAdapter storyAdapter;

    private RecyclerView.LayoutManager layoutManager;

    StoryViewModel storyViewModel;
    public StoryScreen() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storyAdapter = new StoryAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        storyViewModel = new ViewModelProvider(this.requireActivity()).get(StoryViewModel.class);
        this.storyScreen = FragmentStoryBinding.inflate(inflater);
        List<Object> o = new ArrayList<>();
        o.add(new Object());
        o.add(new Object());
        o.add(new Object());
        o.add(new Object());
        o.add(new Object());
        storyAdapter.submitList(o);
        layoutManager = new GridLayoutManager(this.requireContext(), 2);
        this.storyScreen.girdStory.setLayoutManager(layoutManager);
        this.storyScreen.girdStory.setAdapter(storyAdapter);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                final NavController navCo = NavHostFragment.findNavController(StoryScreen.this);
                navCo.popBackStack(R.id.groupMessage, false);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return storyScreen.getRoot();
    }
}