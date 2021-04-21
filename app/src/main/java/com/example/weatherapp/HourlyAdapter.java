package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Model.DailyModel;
import com.example.weatherapp.Model.HourlyModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.Holder> {
    Context context;
    List<HourlyModel.Hourly> HourlyData;


    public HourlyAdapter(Context context, List<HourlyModel.Hourly> mHourly){
        this.context = context;
        this.HourlyData = mHourly;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_item_layout, parent, false);
        return new HourlyAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setItem(position);
    }

    @Override
    public int getItemCount() {
        return HourlyData.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView TimeHourlyID, TempHourlyID;
        ImageView HourlyIconID;

        public Holder(@NonNull View itemView) {
            super(itemView);
            TimeHourlyID = itemView.findViewById(R.id.TimeHourly);
            TempHourlyID = itemView.findViewById(R.id.TempHourly);
            HourlyIconID = itemView.findViewById(R.id.iconHourly);
        }

        public void setItem(int position){
            HourlyModel.Hourly items = HourlyData.get(position);

            String TimeHourly = String.valueOf(items.getDt());
            Date date = new Date(items.getDt()*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("ha");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
            String formattedDate = sdf.format(date);
            TimeHourlyID.setText(formattedDate);

            String TempHourly = String.valueOf(Math.round(items.getTemp()) + "°C");
            TempHourlyID.setText(TempHourly);

            List<HourlyModel.Weather> hourlyWeather = new ArrayList<>(items.getWeather());
            for(HourlyModel.Weather list : hourlyWeather){
                String hourlyIcon = list.getIcon();
                Glide.with(context)
                        .load("http://openweathermap.org/img/wn/" + hourlyIcon + "@2x.png")
                        .into(HourlyIconID);
            }

        }
    }
}
