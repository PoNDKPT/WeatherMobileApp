package com.example.weatherapp;

import com.example.weatherapp.Service.OpenWeatherService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";


    private static HttpManager instances;

    public static HttpManager getInstance(){
        if(instances == null)
            instances = new HttpManager();
        return instances;
    }
    private OpenWeatherService service;

    private HttpManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherService.class);
    }

    public OpenWeatherService getService(){
        return service;
    }
}
