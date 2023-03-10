package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coinclubapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.vpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,ViewProfileActivity.class));
            }
        });

        binding.addBankDetails.setOnClickListener (v -> {
            Intent i =  new Intent (ProfileActivity.this, BankDetailsActivity.class);
            startActivity (i);
        });

    }
}