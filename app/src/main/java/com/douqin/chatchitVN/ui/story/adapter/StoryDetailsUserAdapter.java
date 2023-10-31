package com.douqin.chatchitVN.ui.story.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.data.models.UI.Story;
import com.douqin.chatchitVN.databinding.ItStoryDetailsBinding;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;

public class StoryDetailsUserAdapter extends RecyclerView.Adapter<StoryDetailsUserAdapter.StoryDetailsUserHolder> {

    private UserWithListStory userWithListStory;

    public StoryDetailsUserAdapter(UserWithListStory userWithListStory) {
        super();
        this.userWithListStory = userWithListStory;
    }

    @NonNull
    @Override
    public StoryDetailsUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new StoryDetailsUserAdapter.StoryDetailsUserHolder(ItStoryDetailsBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull StoryDetailsUserHolder holder, int position) {
        holder.bindView(this.userWithListStory.storyList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.userWithListStory.storyList.size();
    }

    public class StoryDetailsUserHolder extends RecyclerView.ViewHolder {

        private ItStoryDetailsBinding itStoryBinding;

        public StoryDetailsUserHolder(@NonNull ItStoryDetailsBinding itemView) {
            super(itemView.getRoot());
            this.itStoryBinding = itemView;
        }

        void bindView(Story story) {
            ExoPlayer player = new ExoPlayer.Builder(this.itStoryBinding.getRoot().getContext()).build();
            MediaItem mediaItem = MediaItem.fromUri(story.content);
            this.itStoryBinding.story.setPlayer(player);
            this.itStoryBinding.story.setOnClickListener(v -> {
                player.play();
            });
            player.prepare();
        }
    }
}
