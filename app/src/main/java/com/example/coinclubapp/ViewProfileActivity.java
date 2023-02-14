package com.example.coinclubapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.coinclubapp.databinding.ActivityViewProfileBinding;

public class ViewProfileActivity extends AppCompatActivity {
    ActivityViewProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.editBtn.setOnClickListener(v -> {
            Intent editImg = new Intent(Intent.ACTION_PICK);
            editImg.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(editImg, 123);
        });

        binding.backBtn.setOnClickListener(v ->
                finish());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 123) {
            binding.dpImg.setImageURI(data.getData());
        }
    }
}