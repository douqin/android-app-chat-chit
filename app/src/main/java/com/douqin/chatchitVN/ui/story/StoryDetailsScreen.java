package com.douqin.chatchitVN.ui.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.databinding.FragmentStoryDetailsBinding;
import com.douqin.chatchitVN.ui.base.OnSnapPositionChangeListener;
import com.douqin.chatchitVN.ui.base.SnapOnScrollListener;
import com.douqin.chatchitVN.ui.story.adapter.StoryDetailsMultiUserAdapter;

public class StoryDetailsScreen extends Fragment {
    FragmentStoryDetailsBinding storyDetailsBinding;

    StoryDetailsMultiUserAdapter storyDetailsAdapter;

    StoryViewModel storyViewModel;

    PagerSnapHelper pagerSnapHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.storyDetailsBinding = FragmentStoryDetailsBinding.inflate(inflater);
        this.storyViewModel = new ViewModelProvider(this.requireActivity()).get(StoryViewModel.class);
        this.pagerSnapHelper = new PagerSnapHelper();
        RecyclerView.OnScrollListener snapOnScrollListener = new SnapOnScrollListener(pagerSnapHelper, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, new OnSnapPositionChangeListener() {
            @Override
            public void onSnapPositionChange(int position) {
//                storyDetailsAdapter.setCurrentSelect(position);
            }
        });
        storyDetailsBinding.listStory.addOnScrollListener(snapOnScrollListener);
        storyDetailsBinding = FragmentStoryDetailsBinding.inflate(inflater);
        storyDetailsAdapter = new StoryDetailsMultiUserAdapter(this.storyViewModel);
        storyDetailsBinding.listStory.setAdapter(storyDetailsAdapter);
        storyViewModel.getStory().observe(this.getViewLifecycleOwner(), userWithListStories -> {
            storyDetailsAdapter.submitList(userWithListStories);
        });
        pagerSnapHelper.attachToRecyclerView(storyDetailsBinding.listStory);
        this.storyDetailsBinding.listStory.scrollToPosition(this.storyViewModel.getIndexCurrPick());
        return this.storyDetailsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
