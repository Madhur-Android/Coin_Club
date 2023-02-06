package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Response.Response;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityClubJoiningFormTwoBinding;
import com.example.coinclubapp.result.Result;

import retrofit2.Call;
import retrofit2.Callback;

public class ClubJoiningFormTwoActivity extends AppCompatActivity {
    ActivityClubJoiningFormTwoBinding binding;
    ProgressDialog progress;
    int IdOfUserCreated = 0;


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
                    Log.i("USER_ENTERED", mobile);
                    String password = getIntent().getStringExtra("password");
                    Log.i("USER_ENTERED", password);
                    String name = getIntent().getStringExtra("full_name");
                    Log.i("USER_ENTERED", name);
                    String gender = getIntent().getStringExtra("gender");
                    Log.i("USER_ENTERED", gender);
                    String city = getIntent().getStringExtra("city");
                    Log.i("USER_ENTERED", city);
                    String occupation = getIntent().getStringExtra("occupation");
                    Log.i("USER_ENTERED", occupation);
                    String organisation = getIntent().getStringExtra("organization");
                    Log.i("USER_ENTERED", organisation);
                    String monthlycontribution = binding.amountEt.getText().toString();
                    Log.i("USER_ENTERED", monthlycontribution);
                    String income = binding.incomeEt.getText().toString();
                    Log.i("USER_ENTERED", income);

                    Call<Result> call = apiInterface.postItems(mobile, password, name, gender, city, occupation, organisation, monthlycontribution, motive, income);


                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
                            if (response.isSuccessful()) {
                                Result res = response.body();

                                try {
                                    IdOfUserCreated = res.getId();
                                    Intent intent = new Intent(ClubJoiningFormTwoActivity.this, MainActivity.class);
                                    progress.dismiss();
                                    intent.putExtra("id", String.valueOf(IdOfUserCreated));
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    Toast.makeText(ClubJoiningFormTwoActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(ClubJoiningFormTwoActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Toast.makeText(ClubJoiningFormTwoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }
}