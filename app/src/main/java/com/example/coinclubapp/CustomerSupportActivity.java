package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coinclubapp.Adapters.IssuesAdapter;
import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Response.CustomerResponse;
import com.example.coinclubapp.Response.IssueResponse;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityCustomerSupportBinding;
import com.example.coinclubapp.result.Issue;

import java.util.Collections;
import java.util.List;

import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerSupportActivity extends AppCompatActivity {
    ActivityCustomerSupportBinding binding;
    String issueSelected;
    ApiInterface apiInterface;
    Boolean[] mamle;
    String[] issues = {"Please select any Issue",
            "Having trouble in adding money",
            "Withdraw money issues",
            "Club joining issues",
            "Missed a round of current club",
            "Not recieving round notification",
            "Contact to our customer support team",
            "Want to raise a comaplaint ticket",
            "other issues"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
        Call<List<IssueResponse>> call = apiInterface.getIssue();
        call.enqueue(new Callback<List<IssueResponse>>() {
            @Override
            public void onResponse(Call<List<IssueResponse>> call, Response<List<IssueResponse>> response) {
                if (response.isSuccessful()) {
                    List<IssueResponse> issueResponses = response.body();
//                    issueResponses.get(0);
                    String tamp = "Please select any Issue,,,";

                    for (int i = 0; i < issueResponses.size(); i++) {

                        tamp = tamp.concat(response.body().get(i).getIssue() + ",,,");

//                        Log.i("jdiogjoifdjgijd",response.body().get(i).getIssue());
                    }
                    String[] strArray = tamp.split(",,,");
                    Log.i("yogesh bhai", tamp);


                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, strArray);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    binding.spinner.setAdapter(aa);

                    binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            issueSelected = strArray[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<IssueResponse>> call, Throwable t) {
                Log.e("hfdouhfidjfiodjoiufjdf", t.getMessage());
                Toast.makeText(CustomerSupportActivity.this, "Failure...", Toast.LENGTH_SHORT).show();
            }
        });


        Call<List<CustomerResponse>> call2 = apiInterface.getCustomerIssue();
        call2.enqueue(new Callback<List<CustomerResponse>>() {
            @Override
            public void onResponse(Call<List<CustomerResponse>> call, Response<List<CustomerResponse>> response) {
                if (response.isSuccessful()) {
                    List<CustomerResponse> customerResponses = response.body();
                    mamle = new Boolean[response.body().size()];
//                    customerResponses.get(0).getStatus();

                    for (int i = 0; i < customerResponses.size(); i++) {

                        mamle[i] = customerResponses.get(i).getStatus();
                    }


                    binding.recyclerview.setAdapter(new IssuesAdapter(mamle));
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                }
            }

            @Override
            public void onFailure(Call<List<CustomerResponse>> call, Throwable t) {

            }
        });


        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String disc = binding.etDisc.getText().toString();
                String spinner = binding.spinner.getSelectedItem().toString();


                if (binding.etDisc.getText().toString().isEmpty()) {
                    binding.etDisc.setError("Please Type Your Issue's");
                    binding.etDisc.requestFocus();
                } else if (binding.spinner.getSelectedItem().equals("Please select any Issue")) {
                    Toast.makeText(CustomerSupportActivity.this, "Please Select Your Issue", Toast.LENGTH_SHORT).show();
                } else {
                    Call<Issue> call3 = apiInterface.postCustomerIssue(disc, spinner);
                    call3.enqueue(new Callback<Issue>() {
                        @Override
                        public void onResponse(Call<Issue> call, Response<Issue> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CustomerSupportActivity.this, "your Issue has been submitted to our team ," +
                                        " please wait for 24 hours", Toast.LENGTH_SHORT).show();
                                Log.i("ISSUESELECTED", issueSelected);
                            } else {
                                Toast.makeText(CustomerSupportActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Issue> call, Throwable t) {
                            Toast.makeText(CustomerSupportActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}