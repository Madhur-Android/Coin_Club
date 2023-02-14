package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Response.signupResponse;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityClubJoiningFormTwoBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubJoiningFormTwoActivity extends AppCompatActivity {
    ActivityClubJoiningFormTwoBinding binding;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClubJoiningFormTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress = new ProgressDialog(ClubJoiningFormTwoActivity.this);
                progress.setMessage("Please Wait....");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);


                if (binding.amountEt.getText().toString().isEmpty()) {
                    binding.amountEt.setError("enter the amount");
                    binding.amountEt.requestFocus();
                } else if (!binding.rbSaving.isChecked() && !binding.rbBorrowing.isChecked() && !binding.rbInvesting.isChecked()) {
                    Toast.makeText(ClubJoiningFormTwoActivity.this, "select motivation", Toast.LENGTH_SHORT).show();
                } else if (binding.incomeEt.getText().toString().isEmpty()) {
                    binding.incomeEt.setError("enter your income");
                    binding.incomeEt.requestFocus();
                } else {
                    progress.show();
                    ApiInterface apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
                    String motive = "";
                    if (binding.rbBorrowing.isChecked()) {
                        motive = "borrowing";
                    } else if (binding.rbInvesting.isChecked()) {
                        motive = "investing";
                    } else {
                        motive = "saving";
                    }
                    String mobile = getIntent().getStringExtra("mobile");
                    String password = getIntent().getStringExtra("password");
                    String name = getIntent().getStringExtra("full_name");
                    String gender = getIntent().getStringExtra("gender");
                    String city = getIntent().getStringExtra("city");
                    String occupation = getIntent().getStringExtra("occupation");
                    String email = getIntent().getStringExtra("Email");
                    String monthlycontribution = binding.amountEt.getText().toString();
                    String income = binding.incomeEt.getText().toString();

                    Call<signupResponse> call = apiInterface.registerNewUser(name, mobile, city, password, gender, occupation, motive, income, monthlycontribution, email);
                    call.enqueue(new Callback<signupResponse>() {
                        @Override
                        public void onResponse(Call<signupResponse> call, Response<signupResponse> response) {
                            if (response.isSuccessful()) {
                                progress.dismiss();
//                                sharedPrefManager.setID(response.body().getId().getId().toString());
                                startActivity(new Intent(ClubJoiningFormTwoActivity.this,MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ClubJoiningFormTwoActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<signupResponse> call, Throwable t) {
                            Toast.makeText(ClubJoiningFormTwoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}