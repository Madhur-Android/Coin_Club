package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.coinclubapp.databinding.ActivityClubJoiningFormOneBinding;

public class ClubJoiningFormOneActivity extends AppCompatActivity {

    ActivityClubJoiningFormOneBinding binding;
    Intent passIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClubJoiningFormOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rbSalary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.rbStudent.setChecked(false);
                    binding.rbSelfEmployed.setChecked(false);
                    binding.rbHousewife.setChecked(false);
                }
            }
        });
        binding.rbStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.rbSalary.setChecked(false);
                    binding.rbSelfEmployed.setChecked(false);
                    binding.rbHousewife.setChecked(false);
                }
            }
        });
        binding.rbSelfEmployed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    binding.rbStudent.setChecked(false);
                    binding.rbSalary.setChecked(false);
                    binding.rbHousewife.setChecked(false);
                }
            }
        });
        binding.rbHousewife.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.rbStudent.setChecked(false);
                    binding.rbSelfEmployed.setChecked(false);
                    binding.rbSalary.setChecked(false);
                }
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = binding.fullNameEt.getText().toString().trim();
                String[] arrOfStr = fullName.split(" ");
                if (binding.fullNameEt.getText().toString().isEmpty()) {
                    binding.fullNameEt.setError("enter your name");
                    binding.fullNameEt.requestFocus();
                } else if (arrOfStr.length < 2) {
                    binding.fullNameEt.setError("enter your full name");
                    binding.fullNameEt.requestFocus();
                } else if (binding.mobileNoEt.getText().toString().trim().length() != 10) {
                    binding.mobileNoEt.setError("enter correct mobile no");
                    binding.mobileNoEt.requestFocus();
                } else if (binding.cityEt.getText().toString().trim().isEmpty()) {
                    binding.cityEt.setError("enter city name");
                    binding.cityEt.requestFocus();
                } else if (binding.etPassword.getText().toString().trim().isEmpty()) {
                    binding.etPassword.setError("enter phone number");
                    binding.etPassword.requestFocus();
                } else if (!binding.rbMale.isChecked() && !binding.rbFemale.isChecked()) {
                    Toast.makeText(ClubJoiningFormOneActivity.this, "enter your gender", Toast.LENGTH_SHORT).show();
                } else if (!binding.rbSalary.isChecked() && !binding.rbHousewife.isChecked() && !binding.rbStudent.isChecked() && !binding.rbSelfEmployed.isChecked()) {
                    Toast.makeText(ClubJoiningFormOneActivity.this, "please enter your occupation", Toast.LENGTH_SHORT).show();
                } else if (binding.noyoEt.getText().toString().isEmpty()) {
                    binding.noyoEt.setError("enter email address");
                    binding.noyoEt.requestFocus();
                } else {
                    passIntent = new Intent(ClubJoiningFormOneActivity.this, ClubJoiningFormTwoActivity.class);
                    String name = binding.fullNameEt.getText().toString();
                    passIntent.putExtra("full_name", name);
                    Log.i("USER_ENTERED", name);

                    String city = binding.cityEt.getText().toString();
                    passIntent.putExtra("city", city);
                    Log.i("USER_ENTERED", city);

                    if (binding.rbMale.isChecked()) {
                        passIntent.putExtra("gender", "male");
                        Log.i("USER_ENTERED", "male");
                    } else {
                        passIntent.putExtra("gender", "female");
                        Log.i("USER_ENTERED", "female");
                    }


                    if (binding.rbSalary.isChecked()) {
                        passIntent.putExtra("occupation", "salaried professional");
                        Log.i("USER_ENTERED", "salaried");
                    } else if (binding.rbStudent.isChecked()) {
                        passIntent.putExtra("occupation", "student");
                        Log.i("USER_ENTERED", "student");
                    } else if (binding.rbSelfEmployed.isChecked()) {
                        passIntent.putExtra("occupation", "self employed");
                        Log.i("USER_ENTERED", "self");
                    } else {
                        passIntent.putExtra("occupation", "house wife");
                        Log.i("USER_ENTERED", "wife");
                    }

                    String noyo = binding.noyoEt.getText().toString();
                    passIntent.putExtra("organization", noyo);
                    Log.i("USER_ENTERED", noyo);

                    String mobile = binding.mobileNoEt.getText().toString();
                    passIntent.putExtra("mobile", mobile);
                    Log.i("USER_ENTERED", mobile);

                    String password = binding.etPassword.getText().toString();
                    passIntent.putExtra("password",password);
                    Log.i("USER_ENTERED",password);

                    startActivity(passIntent);
                    finish();
                }
            }
        });
    }
}