package com.douqin.chatchitVN.ui.story.adapter;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.data.models.UI.Story;
import com.douqin.chatchitVN.databinding.ItStoryBinding;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder>{
    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new StoryHolder(ItStoryBinding.inflate(layoutInflater));
    }
    public void submitList(List<Story> newList) {
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

    public static class StoryHolder extends RecyclerView.ViewHolder{

        private static int height;
        private static int width;
        ItStoryBinding itStory;

        public StoryHolder(@NonNull ItStoryBinding binding) {
            super(binding.getRoot());
            itStory = binding;
            int dpValue = 14;
//            int sizeInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, this.itStory.getRoot().getContext().getResources().getDisplayMetrics());
        }

        public void bindView(Story story){
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams((int) (MotherCanvas.width * 0.47),(int) (MotherCanvas.height * 0.3));
            layoutParams.setMargins(10,10,10,10);
            itStory.containerStory.setLayoutParams(layoutParams);
        }
    }
    private DiffUtil.ItemCallback<Story> a = new DiffUtil.ItemCallback<Story>() {

        @Override
        public boolean areItemsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
            return true;
        }
    };
    private final AsyncListDiffer<Story> listDiffer = new AsyncListDiffer<Story>(this, a);
}
