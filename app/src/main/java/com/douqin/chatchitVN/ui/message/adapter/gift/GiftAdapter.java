package com.douqin.chatchitVN.ui.message.adapter.gift;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.data.models.UI.TypeGiftConstants;
import com.douqin.chatchitVN.databinding.ItLayoutGifBinding;
import com.douqin.chatchitVN.network.apis.RemoteData.GiftRemoteData;
import com.douqin.chatchitVN.ui.message.adapter.OnClickItemGif;

import java.util.List;
import java.util.Objects;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.ViewHolder> {
    private final OnClickItemGif onClickItemGif;

    public GiftAdapter(OnClickItemGif onClickItemGif) {
        this.onClickItemGif = onClickItemGif;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new GiftAdapter.ViewHolder(ItLayoutGifBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GiftRemoteData giftRemoteData = this.listDiffer.getCurrentList().get(position);
        holder.bindView(giftRemoteData);
    }

    @Override
    public int getItemCount() {
        return this.listDiffer.getCurrentList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItLayoutGifBinding itLayoutGifBinding;

        public ViewHolder(ItLayoutGifBinding inflate) {
            super(inflate.getRoot());
            this.itLayoutGifBinding = inflate;
        }

        public void bindView(GiftRemoteData gif) {
            String url = Objects.requireNonNull(gif.media_formats.get(TypeGiftConstants.NANO_GIF)).url;
            Glide.with(this.itemView.getContext())
                    .asGif()
                    .load(url)
                    .override((int) (MotherCanvas.width / 0.12), (int) (MotherCanvas.height * 0.12))
                    .into(this.itLayoutGifBinding.gifContainer);
        }
    }

    public void submitList(List<GiftRemoteData> newList) {
        this.listDiffer.submitList(newList);
    }

    private DiffUtil.ItemCallback<GiftRemoteData> a = new DiffUtil.ItemCallback<GiftRemoteData>() {

        @Override
        public boolean areItemsTheSame(@NonNull GiftRemoteData oldItem, @NonNull GiftRemoteData newItem) {
            return Objects.equals(oldItem.id, newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull GiftRemoteData oldItem, @NonNull GiftRemoteData newItem) {
            return true;
        }
    };
    private final AsyncListDiffer<GiftRemoteData> listDiffer = new AsyncListDiffer<GiftRemoteData>(this, a);
}
