package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coinclubapp.Adapters.TransactionAdapter;
import com.example.coinclubapp.databinding.ActivityMyBankBinding;
import com.example.coinclubapp.databinding.ActivityProfileBinding;

public class MyBankActivity extends AppCompatActivity {

    ActivityMyBankBinding binding;
    RecyclerView.LayoutManager LayoutManager;
    TransactionAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMyBankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.btnSetting.setOnClickListener(v -> {
//            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
//        });

        LayoutManager = new LinearLayoutManager (MyBankActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.transactionRecyclerView.setLayoutManager (LayoutManager);
        adapter = new TransactionAdapter (MyBankActivity.this, null );
        binding.transactionRecyclerView.setAdapter (adapter);
        adapter.notifyDataSetChanged ();
        adapter.getItemCount ();
        binding.transactionRecyclerView.setHasFixedSize (true);
        binding.backBtn.setOnClickListener (v -> {
            finish ();
        });

        binding.addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyBankActivity.this,AddMoneyActivity.class));
            }
        });

        binding.withdrawalMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyBankActivity.this,WithdrawActivity.class));
            }
        });

    }
}