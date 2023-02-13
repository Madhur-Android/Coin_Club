package com.example.coinclubapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinclubapp.R;

public class MyClubsAdapter extends RecyclerView.Adapter<MyClubsAdapter.MyClubsViewHolder>
{
    public MyClubsAdapter() {
    }

    @NonNull
    @Override
    public MyClubsAdapter.MyClubsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.user_join_club_layout,parent,false);
        return new MyClubsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClubsAdapter.MyClubsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyClubsViewHolder extends RecyclerView.ViewHolder {

        public MyClubsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
