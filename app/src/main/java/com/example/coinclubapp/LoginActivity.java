package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Response.UserLoginResponse;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        ApiInterface apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);




        binding.goBtn.setOnClickListener(v -> {
            if (binding.edMobile.getText().toString().trim().isEmpty()) {
                binding.edMobile.setError("Please Enter Mobile Number");
                binding.edMobile.requestFocus();
            } else if (binding.edMobile.getText().toString().trim().length() < 10) {
                binding.edMobile.setError("Enter Correct Mobile Number");
                binding.edMobile.requestFocus();
            } else if (binding.edPassword.getText().toString().trim().isEmpty()) {
                binding.edPassword.setError("Enter Password Number");
                binding.edPassword.requestFocus();
            } else {

                String mobileno = binding.edMobile.getText().toString();
                String password = binding.edPassword.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                Call<UserLoginResponse> call = apiInterface.loginUser(mobileno, password);
                call.enqueue(new Callback<UserLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            if (response.body().getStatus().equals("True")) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Integer loginId=response.body().getId();
                                editor.putInt("Id", loginId);
                                editor.apply();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

                                WifiManager wifiManager=(WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                                Log.i("IP_ADDRESS", Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress()));

                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "You are not authorized by the admin", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        binding.btnSignUp.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this, ClubJoiningFormOneActivity.class));
        });
    }
}