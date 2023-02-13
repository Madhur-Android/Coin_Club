package com.example.coinclubapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
                Intent imgIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent, 101);
            }
        });

        binding.submitBtn.setOnClickListener(v -> {
            String fullName = binding.accountHolderNameET.getText().toString().trim();
            String[] arrOfStr = fullName.split(" ");

            if (binding.accountHolderNameET.getText().toString().isEmpty()) {
                binding.accountHolderNameET.setError("Enter Your Name");
                binding.accountHolderNameET.requestFocus();
            } else if (arrOfStr.length < 2) {
                binding.accountHolderNameET.setError("Enter Your Full Name");
                binding.accountHolderNameET.requestFocus();
            } else if (binding.accountNumberET.getText().toString().isEmpty()) {
                binding.accountNumberET.setError("Enter Account Number");
                binding.accountNumberET.requestFocus();
            } else if (binding.ifscET.getText().toString().isEmpty()) {
                binding.accountNumberET.setError("Enter IFSC code");
                binding.accountNumberET.requestFocus();
            } else if (binding.registerMobileET.getText().toString().trim().length() != 10) {
                binding.registerMobileET.setError("Enter Correct Mobile No");
                binding.registerMobileET.requestFocus();
            } else if (!bankPassbook) {
                Toast.makeText(BankDetailsActivity.this, "Please Upload Bank Passbook Photo", Toast.LENGTH_SHORT).show();
            } else if (!binding.check.isChecked()) {
                Toast.makeText(BankDetailsActivity.this, "Please Select Checkbox", Toast.LENGTH_SHORT).show();
            } else {

                String Mobile = binding.registerMobileET.getText().toString();
                String IFScode = binding.ifscET.getText().toString();
                String AccName = binding.accountHolderNameET.getText().toString();
                String AccNumber = binding.accountNumberET.getText().toString();

                sendDetails(Mobile,IFScode,AccName,AccNumber);

            }
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
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            FileOutputStream outputStream = null;
//            try {
//                outputStream = new FileOutputStream(imagePath);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//            binding.BankPassbook.setImageBitmap(bitmap);
            bankPassbook = true;
        }
    }

    private void sendDetails(String Mobile, String ifsccode, String AccName, String AccNumber) {
        File file = new File (imagePath);

        RequestBody requestFile = RequestBody.create (MediaType.parse ("multipart/form-data"), file);
        MultipartBody.Part passbookimg = MultipartBody.Part.createFormData ("passbookimg", file.getName (), requestFile);

        RequestBody accountnumber = RequestBody.create (MediaType.parse ("text/plain"), AccNumber);
        RequestBody accountname = RequestBody.create (MediaType.parse ("text/plain"), AccName);
        RequestBody registerno = RequestBody.create (MediaType.parse ("text/plain"), Mobile);
        RequestBody IFSCcode = RequestBody.create (MediaType.parse ("text/plain"), ifsccode);

        apiInterface=RetrofitService.getRetrofit().create(ApiInterface.class);
        Call<BankDetailsResult> call=apiInterface.postBankDetails(registerno,IFSCcode,accountname,accountnumber,passbookimg);
        call.enqueue(new Callback<BankDetailsResult>() {
            @Override
            public void onResponse(Call<BankDetailsResult> call, Response<BankDetailsResult> response) {
                int val=response.code();
                if(val==201)
                {
                    Toast.makeText(BankDetailsActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
                else if(val==208)
                {
                    Toast.makeText(BankDetailsActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(BankDetailsActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BankDetailsResult> call, Throwable t) {
                Log.e("FAILURE",t.getMessage());
                Toast.makeText(BankDetailsActivity.this, "Some error occured,Try again after some time", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
