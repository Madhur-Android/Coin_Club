package com.example.coinclubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import com.example.coinclubapp.Adapters.MemberAdapter;
import com.example.coinclubapp.Adapters.RoundAdapter;
import com.example.coinclubapp.InterFace.ApiInterface;
import com.example.coinclubapp.Retrofit.RetrofitService;
import com.example.coinclubapp.databinding.ActivityClubBinding;

import com.example.coinclubapp.result.Id;
import com.example.coinclubapp.result.RoundsResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubActivity extends AppCompatActivity {
    ActivityClubBinding binding;

    RecyclerView.LayoutManager layoutManagerM;
    RecyclerView.LayoutManager layoutManagerR;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
        binding.bidStartIn.setEnabled(false);
        Intent ii = getIntent();
        binding.clubName.setText(ii.getStringExtra("clubName"));
        binding.clubAmountTv.setText(ii.getStringExtra("clubAmount"));
        binding.perHeadTv.setText(ii.getStringExtra("perHead"));
        binding.nextRoundTv.setText(ii.getStringExtra("nextBid"));


        String timepassed = ii.getStringExtra("time");


        try {
            countDownFunc(timepassed);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        binding.bidStartIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.bidStartIn.isEnabled()) {
                    startActivity(new Intent(ClubActivity.this, BidRoomActivity.class));
                } else {
                    Toast.makeText(ClubActivity.this, "Button Is Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.backBtn.setOnClickListener(v -> {
            Intent i = new Intent(ClubActivity.this, HotClubActivity.class);
            startActivity(i);
            finish();
        });

        layoutManagerM = new LinearLayoutManager(ClubActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewMember.setLayoutManager(layoutManagerM);


        layoutManagerR = new LinearLayoutManager(ClubActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewRound.setLayoutManager(layoutManagerR);

        Call<List<Id>> call1 = apiInterface.getAllRegisteredUsers();
        call1.enqueue(new Callback<List<Id>>() {
            @Override
            public void onResponse(Call<List<Id>> call, Response<List<Id>> response) {
                if (response.isSuccessful()) {
                    binding.recyclerViewMember.setAdapter(new MemberAdapter(ClubActivity.this, response.body()));
                } else {
                    Toast.makeText(ClubActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Id>> call, Throwable t) {
                Toast.makeText(ClubActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Call<List<RoundsResult>> call = apiInterface.getAllRounds();
        call.enqueue(new Callback<List<RoundsResult>>() {
            @Override
            public void onResponse(Call<List<RoundsResult>> call, Response<List<RoundsResult>> response) {
                if (response.isSuccessful()) {
                    binding.recyclerViewRound.setAdapter(new RoundAdapter(response.body()));
                }
            }


            @Override
            public void onFailure(Call<List<RoundsResult>> call, Throwable t) {
                Toast.makeText(ClubActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.seeAllMember.setOnClickListener(v -> {
            Intent i = new Intent(ClubActivity.this, MemberActivity.class);
            startActivity(i);
        });
    }

    private void countDownFunc(String mydate) throws ParseException {

            if (mydate != null) {
                String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

                Handler handler = new Handler();

                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                Date event_date = dateFormat.parse(mydate);
                Date current_date = new Date();
                if (event_date.getTime() - current_date.getTime() > 0) {
                    Runnable runnable;
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                handler.postDelayed(this, 1000);
                                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                                Date event_date = dateFormat.parse(mydate);
                                Date current_date = new Date();
                                if (!current_date.after(event_date)) {
                                    long diff = event_date.getTime() - current_date.getTime();
                                    long Days = diff / (24 * 60 * 60 * 1000);
                                    long Hours = diff / (60 * 60 * 1000) % 24;
                                    long Minutes = diff / (60 * 1000) % 60;
                                    long Seconds = diff / 1000 % 60;

                                    binding.bidStartIn.setText("");
                                    binding.bidStartIn.setEnabled(false);
                                    binding.bidStartIn.setText("Bid Starts in " + String.format("%02d", Days) + " days " + String.format("%02d", Hours) + " hours " + String.format("%02d", Minutes) + " minutes " + String.format("%02d", Seconds) + " seconds");



                                } else {
                                    handler.removeCallbacks(null);

//                                    binding.bidStartIn.setText("Start Bidding");
//
//                                    binding.bidStartIn.setEnabled(true);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    handler.postDelayed(runnable, 0);
                } else {

                    binding.bidStartIn.setText("Start Bidding");

                    binding.bidStartIn.setEnabled(true);
                }

            }
            else
            {
                binding.bidStartIn.setText("Start Bidding");
                binding.bidStartIn.setEnabled(true);
        }
    }
}
