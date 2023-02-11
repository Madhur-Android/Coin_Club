package com.example.coinclubapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coinclubapp.Club_Activity;
import com.example.coinclubapp.R;
import com.example.coinclubapp.result.ClubResult;

import java.text.ParseException;
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
        try {
            countDownFunc(holder, current.getStartdate());
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
                intent.putExtra("clubName", current.getClubname());
                intent.putExtra("countDownTime", finalDate);
                intent.putExtra("clubAmount", current.getClubamount());
                intent.putExtra("perHead", current.getClubcontribution());
                intent.putExtra("nextBid", current.getNextround());
                context.startActivity(intent);
            }
        });
    }

    private void countDownFunc(MyViewHolder holder, String mydate) throws ParseException {

        if (mydate != null && holder != null) {
            xdate = mydate.replace("T", " ");
            mydate = xdate.replace("Z", "");
            finalDate = mydate;

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date event_date = dateFormat.parse(finalDate);
            Date current_date = new Date();
            long diff = event_date.getTime() - current_date.getTime();
            long Seconds = diff / 1000 % 60;
            //

            String strSecond = Long.toString(Seconds);

            if (strSecond.contains("-")) {
                if (holder.getAdapterPosition() == clubResultList.size()) {
                    holder.startBiddingTv.setVisibility(View.VISIBLE);
                    holder.ll1.setVisibility(View.GONE);
                    holder.ll2.setVisibility(View.GONE);
                    holder.ll3.setVisibility(View.GONE);
                    holder.ll4.setVisibility(View.GONE);
                } else {
                    holder.startBiddingTv.setVisibility(View.VISIBLE);
                    holder.ll1.setVisibility(View.GONE);
                    holder.ll2.setVisibility(View.GONE);
                    holder.ll3.setVisibility(View.GONE);
                    holder.ll4.setVisibility(View.GONE);
                }


            } else {
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

                            String strDay = Long.toString(Seconds);
                            String strHour = Long.toString(Seconds);
                            String strMinute = Long.toString(Seconds);
                            String strSecond = Long.toString(Seconds);

//                            if (strDay.contains("-") || strHour.contains("-") || strMinute.contains("-") || strSecond.contains("-")) {
//                                holder.startBiddingTv.setVisibility(View.VISIBLE);
//                                holder.ll1.setVisibility(View.GONE);
//                                holder.ll2.setVisibility(View.GONE);
//                                holder.ll3.setVisibility(View.GONE);
//                                holder.ll4.setVisibility(View.GONE);
//
//                            } else {
                            holder.tv_days.setText(String.format("%02d", Days));
                            holder.tv_hour.setText(String.format("%02d", Hours));
                            holder.tv_minute.setText(String.format("%02d", Minutes));
                            holder.tv_second.setText(String.format("%02d", Seconds));
                            holder.startBiddingTv.setVisibility(View.GONE);

                            holder.ll1.setVisibility(View.VISIBLE);
                            holder.ll2.setVisibility(View.VISIBLE);
                            holder.ll3.setVisibility(View.VISIBLE);
                            holder.ll4.setVisibility(View.VISIBLE);
//                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                handler.postDelayed(runnable, 0);
            }


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
        TextView tv_days, tv_hour, tv_minute, tv_second, startBiddingTv;
        LinearLayout ll1, ll2, ll3, ll4;


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
            startBiddingTv = itemView.findViewById(R.id.startBiddingTv);
            ll1 = itemView.findViewById(R.id.ll1);
            ll2 = itemView.findViewById(R.id.ll2);
            ll3 = itemView.findViewById(R.id.ll3);
            ll4 = itemView.findViewById(R.id.ll4);


        }

    }

}