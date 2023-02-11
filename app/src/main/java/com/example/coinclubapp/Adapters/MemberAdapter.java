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
import com.example.coinclubapp.result.FormTwoResult;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    Context context;
    List<FormTwoResult> resultList;

    public MemberAdapter(Context context, List<FormTwoResult> resultList) {
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
        FormTwoResult result=resultList.get(position);
        Glide.with(context).load("https://jobmeet.techpanda.art"+result.getProfileimg()).placeholder(R.drawable.avatar).into(holder.dp_img);
        holder.nameTv.setText(result.getFullName());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        CircleImageView dp_img;
        TextView nameTv;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            dp_img=itemView.findViewById(R.id.dp_img);
            nameTv=itemView.findViewById(R.id.nameTv);
        }
    }
}