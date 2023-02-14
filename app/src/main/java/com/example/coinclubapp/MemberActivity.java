package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.coinclubapp.Adapters.SeeAllMembersAdapter;
import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityMemberBinding;
import com.example.coinclubapp.result.Id;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberActivity extends AppCompatActivity {

    ActivityMemberBinding binding;
    RecyclerView member_list_Rv;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        binding = ActivityMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        member_list_Rv = findViewById (R.id.member_list_Rv);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.memberListRv.setLayoutManager(layoutManager);
        apiInterface= RetrofitService.getRetrofit().create(ApiInterface.class);
        Call<List<Id>> call=apiInterface.getAllRegisteredUsers();
        call.enqueue(new Callback<List<Id>>() {
            @Override
            public void onResponse(Call<List<Id>> call, Response<List<Id>> response) {
                if(response.isSuccessful())
                {
                    binding.memberListRv.setAdapter(new SeeAllMembersAdapter(MemberActivity.this,response.body()));
                }
                else
                {
                    Toast.makeText(MemberActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Id>> call, Throwable t) {
                Toast.makeText(MemberActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}