package com.example.coinclubapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityUpdateKycBinding;

import java.util.Calendar;

public class UpdateKycActivity extends AppCompatActivity {
    ActivityUpdateKycBinding binding;

    private static boolean frontaadhar = false;
    private static boolean backaadhar = false;
    private static boolean frontpan = false;

    Uri aadharFront;
    Uri aadharBack;
    Uri panFront;

    String uriafs, uriabs, uripfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateKycBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.aadharFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent1 = new Intent(Intent.ACTION_PICK);
                imgIntent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent1, 101);
            }
        });

        binding.aadharBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent1 = new Intent(Intent.ACTION_PICK);
                imgIntent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent1, 102);
            }
        });

        binding.panFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent1 = new Intent(Intent.ACTION_PICK);
                imgIntent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent1, 201);
            }
        });


        binding.licenseExpiryDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateKycActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "-" + (month + 1) + "-" + (year);
                        binding.licenseExpiryDateEt.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = binding.fullNameEt.getText().toString().trim();
                String[] arrOfStr = fullName.split(" ");

                String mobile = binding.mobileEt.getText().toString();

                String fullAddress = binding.addressEt.getText().toString().trim();
                String[] arrOfStr2 = fullAddress.split(" ");

                String email_id = binding.emailEt.getText().toString();

                String adhar_no = binding.adharNumberEt.getText().toString();
                String pan_no = binding.panNumberEt.getText().toString();
                String license_no = binding.licenseNumberEt.getText().toString();
                String licenseExpiryDate = binding.licenseExpiryDateEt.getText().toString();

                if (binding.fullNameEt.getText().toString().isEmpty()) {
                    binding.fullNameEt.setError("enter your name");
                    binding.fullNameEt.requestFocus();
                } else if (arrOfStr.length < 2) {
                    binding.fullNameEt.setError("enter your full name");
                    binding.fullNameEt.requestFocus();
                } else if (binding.mobileEt.getText().toString().trim().length() != 10) {
                    binding.mobileEt.setError("enter correct mobile no");
                    binding.mobileEt.requestFocus();
                } else if (binding.addressEt.getText().toString().isEmpty()) {
                    binding.addressEt.setError("enter your Address");
                    binding.addressEt.requestFocus();
                } else if (arrOfStr2.length < 2) {
                    binding.addressEt.setError("enter your full Address");
                    binding.addressEt.requestFocus();
                } else if (binding.emailEt.getText().toString().isEmpty()) {
                    binding.emailEt.setError("enter your Email Id");
                    binding.emailEt.requestFocus();
                } else if (!binding.emailEt.getText().toString().contains(".") || !binding.emailEt.getText().toString().contains("@")) {
                    binding.emailEt.setError("enter full Email address");
                    binding.emailEt.requestFocus();
                } else if (binding.adharNumberEt.getText().toString().isEmpty()) {
                    binding.adharNumberEt.setError("enter Aadhar Number");
                    binding.adharNumberEt.requestFocus();
                } else if (binding.panNumberEt.getText().toString().isEmpty()) {
                    binding.panNumberEt.setError("enter Pan Number");
                    binding.panNumberEt.requestFocus();
                } else if (!frontaadhar || !backaadhar) {
                    Toast.makeText(UpdateKycActivity.this, "please upload Aadhar Images", Toast.LENGTH_SHORT).show();
                } else if (!frontpan) {
                    Toast.makeText(UpdateKycActivity.this, "please upload Pan Images", Toast.LENGTH_SHORT).show();
                } else if (binding.licenseNumberEt.getText().toString().isEmpty()) {
                    binding.licenseNumberEt.setError("enter license number");
                    binding.licenseNumberEt.requestFocus();
                } else if (binding.licenseExpiryDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateKycActivity.this, "enter expiry date", Toast.LENGTH_SHORT).show();
                } else if (!binding.check.isChecked()) {
                    Toast.makeText(UpdateKycActivity.this, "please check the checkbox", Toast.LENGTH_SHORT).show();
                } else {
                    String id = getIntent().getStringExtra("id");

                    ApiInterface apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
                    Toast.makeText(UpdateKycActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 101) {
            binding.aadharFront.setImageURI(data.getData());
            frontaadhar = true;
            aadharFront = data.getData();
            uriafs = getRealPathFromURI(aadharFront);
        } else if (resultCode == RESULT_OK && requestCode == 102) {
            binding.aadharBack.setImageURI(data.getData());
            backaadhar = true;
            aadharBack = data.getData();
            uriabs = getRealPathFromURI(aadharBack);
        } else if (resultCode == RESULT_OK && requestCode == 201) {
            binding.panFront.setImageURI(data.getData());
            frontpan = true;
            panFront = data.getData();
            uripfs = getRealPathFromURI(panFront);
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}