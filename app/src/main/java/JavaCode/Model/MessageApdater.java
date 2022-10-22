package JavaCode.Model;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dxlampro.appchat.R;

import java.util.ArrayList;
import java.util.List;

public class MessageApdater extends RecyclerView.Adapter<MessageApdater.ViewHolder> {
    private List<MessageChat> mMessageChat ;
    private final Context mContext;
    public final static int ME_TYPE_CHAT_IMG = 1;
    public final static int ME_TYPE_CHAT_STR = 2;
    public final static int ME_TYPE_CHAT_VIDEO = 3;
    public final static int ME_TYPE_CHAT_HYPER_LINK = 4;
    public final static int ME_TYPE_CHAT_GIF = 5;
    public final static int NOT_ME_TYPE_CHAT_IMG = 6;
    public final static int NOT_ME_TYPE_CHAT_STR = 7;
    public final static int NOT_ME_TYPE_CHAT_VIDEO = 8;
    public final static int NOT_ME_TYPE_CHAT_HYPER_LINK = 9;
    public final static int NOT_ME_TYPE_CHAT_GIF = 10;
    public MessageApdater(List<MessageChat> mMessageChat, Context mContext) {
        if(mMessageChat != null)
        this.mMessageChat = mMessageChat;
        else  this.mMessageChat = new ArrayList<>();
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case MessageApdater.NOT_ME_TYPE_CHAT_STR:
                view = LayoutInflater.from(mContext).inflate(R.layout.chat_left, parent, false);
                break;
            case MessageApdater.NOT_ME_TYPE_CHAT_IMG:
                view = LayoutInflater.from(mContext).inflate(R.layout.chat_left_img, parent, false);
                break;
            case MessageApdater.ME_TYPE_CHAT_IMG:
                view = LayoutInflater.from(mContext).inflate(R.layout.chat_right_img, parent, false);
                break;
            case MessageApdater.ME_TYPE_CHAT_STR:
                view = LayoutInflater.from(mContext).inflate(R.layout.chat_right, parent, false);
        }
        if (view != null) {
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        MessageChat messageChat = mMessageChat.get(position);
//        /**
//         if(position != 0 ){
//         Chat chatOld = mChat.get(position -1);
//         //
//         //holder.nameUser.setVisibility(View.GONE);
//         if(chatOld != null){
//
//         }
//         }*/
//        switch (messageChat.typeChat) {
//            case NOT_ME_STR:
//            case ME_STR:
//                holder.message.setText(messageChat.message);
//                break;
//            case NOT_ME_IMG:
//            case ME_IMG:
//                GradientDrawable shape = new GradientDrawable();
//                shape.setCornerRadius(8);
//                holder.imgMess.setBackground(shape);
////                holder.imgMess.setImageBitmap(messageChat.img.getBitmap()); ccc
//                break;
//        }
    }

    @Override
    public int getItemViewType(int position) {
        //cc
        if(mMessageChat.get(position).user.iduser == 1){ // chua xong
            switch (mMessageChat.get(position).typeChat) {
                case MessageApdater.ME_TYPE_CHAT_STR:
                    return MessageApdater.ME_TYPE_CHAT_STR;
                case MessageApdater.ME_TYPE_CHAT_IMG:
                    return MessageApdater.ME_TYPE_CHAT_IMG;
                case MessageApdater.ME_TYPE_CHAT_VIDEO:
                    return MessageApdater.ME_TYPE_CHAT_VIDEO;
                case MessageApdater.ME_TYPE_CHAT_HYPER_LINK:
                    return MessageApdater.ME_TYPE_CHAT_HYPER_LINK;
                case MessageApdater.ME_TYPE_CHAT_GIF:
                    return MessageApdater.ME_TYPE_CHAT_GIF;
            }
        }
        else{
            switch (mMessageChat.get(position).typeChat) {
                case MessageApdater.NOT_ME_TYPE_CHAT_STR:
                    return MessageApdater.NOT_ME_TYPE_CHAT_STR;
                case MessageApdater.NOT_ME_TYPE_CHAT_IMG:
                    return MessageApdater.NOT_ME_TYPE_CHAT_IMG;
                case MessageApdater.NOT_ME_TYPE_CHAT_VIDEO:
                    return MessageApdater.NOT_ME_TYPE_CHAT_VIDEO;
                case MessageApdater.NOT_ME_TYPE_CHAT_HYPER_LINK:
                    return MessageApdater.NOT_ME_TYPE_CHAT_HYPER_LINK;
                case MessageApdater.NOT_ME_TYPE_CHAT_GIF:
                    return MessageApdater.NOT_ME_TYPE_CHAT_GIF;
            }
        }
        return  0;
    }

    @Override
    public int getItemCount() {
        return mMessageChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvt;
        public TextView message;
        public TextView nameUser;
        public ImageView imgMess;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                imgAvt = itemView.findViewById(R.id.avtuser);
                message = itemView.findViewById(R.id.message);
                imgMess = itemView.findViewById(R.id.imgMess);
                nameUser = itemView.findViewById(R.id.nameUserChat);
            } catch (Exception ignored) {

            }
        }
    }
}
