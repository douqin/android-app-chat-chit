package com.douqin.chatchitVN.ui.story.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.common.OnClickStoryThumbnail;
import com.douqin.chatchitVN.databinding.ItStoryBinding;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;

import java.util.List;
import java.util.Objects;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {

    private OnClickStoryThumbnail onClickStoryThumbnail;

    public StoryAdapter(OnClickStoryThumbnail onClickStoryThumbnail) {
        this.onClickStoryThumbnail = onClickStoryThumbnail;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new StoryHolder(ItStoryBinding.inflate(layoutInflater));
    }

    public void submitList(List<UserWithListStory> newList) {
        this.listDiffer.submitList(newList);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, int position) {
        holder.bindView(listDiffer.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return listDiffer.getCurrentList().size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {

        ItStoryBinding itStory;

        public StoryHolder(@NonNull ItStoryBinding binding) {
            super(binding.getRoot());
            itStory = binding;
        }

        public void bindView(UserWithListStory story) {
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams((int) (MotherCanvas.width * 0.47), (int) (MotherCanvas.height * 0.3));
            layoutParams.setMargins(10, 10, 10, 10);
            itStory.containerStory.setLayoutParams(layoutParams);
            itStory.containerStory.setOnClickListener(v -> {
                StoryAdapter.this.onClickStoryThumbnail.onClickStoryThumbnail(story);
            });
//            Glide.with(this.itemView.getContext())
//                    .asBitmap()
//                    .load(story.user.avatar)
//                    .into(itStory.avatar);
            Glide.with(this.itemView.getContext())
                    .asBitmap()
                    .load(story.storyList.get(0).content)
                    .transform(new CenterCrop())
                    .into(itStory.thumbnailStory);
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
