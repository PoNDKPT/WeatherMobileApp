package com.example.weatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Model.DailyModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    private List<DailyModel.Daily> DailyTemp;
    Context context;

    public RecyclerAdapter(Context context, List<DailyModel.Daily> mDailyTemp){
        this.context = context;
        this.DailyTemp = mDailyTemp;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setItem(position);
    }

    @Override
    public int getItemCount() {
        return DailyTemp.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView DailyDateID, DailyTempID;
        ImageView DailyIconID;

        public Holder(View itemView) {
            super(itemView);
            DailyDateID = (TextView) itemView.findViewById(R.id.DailyDate);
            DailyTempID = (TextView) itemView.findViewById(R.id.DailyTemp);
            DailyIconID = (ImageView) itemView.findViewById(R.id.iconDaily);
        }

        public void setItem(int position){
            DailyModel.Daily items = DailyTemp.get(position);

            // Set Date
            String dailyDate = String.valueOf(items.getDt());

            Date date = new Date(items.getDt()*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
            String formattedDate = sdf.format(date);
            DailyDateID.setText(formattedDate);

            // Set Temp
            String dailyTempMin = String.valueOf(Math.round(items.getTemp().getMin()));
            String dailyTempMax = String.valueOf(Math.round(items.getTemp().getMax()));
            String dailyTemp = dailyTempMax + " / " + dailyTempMin + "°C" + "\n";
            DailyTempID.setText(dailyTemp);

            // Set Icon
            List<DailyModel.Weather> dailyWeather = new ArrayList<>(items.getWeather());
            for(DailyModel.Weather list : dailyWeather){
                String dailyIcon = list.getIcon();
                Glide.with(context)
                        .load("http://openweathermap.org/img/wn/" + dailyIcon + "@2x.png")
                        .into(DailyIconID);
            }

        }
    }
}

