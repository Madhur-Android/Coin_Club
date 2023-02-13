package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.coinclubapp.Adapters.BidsAdapter;
import com.example.coinclubapp.Fragments.BidNowFragment;
import com.example.coinclubapp.databinding.ActivityBidRoomBinding;

public class BidRoomActivity extends AppCompatActivity {
    ActivityBidRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBidRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bidBtn.setOnClickListener(v -> {

            BidNowFragment bidNowFragment =new BidNowFragment();
            bidNowFragment.show(getSupportFragmentManager(),bidNowFragment.getTag());

        });

        binding.bidRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.bidRecyclerView.setAdapter(new BidsAdapter());
    }
}