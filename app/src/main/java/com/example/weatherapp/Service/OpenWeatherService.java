package com.example.weatherapp.Service;

import com.example.weatherapp.Model.DailyModel;
import com.example.weatherapp.Model.HourlyModel;
import com.example.weatherapp.Model.OpenWeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {
    @GET("weather")
    Call<OpenWeatherModel> getData(@Query("appid") String appid,
                                   @Query("q") String q,
                                   @Query("units") String units,
                                   @Query("lat") String lat,
                                   @Query("lon") String lon);

    @GET("onecall")
    Call<DailyModel> getDailyData(@Query("lat") String lat,
                                  @Query("lon") String lon,
                                  @Query("exclude") String exclude,
                                  @Query("units") String units,
                                  @Query("appid") String appid);

    @GET("onecall")
    Call<HourlyModel> getHourlyData(@Query("lat") String lat,
                                    @Query("lon") String lon,
                                    @Query("exclude") String exclude,
                                    @Query("units") String units,
                                    @Query("appid") String appid);
}
