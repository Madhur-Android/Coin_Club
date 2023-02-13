package com.example.coinclubapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coinclubapp.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.uKycTv.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), KycDetailsActivity.class));
        });
        binding.tncTv.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), TermsAndConditionActivity.class));
        });
        binding.ppTv.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Privacy_PolicyActivity.class));
        });
    }
}