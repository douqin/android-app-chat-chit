package com.douqin.chatchitVN.ui.story.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.databinding.ItUserStoryDetailsBinding;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;
import com.douqin.chatchitVN.ui.story.StoryViewModel;

import java.util.List;
import java.util.Objects;

public class StoryDetailsAdapter extends RecyclerView.Adapter<StoryDetailsAdapter.StoryDetailsHolder> {

    private int currentSelect = -1;

    StoryViewModel storyViewModel;

    public StoryDetailsAdapter(StoryViewModel storyViewModel) {
        this.storyViewModel = storyViewModel;
    }

    public void setCurrentSelect(int currentSelect) {
        this.currentSelect = currentSelect;
    }

    @NonNull
    @Override
    public StoryDetailsAdapter.StoryDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new StoryDetailsAdapter.StoryDetailsHolder(ItUserStoryDetailsBinding.inflate(layoutInflater));
    }

    public void submitList(List<UserWithListStory> newList) {
        this.listDiffer.submitList(newList);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryDetailsAdapter.StoryDetailsHolder holder, int position) {
        if (this.currentSelect == position)
            holder.bindView(listDiffer.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return listDiffer.getCurrentList().size();
    }

    public static class StoryDetailsHolder extends RecyclerView.ViewHolder {
        ItUserStoryDetailsBinding itStory;

        public StoryDetailsHolder(@NonNull ItUserStoryDetailsBinding binding) {
            super(binding.getRoot());
            itStory = binding;
        }

        public void bindView(UserWithListStory story) {
            StoryDetailsUserAdapter storyDetailsUserAdapter = new StoryDetailsUserAdapter(story);
            this.itStory.itListDetailsStory.setAdapter(storyDetailsUserAdapter);
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
