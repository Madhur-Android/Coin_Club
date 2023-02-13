package com.example.coinclubapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinclubapp.R;

public class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.BidsViewHolder> {
    public BidsAdapter() {
    }

    @NonNull
    @Override
    public BidsAdapter.BidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.bid_member_layout,parent,false);
        return new BidsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidsAdapter.BidsViewHolder holder, int position) {
        holder.serialNoTv.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class BidsViewHolder extends RecyclerView.ViewHolder {
        TextView serialNoTv;
        public BidsViewHolder(@NonNull View itemView) {
            super(itemView);
           serialNoTv=itemView.findViewById(R.id.serialNoTv);
        }
    }
}
