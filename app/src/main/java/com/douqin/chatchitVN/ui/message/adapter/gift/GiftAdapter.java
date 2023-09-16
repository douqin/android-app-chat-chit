package com.douqin.chatchitVN.ui.message.adapter.gift;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.data.models.UI.TypeGift;
import com.douqin.chatchitVN.databinding.ItLayoutGifBinding;
import com.douqin.chatchitVN.network.apis.RemoteData.GiftRemoteData;

import java.util.ArrayList;
import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.ViewHolder> {
    private final List<GiftRemoteData> listDataGift;

    public GiftAdapter(List<GiftRemoteData> listDataGift) {
        if (listDataGift != null) {
            this.listDataGift = listDataGift;
        } else this.listDataGift = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new GiftAdapter.ViewHolder(ItLayoutGifBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GiftRemoteData giftRemoteData = listDataGift.get(position);
        holder.bindView(giftRemoteData);
    }

    @Override
    public int getItemCount() {
        return listDataGift.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItLayoutGifBinding itLayoutGifBinding;

        public ViewHolder(ItLayoutGifBinding inflate) {
            super(inflate.getRoot());
            this.itLayoutGifBinding = inflate;
        }

        public void bindView(GiftRemoteData gif) {
            String url = gif.media_formats.get(TypeGift.NANO_GIF.getValue()).url;
            Glide.with(this.itemView.getContext())
                    .asGif()
                    .load(url)
                    .override((int) (MotherCanvas.height * 0.12))
                    .into(this.itLayoutGifBinding.gifContainer);
        }
    }
}
