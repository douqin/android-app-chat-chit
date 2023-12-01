package com.douqin.chatchitVN.ui.story.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.databinding.ItUserStoryDetailsBinding;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;
import com.douqin.chatchitVN.ui.base.OnSnapPositionChangeListener;
import com.douqin.chatchitVN.ui.base.SnapOnScrollListener;
import com.douqin.chatchitVN.ui.story.StoryViewModel;

import java.util.List;
import java.util.Objects;

public class StoryDetailsMultiUserAdapter extends RecyclerView.Adapter<StoryDetailsMultiUserAdapter.StoryDetailsHolder> {

    private int currentSelect = 0;

    StoryViewModel storyViewModel;

    public StoryDetailsMultiUserAdapter(StoryViewModel storyViewModel) {
        this.storyViewModel = storyViewModel;
    }

    public void setCurrentSelect(int currentSelect) {
        this.currentSelect = currentSelect;
    }

    @NonNull
    @Override
    public StoryDetailsMultiUserAdapter.StoryDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new StoryDetailsMultiUserAdapter.StoryDetailsHolder(ItUserStoryDetailsBinding.inflate(layoutInflater, parent, false));
    }

    public void submitList(List<UserWithListStory> newList) {
        this.listDiffer.submitList(newList);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryDetailsMultiUserAdapter.StoryDetailsHolder holder, int position) {
//        if (this.currentSelect == position)
        holder.bindView(listDiffer.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return listDiffer.getCurrentList().size();
    }

    public static class StoryDetailsHolder extends RecyclerView.ViewHolder {

        private int currentIndex = 0;

        ItUserStoryDetailsBinding itStory;

        PagerSnapHelper pagerSnapHelper;

        public StoryDetailsHolder(@NonNull ItUserStoryDetailsBinding binding) {
            super(binding.getRoot());
            itStory = binding;
        }

        public void bindView(UserWithListStory story) {
            this.pagerSnapHelper = new PagerSnapHelper();
            StoryDetailsSingleUserAdapter storyDetailsUserAdapter = new StoryDetailsSingleUserAdapter(story);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.itStory.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false);
            this.pagerSnapHelper.attachToRecyclerView(this.itStory.itListDetailsStory);
            RecyclerView.OnScrollListener snapOnScrollListener = new SnapOnScrollListener(pagerSnapHelper, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, new OnSnapPositionChangeListener() {
                @Override
                public void onSnapPositionChange(int position) {
                    StoryDetailsSingleUserAdapter.StoryDetailsUserHolder storyDetailsUserHolder = (StoryDetailsSingleUserAdapter.StoryDetailsUserHolder) StoryDetailsHolder.this.itStory.itListDetailsStory.findViewHolderForAdapterPosition(currentIndex);
                    assert storyDetailsUserHolder != null;
                    storyDetailsUserHolder.player.pause();
                    storyDetailsUserHolder.player.seekTo(0);
                    currentIndex = position;
                }
            });
            this.itStory.itListDetailsStory.addOnScrollListener(snapOnScrollListener);
            this.itStory.itListDetailsStory.setLayoutManager(layoutManager);
            this.itStory.itListDetailsStory.setAdapter(storyDetailsUserAdapter);
            this.itStory.circleIndicator.attachToRecyclerView(this.itStory.itListDetailsStory, pagerSnapHelper);
            storyDetailsUserAdapter.registerAdapterDataObserver(this.itStory.circleIndicator.getAdapterDataObserver());
        }
    }

    private DiffUtil.ItemCallback<UserWithListStory> a = new DiffUtil.ItemCallback<UserWithListStory>() {

        @Override
        public boolean areItemsTheSame(@NonNull UserWithListStory oldItem, @NonNull UserWithListStory newItem) {
            return oldItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserWithListStory oldItem, @NonNull UserWithListStory newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
    private final AsyncListDiffer<UserWithListStory> listDiffer = new AsyncListDiffer<UserWithListStory>(this, a);
}
