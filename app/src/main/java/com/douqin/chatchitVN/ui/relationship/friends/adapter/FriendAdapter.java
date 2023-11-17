package com.douqin.chatchitVN.ui.relationship.friends.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.douqin.chatchitVN.data.models.UI.Friend;

import kotlin.coroutines.CoroutineContext;

public class FriendAdapter extends PagingDataAdapter<Friend,FriendAdapter.FriendHolder > {

    public FriendAdapter(@NonNull DiffUtil.ItemCallback<Friend> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {

    }

    public static class FriendHolder extends RecyclerView.ViewHolder {

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
