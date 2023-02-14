package com.example.coinclubapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.RealPathUtilGithub.RealPathUtil;
import com.example.coinclubapp.Retrofit.RetrofitService;



import com.example.coinclubapp.databinding.ActivityBankDetailsBinding;
import com.example.coinclubapp.result.BankDetailsResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailsActivity extends AppCompatActivity {

    ActivityBankDetailsBinding binding;
    private static boolean bankPassbook = false;
    private static String imagePath;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.backBtn.setOnClickListener(v -> {

            finish();
        });

        binding.BankPassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent, 101);
            }
        });

        binding.submitBtn.setOnClickListener(v -> {
            if (!binding.check.isChecked()) {
                Toast.makeText(BankDetailsActivity.this, "Please Select Checkbox", Toast.LENGTH_SHORT).show();
            } else {
                String PhonePay = binding.accountHolderNameET.getText().toString().trim();
                String GooglePay = binding.accountNumberET.getText().toString();
                String Paytm = binding.ifscET.getText().toString();
                String BhimUPI = binding.registerMobileET.getText().toString();
                String id = "3";
                sendDetails(id, PhonePay, GooglePay, Paytm, BhimUPI);

            }
//            if (binding.accountHolderNameET.getText().toString().isEmpty()) {
//                binding.accountHolderNameET.setError("Enter Your Name");
//                binding.accountHolderNameET.requestFocus();
//            } else if (arrOfStr.length < 2) {
//                binding.accountHolderNameET.setError("Enter Your Full Name");
//                binding.accountHolderNameET.requestFocus();
//            } else if (binding.accountNumberET.getText().toString().isEmpty()) {
//                binding.accountNumberET.setError("Enter Account Number");
//                binding.accountNumberET.requestFocus();
//            } else if (binding.ifscET.getText().toString().isEmpty()) {
//                binding.accountNumberET.setError("Enter IFSC code");
//                binding.accountNumberET.requestFocus();
//            } else if (binding.registerMobileET.getText().toString().trim().length() != 10) {
//                binding.registerMobileET.setError("Enter Correct Mobile No");
//                binding.registerMobileET.requestFocus();
//            } else if (!bankPassbook) {
//                Toast.makeText(BankDetailsActivity.this, "Please Upload Bank Passbook Photo", Toast.LENGTH_SHORT).show();
//            } else if (!binding.check.isChecked()) {
//                Toast.makeText(BankDetailsActivity.this, "Please Select Checkbox", Toast.LENGTH_SHORT).show();
//            } else {

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && data != null) {

            Uri uri = data.getData();
            Context context = BankDetailsActivity.this;
            imagePath = RealPathUtil.getRealPath(context, uri);
            binding.BankPassbook.setImageURI(uri);

            bankPassbook = true;
        }
    }

    private void sendDetails(String id, String PhonePay, String GooglePay, String Paytm, String BhimUPI) {
        File file = new File(imagePath);

//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part document_image = MultipartBody.Part.createFormData("passbookimg", file.getName(), requestFile);

        MultipartBody.Part document_image = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));


        RequestBody phonepe_number = RequestBody.create(MediaType.parse("multipart/form-data"), PhonePay);
        RequestBody googlepay_number = RequestBody.create(MediaType.parse("multipart/form-data"), GooglePay);
        RequestBody paytm_number = RequestBody.create(MediaType.parse("multipart/form-data"), Paytm);
        RequestBody bhim_upi = RequestBody.create(MediaType.parse("multipart/form-data"), BhimUPI);
        RequestBody registeruser = RequestBody.create(MediaType.parse("multipart/form-data"),id);

        apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
        Call<BankDetailsResult> call = apiInterface.postBankDetails(registeruser, googlepay_number, paytm_number, phonepe_number, bhim_upi, document_image);
        call.enqueue(new Callback<BankDetailsResult>() {
            @Override
            public void onResponse(Call<BankDetailsResult> call, Response<BankDetailsResult> response) {

                    Toast.makeText(BankDetailsActivity.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BankDetailsResult> call, Throwable t) {
                Toast.makeText(BankDetailsActivity.this, "Some error occured,Try again after some time", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
