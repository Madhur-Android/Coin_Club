package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.coinclubapp.Adapters.HotClubAdapter;
import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityHotClubBinding;
import com.example.coinclubapp.result.ClubResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotClubActivity extends AppCompatActivity {
    ActivityHotClubBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotClubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.progressBar.setVisibility(View.VISIBLE);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
        Call<List<ClubResult>> call = apiInterface.getAllClubs();
        call.enqueue(new Callback<List<ClubResult>>() {
            @Override
            public void onResponse(Call<List<ClubResult>> call, Response<List<ClubResult>> response) {
                if (response.isSuccessful()) {
                    binding.recyclerView.setAdapter(new HotClubAdapter(HotClubActivity.this, response.body()));
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(HotClubActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ClubResult>> call, Throwable t) {
                Log.i("THROWABLE",t.getMessage());
                Toast.makeText(HotClubActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.notification:
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    break;
                case R.id.Dashboard:
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    break;
                case R.id.money:
                    startActivity(new Intent(getApplicationContext(), MyBankActivity.class));
                    break;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
            }
            return false;
        });

    }

}