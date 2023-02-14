package com.example.coinclubapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coinclubapp.R;
import com.example.coinclubapp.result.Id;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    Context context;
    List<Id> resultList;

    public MemberAdapter(Context context, List<Id> resultList) {
        this.context = context;
        this.resultList = resultList;
    }


    @NonNull
    @Override
    public MemberAdapter.MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.member_layout,parent,false);
        return new MemberAdapter.MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.MemberViewHolder holder, int position) {
        Id result=resultList.get(position);
        Glide.with(context).load("https://jobmeet.techpanda.art"+result.getProfileimg()).placeholder(R.drawable.avatar).into(holder.dp_img);
        holder.nameTv.setText(result.getFullName());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView dp_img;
        TextView nameTv;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            dp_img=itemView.findViewById(R.id.dp_img);
            nameTv=itemView.findViewById(R.id.nameTv);
        }
    }
}