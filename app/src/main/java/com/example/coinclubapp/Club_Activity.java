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

import com.example.coinclubapp.result.FormTwoResult;
import com.example.coinclubapp.result.RoundsResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Club_Activity extends AppCompatActivity {
    ActivityClubBinding binding;

    RecyclerView.LayoutManager layoutManagerM;
    RecyclerView.LayoutManager layoutManagerR;

    Handler handler = new Handler();
    Runnable runnable;
    String xdate;

    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);
        if(binding.bidStartIn.getText().equals("Start Bidding"))
        {
            binding.bidStartIn.setEnabled(true);
        }
        else
        {
            binding.bidStartIn.setEnabled(false);
        }
        Intent ii = getIntent();
        binding.clubName.setText(ii.getStringExtra("clubName"));
        String time = ii.getStringExtra("countDownTime");
        binding.clubAmountTv.setText(ii.getStringExtra("clubAmount"));
        binding.perHeadTv.setText(ii.getStringExtra("perHead"));
        binding.nextRoundTv.setText(ii.getStringExtra("nextBid"));
        countDownFunc(time);

        binding.bidStartIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.bidStartIn.isEnabled())
                {
                    startActivity(new Intent(Club_Activity.this,BidRoomActivity.class));
                }
                else
                {
                    Toast.makeText(Club_Activity.this, "Button Is Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.backBtn.setOnClickListener(v -> {
            Intent i = new Intent(Club_Activity.this, HotClubActivity.class);
            startActivity(i);
            finish();
        });

        layoutManagerM = new LinearLayoutManager(Club_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewMember.setLayoutManager(layoutManagerM);


        layoutManagerR = new LinearLayoutManager(Club_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewRound.setLayoutManager(layoutManagerR);

        Call<List<FormTwoResult>> call1 = apiInterface.getAllRegisteredUsers();
        call1.enqueue(new Callback<List<FormTwoResult>>() {
            @Override
            public void onResponse(Call<List<FormTwoResult>> call, Response<List<FormTwoResult>> response) {
                if (response.isSuccessful()) {
                    binding.recyclerViewMember.setAdapter(new MemberAdapter(Club_Activity.this, response.body()));
                } else {
                    Toast.makeText(Club_Activity.this, "some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FormTwoResult>> call, Throwable t) {
                Toast.makeText(Club_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Club_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.seeAllMember.setOnClickListener(v -> {
            Intent i = new Intent(Club_Activity.this, MemberActivity.class);
            startActivity(i);
        });
    }

    private void countDownFunc(String mydate) {

        if (mydate != null) {
            xdate = mydate.replace("T", " ");
            mydate = xdate.replace("Z", "");

            String finalMydate = mydate;
            runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        handler.postDelayed(this, 1000);
                        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                        Date event_date = dateFormat.parse(finalMydate);
                        Date current_date = new Date();
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        String strDay = Long.toString(Seconds);
                        String strHour = Long.toString(Seconds);
                        String strMinute = Long.toString(Seconds);
                        String strSecond = Long.toString(Seconds);

                        if(strDay.contains("-") || strHour.contains("-") || strMinute.contains("-") || strSecond.contains("-"))
                        {
                            binding.bidStartIn.setText("Start Bidding");
                            handler=null;
                            binding.bidStartIn.setEnabled(true);

                        }
                        else
                        {
                            binding.bidStartIn.setEnabled(true);
                            binding.bidStartIn.setText("Bid Starts in " + String.format("%02d", Days) + " days " + String.format("%02d", Hours) + " days " + String.format("%02d", Minutes) + " days " + String.format("%02d", Seconds));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 0);
        }

    }
}