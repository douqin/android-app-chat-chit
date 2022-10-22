package JavaCode.Model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dxlampro.appchat.R;
import com.dxlampro.appchat.databinding.ItUserChatBinding;

import java.util.ArrayList;
import java.util.List;

import JavaCode.Screen.StartChat.iOnClickItemGroupChat;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private List<GroupChat> mGroupChat;
    private iOnClickItemGroupChat onClickItemGroupChat;

    public GroupAdapter(List<GroupChat> mGroupChat, iOnClickItemGroupChat onClickItemGroupChat) {
        if(mGroupChat != null){
            this.mGroupChat = mGroupChat;
        }
       else  this.mGroupChat = new ArrayList<>();
       this.onClickItemGroupChat = onClickItemGroupChat;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new GroupAdapter.ViewHolder(ItUserChatBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        GroupChat groupChat = mGroupChat.get(position);
        holder.bindView(groupChat,this.onClickItemGroupChat);
    }

    @Override
    public int getItemCount() {
        return mGroupChat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItUserChatBinding inflate;
        public ViewHolder(ItUserChatBinding inflate) {
            super(inflate.getRoot());
            this.inflate = inflate;
        }
        @SuppressLint("ResourceAsColor")
        public void bindView(GroupChat groupChat,iOnClickItemGroupChat onClickItemGroupChat){
            this.inflate.nameGroup.setText(groupChat.name);
            this.inflate.messageUser.setText("Loading...");
            this.inflate.messageUser.setTextColor(R.color.red);
            this.inflate.layoutDetailsChat.setOnClickListener(v -> {
                onClickItemGroupChat.onClickItemGroupChat(groupChat);
            });
        }
    }
}
