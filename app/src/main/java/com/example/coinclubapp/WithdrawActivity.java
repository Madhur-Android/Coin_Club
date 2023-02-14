package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.coinclubapp.databinding.ActivityAddMoneyBinding;
import com.example.coinclubapp.databinding.ActivityWithdrawBinding;

public class WithdrawActivity extends AppCompatActivity {
ActivityWithdrawBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWithdrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.hundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.withdrawEt.setText("100");
            }
        });

        binding.twohundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.withdrawEt.setText("200");
            }
        });

        binding.fivehundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.withdrawEt.setText("500");
            }
        });

        binding.thousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.withdrawEt.setText("1000");
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}