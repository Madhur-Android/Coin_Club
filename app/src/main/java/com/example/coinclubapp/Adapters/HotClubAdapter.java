package com.example.coinclubapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coinclubapp.Club_Activity;
import com.example.coinclubapp.R;
import com.example.coinclubapp.result.ClubResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HotClubAdapter extends RecyclerView.Adapter<HotClubAdapter.MyViewHolder> {

    Context context;
    List<ClubResult> clubResultList;
    String xdate, mydate;


    private static String finalDate;
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    Handler handler = new Handler();
    Runnable runnable;

    public HotClubAdapter(Context context, List<ClubResult> clubResultList) {
        this.context = context;
        this.clubResultList = clubResultList;
    }

    @NonNull
    @Override
    public HotClubAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hot_club_cardview_layout, parent, false);

        return new HotClubAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotClubAdapter.MyViewHolder holder, int position) {

        ClubResult current = clubResultList.get(position);
        countDownFunc(holder, current.getStartdate());


        holder.txtName.setText(current.getClubname());
        holder.txtDesc.setText("per head : " + current.getClubcontribution() + " ₹ ");
        holder.txtRound.setText("round 3 of " + current.getClubmembers());
        holder.txtAmount.setText(current.getClubamount() + " ₹");
        holder.txtNextBid.setText("Next Bid : " + current.getNextround());
        Glide.with(context)
                .load(current.getClubimage())
                .centerCrop()
                .placeholder(R.drawable.logo_money)
                .into(holder.logo);

        holder.Club_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Club_Activity.class);
                context.startActivity(intent);
            }
        });
    }

    private void countDownFunc(MyViewHolder holder, String mydate) {

        if (mydate != null) {
            xdate = mydate.replace("T", " ");
            mydate = xdate.replace("Z", "");
            finalDate = mydate;

            runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        handler.postDelayed(this, 1000);
                        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                        Date event_date = dateFormat.parse(finalDate);
                        Date current_date = new Date();
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        //
                        holder.tv_days.setText(String.format("%02d", Days));
                        holder.tv_hour.setText(String.format("%02d", Hours));
                        holder.tv_minute.setText(String.format("%02d", Minutes));
                        holder.tv_second.setText(String.format("%02d", Seconds));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 0);
        }

    }

    @Override
    public int getItemCount() {
        return clubResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDesc, txtNextBid, txtRound, txtAmount;
        ConstraintLayout Club_layout;
        ImageView logo;
        TextView tv_days, tv_hour, tv_minute, tv_second;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtNextBid = itemView.findViewById(R.id.txtNextBid);
            txtRound = itemView.findViewById(R.id.txtRound);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            Club_layout = itemView.findViewById(R.id.Club_layout);
            logo = itemView.findViewById(R.id.logo);
            tv_days = itemView.findViewById(R.id.tv_days);
            tv_hour = itemView.findViewById(R.id.tv_hour);
            tv_minute = itemView.findViewById(R.id.tv_minute);
            tv_second = itemView.findViewById(R.id.tv_second);


        }

    }

}