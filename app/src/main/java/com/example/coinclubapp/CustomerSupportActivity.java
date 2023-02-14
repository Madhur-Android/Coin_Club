package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.coinclubapp.databinding.ActivityCustomerSupportBinding;

public class CustomerSupportActivity extends AppCompatActivity {
    ActivityCustomerSupportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}