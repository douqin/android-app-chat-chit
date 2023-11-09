package com.douqin.chatchitVN.ui.story.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.data.models.UI.Story;
import com.douqin.chatchitVN.databinding.ItStoryDetailsBinding;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;

public class StoryDetailsSingleUserAdapter extends RecyclerView.Adapter<StoryDetailsSingleUserAdapter.StoryDetailsUserHolder> {


    private UserWithListStory userWithListStory;

    public StoryDetailsSingleUserAdapter(UserWithListStory userWithListStory) {
        super();
        this.userWithListStory = userWithListStory;
    }

    @NonNull
    @Override
    public StoryDetailsUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new StoryDetailsSingleUserAdapter.StoryDetailsUserHolder(ItStoryDetailsBinding.inflate(layoutInflater, parent, false));
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

        ExoPlayer player;

        private boolean isPlaying = false;

        private ItStoryDetailsBinding itStoryBinding;

        public StoryDetailsUserHolder(@NonNull ItStoryDetailsBinding itemView) {
            super(itemView.getRoot());
            this.itStoryBinding = itemView;
        }

        void bindView(Story story) {
            this.player = new ExoPlayer.Builder(this.itStoryBinding.getRoot().getContext()).build();
            MediaItem mediaItem = MediaItem.fromUri(story.content);
            this.itStoryBinding.story.setPlayer(player);
            player.setMediaItem(mediaItem);
            this.itStoryBinding.story.setOnClickListener(v -> {
                if(!this.isPlaying){
                    player.play();
                } else {
                    player.pause();
                }
            });
            player.prepare();
            player.addListener(new Player.Listener() {
                @Override
                public void onEvents(Player player, Player.Events events) {
                    Player.Listener.super.onEvents(player, events);
                }

                @Override
                public void onTimelineChanged(Timeline timeline, int reason) {
                    Player.Listener.super.onTimelineChanged(timeline, reason);
                }

                @Override
                public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {
                    Player.Listener.super.onMediaItemTransition(mediaItem, reason);
                }

                @Override
                public void onTracksChanged(Tracks tracks) {
                    Player.Listener.super.onTracksChanged(tracks);
                }

                @Override
                public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                    Player.Listener.super.onMediaMetadataChanged(mediaMetadata);
                }

                @Override
                public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                    Player.Listener.super.onPlaylistMetadataChanged(mediaMetadata);
                }

                @Override
                public void onIsLoadingChanged(boolean isLoading) {
                    Player.Listener.super.onIsLoadingChanged(isLoading);
                }


                @Override
                public void onAvailableCommandsChanged(Player.Commands availableCommands) {
                    Player.Listener.super.onAvailableCommandsChanged(availableCommands);
                }

                @Override
                public void onTrackSelectionParametersChanged(TrackSelectionParameters parameters) {
                    Player.Listener.super.onTrackSelectionParametersChanged(parameters);
                }

                @Override
                public void onPlaybackStateChanged(int playbackState) {
                    Player.Listener.super.onPlaybackStateChanged(playbackState);
                    switch (playbackState){
                        case Player.STATE_IDLE:
                            StoryDetailsUserHolder.this.itStoryBinding.getRoot().setEnabled(false);
                            StoryDetailsUserHolder.this.itStoryBinding.loadingContent.show();
                            break;
                        case Player.STATE_BUFFERING:
                            StoryDetailsUserHolder.this.itStoryBinding.getRoot().setEnabled(false);
                            StoryDetailsUserHolder.this.itStoryBinding.loadingContent.show();
                            break;
                        case Player.STATE_READY:
                            StoryDetailsUserHolder.this.itStoryBinding.getRoot().setEnabled(true);
                            StoryDetailsUserHolder.this.itStoryBinding.loadingContent.hide();
                            break;
                        case Player.STATE_ENDED:
                            player.seekTo(0);
                            break;
                        default:
                    }
                }

                @Override
                public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                    Player.Listener.super.onPlayWhenReadyChanged(playWhenReady, reason);
                }

                @Override
                public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {
                    Player.Listener.super.onPlaybackSuppressionReasonChanged(playbackSuppressionReason);
                }

                @Override
                public void onIsPlayingChanged(boolean isPlaying) {
                    StoryDetailsUserHolder.this.isPlaying = isPlaying;
                    Player.Listener.super.onIsPlayingChanged(isPlaying);
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                    Player.Listener.super.onRepeatModeChanged(repeatMode);
                }

                @Override
                public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
                    Player.Listener.super.onShuffleModeEnabledChanged(shuffleModeEnabled);
                }

                @Override
                public void onPlayerError(PlaybackException error) {
                    Player.Listener.super.onPlayerError(error);
                }

                @Override
                public void onPlayerErrorChanged(@Nullable PlaybackException error) {
                    Player.Listener.super.onPlayerErrorChanged(error);
                }

                @Override
                public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
                    Player.Listener.super.onPositionDiscontinuity(oldPosition, newPosition, reason);
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    Player.Listener.super.onPlaybackParametersChanged(playbackParameters);
                }

                @Override
                public void onSeekBackIncrementChanged(long seekBackIncrementMs) {
                    Player.Listener.super.onSeekBackIncrementChanged(seekBackIncrementMs);
                }

                @Override
                public void onSeekForwardIncrementChanged(long seekForwardIncrementMs) {
                    Player.Listener.super.onSeekForwardIncrementChanged(seekForwardIncrementMs);
                }

                @Override
                public void onMaxSeekToPreviousPositionChanged(long maxSeekToPreviousPositionMs) {
                    Player.Listener.super.onMaxSeekToPreviousPositionChanged(maxSeekToPreviousPositionMs);
                }

                @Override
                public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                    Player.Listener.super.onAudioAttributesChanged(audioAttributes);
                }

                @Override
                public void onVolumeChanged(float volume) {
                    Player.Listener.super.onVolumeChanged(volume);
                }

                @Override
                public void onSkipSilenceEnabledChanged(boolean skipSilenceEnabled) {
                    Player.Listener.super.onSkipSilenceEnabledChanged(skipSilenceEnabled);
                }

                @Override
                public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                    Player.Listener.super.onDeviceInfoChanged(deviceInfo);
                }

                @Override
                public void onDeviceVolumeChanged(int volume, boolean muted) {
                    Player.Listener.super.onDeviceVolumeChanged(volume, muted);
                }

                @Override
                public void onVideoSizeChanged(VideoSize videoSize) {
                    Player.Listener.super.onVideoSizeChanged(videoSize);
                }

                @Override
                public void onSurfaceSizeChanged(int width, int height) {
                    Player.Listener.super.onSurfaceSizeChanged(width, height);
                }

                @Override
                public void onRenderedFirstFrame() {
                    Player.Listener.super.onRenderedFirstFrame();
                }

                @Override
                public void onCues(CueGroup cueGroup) {
                    Player.Listener.super.onCues(cueGroup);
                }
            });
        }

    }
}
