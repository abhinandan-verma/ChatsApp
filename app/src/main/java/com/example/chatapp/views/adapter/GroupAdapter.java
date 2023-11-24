package com.example.chatapp.views.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatapp.R;
import com.example.chatapp.databinding.ItemCardBinding;
import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.views.ChatActivity;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private ArrayList<ChatGroup> groupArrayList;

    public GroupAdapter(ArrayList<ChatGroup> groupArrayList) {
        this.groupArrayList = groupArrayList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemCardBinding itemCardBinding = DataBindingUtil
               .inflate(LayoutInflater.from(parent.getContext()),
                       R.layout.item_card,
                       parent,false);
       return new GroupViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        //Binds data to an existing ViewHolder
        //populates the Views in the ViewHolder with Data from the Dataset
        ChatGroup currentUser = groupArrayList.get(position);
        holder.itemCardBinding.setChatGroup(currentUser);
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }

    public  class  GroupViewHolder extends RecyclerView.ViewHolder{

        private ItemCardBinding itemCardBinding;

        public GroupViewHolder(ItemCardBinding itemCardBinding) {
            super(itemCardBinding.getRoot());
            this.itemCardBinding = itemCardBinding;

            itemCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    ChatGroup clickedChatGroup = groupArrayList.get(position);

                    Intent i = new Intent(v.getContext(), ChatActivity.class);
                    i.putExtra("GROUP_NAME",clickedChatGroup.getUserName());
                    v.getContext().startActivity(i);

                }
            });
        }
    }
}
