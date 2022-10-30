package com.ass2.f190260_i190468.messages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.f190260_i190468.R;
import com.ass2.f190260_i190468.chat.chat;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    private List<MessagesList> messagesList;
    private final Context context;

    public MessagesAdapter(List<MessagesList> messagesList,Context context) {
        this.messagesList = messagesList;
        this.context=context;
    }

    @NonNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter_layout, null);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MyViewHolder holder, int position) {
        MessagesList list2 = messagesList.get(position);
        if(!list2.getProfilePic().isEmpty()){
            Picasso.get().load(list2.getProfilePic()).into(holder.profilePic);
        }
        holder.name.setText(list2.getName());
        holder.lastMessage.setText(list2.getLastMessage());

        if(list2.getUnSeenMessage() == 0){
            holder.unseenMessages.setVisibility(View.GONE);
            holder.lastMessage.setTextColor(Color.parseColor("#959595"));
        }
        else{
            holder.unseenMessages.setVisibility(View.VISIBLE);
            holder.unseenMessages.setText(list2.getUnSeenMessage()+"");
            holder.lastMessage.setTextColor(context.getResources().getColor(R.color.purple_700)); //theme_color_80
        }
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, chat.class);
                i.putExtra("mobile",list2.getMobile());
                i.putExtra("name",list2.getName());
                i.putExtra("profile_pic",list2.getProfilePic());
                i.putExtra("chat_key",list2.getchatKey());
                context.startActivity(i);
            }
        });
    }

    public void updateData(List<MessagesList>messagesList){
        this.messagesList = messagesList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profilePic;
        private TextView name,lastMessage,unseenMessages;
        private LinearLayout rootLayout;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        profilePic = itemView.findViewById(R.id.profilePic);
        name = itemView.findViewById(R.id.name);
        lastMessage = itemView.findViewById(R.id.lastMessage);
        unseenMessages = itemView.findViewById(R.id.unseenMessages);
        rootLayout = itemView.findViewById(R.id.rootLayout);
//        itemView.setOnClickListener(this);
    }
//    public  void onClick(View view){
//
//    }
}
}
