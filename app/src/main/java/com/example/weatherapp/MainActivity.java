package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Model.DailyModel;
import com.example.weatherapp.Model.HourlyModel;
import com.example.weatherapp.Model.OpenWeatherModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView TemperatureID, WeatherTextID, FeelsLikeID, DailyDateID, DailyTempID, WindID, HumidityID, VisibilityID, PressureID;
    ImageView iconTempID;
    androidx.recyclerview.widget.RecyclerView DailyRecyclerViewID, HourlyRecyclerViewID;
    SwipeRefreshLayout SwipeRefreshID;

    SearchView searchView;

    ArrayList<DailyModel.Daily> mDaily;
    ArrayList<HourlyModel.Hourly> mHourly;

    String lat, lon = "";
    String SearchCity = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TemperatureID = findViewById(R.id.Temperature);
        WeatherTextID = findViewById(R.id.WeatherText);
        FeelsLikeID = findViewById(R.id.FeelsLike);
        iconTempID = findViewById(R.id.iconTemp);
        DailyDateID = findViewById(R.id.DailyDate);
        DailyTempID = findViewById(R.id.DailyTemp);
        DailyRecyclerViewID = findViewById(R.id.DailyRecyclerView);
        HourlyRecyclerViewID = findViewById(R.id.HourlyRecyclerView);
        SwipeRefreshID = findViewById(R.id.SwipeRefresh);

        WindID = findViewById(R.id.Wind);
        HumidityID = findViewById(R.id.Humidity);
        VisibilityID = findViewById(R.id.Visibility);
        PressureID = findViewById(R.id.Pressure);

        searchView = findViewById(R.id.search_view);

        getCurrentData("bangkok");
        getHourlyData("13.75", "100.5167");
        getDailyData("13.75", "100.5167");
        SwipeRefreshLayout();
    }

    private void getCurrentData(String cityName){
        String CITY_NAME = cityName;
        String API_KEY = "3d183a4ae8316fa41f6131ff5189ff7a";
        String UNITS = "metric";

        Call<OpenWeatherModel> call = HttpManager.getInstance().getService().getData(API_KEY, CITY_NAME, UNITS);
        call.enqueue(new Callback<OpenWeatherModel>() {
            @Override
            public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                if(response.isSuccessful()){
                    OpenWeatherModel dao = response.body();

                    lat = String.valueOf(dao.getCoord().getLat());
                    lon = String.valueOf(dao.getCoord().getLon());

                    // Set Title Application
                    String City = dao.getName();
                    String Country = dao.getSys().getCountry();
                    setTitle(City + ", " + Country);


                    String Feels_Like = String.valueOf(Math.round(dao.getMain().getFeels_like()));
                    String Temp = String.valueOf(Math.round(dao.getMain().getTemp()));

                    TemperatureID.setText(Temp + "°C");
                    FeelsLikeID.setText(Feels_Like + "°C");
                    WindID.setText(String.valueOf(dao.getWind().getSpeed()) + "m/s SSE");
                    HumidityID.setText(String.valueOf(dao.getMain().getHumidity()) + "%");
                    VisibilityID.setText(String.valueOf(dao.getVisibility()/1000) + "km");
                    PressureID.setText(String.valueOf(dao.getMain().getPressure()) + "hPa");


                    // Get Weather
                    List<OpenWeatherModel.Weather> lists = new ArrayList<>(dao.getWeather());
                    String icon = "";
                    for(OpenWeatherModel.Weather list : lists){
                        WeatherTextID.setText(list.getDescription().substring(0, 1).toUpperCase() + list.getDescription().substring(1));
                        icon = list.getIcon();
                    }

                    // Set ImageView
                    setIcon(icon);
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherModel> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        , t.toString()
                        , Toast.LENGTH_SHORT)
                        .show();
                Log.e("ErrorCurrentData", t.getMessage());
            }
        });
    }

    private void getDailyData(String lat, String lon){
        String LAT = lat;
        String LON = lon;
        String EXCLUDE = "minutely,alerts,hourly,current";
        String API_KEY = "3d183a4ae8316fa41f6131ff5189ff7a";
        String UNITS = "metric";

        Call<DailyModel> call = HttpManager.getInstance().getService().getDailyData(LAT, LON, EXCLUDE, UNITS, API_KEY);

        call.enqueue(new Callback<DailyModel>() {
            @Override
            public void onResponse(Call<DailyModel> call, Response<DailyModel> response) {
                if(response.isSuccessful()) {
                    DailyModel dao = response.body();

                    mDaily = new ArrayList<>(dao.getDaily());
                    RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this, mDaily);
                    DailyRecyclerViewID.setAdapter(adapter);
                    DailyRecyclerViewID.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    // Scroll Smooth
                    DailyRecyclerViewID.setNestedScrollingEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<DailyModel> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        , t.toString()
                        , Toast.LENGTH_SHORT)
                        .show();
                Log.e("ErrorDailyData", t.getMessage());
            }
        });
    }

    private void getHourlyData(String lat, String lon){
        String LAT = lat;
        String LON = lon;
        String EXCLUDE = "minutely,alerts,current,daily";
        String API_KEY = "3d183a4ae8316fa41f6131ff5189ff7a";
        String UNITS = "metric";

        Call<HourlyModel> call = HttpManager.getInstance().getService().getHourlyData(LAT, LON, EXCLUDE, UNITS, API_KEY);

        call.enqueue(new Callback<HourlyModel>() {
            @Override
            public void onResponse(Call<HourlyModel> call, Response<HourlyModel> response) {
                if(response.isSuccessful()){
                    HourlyModel dao = response.body();

                    mHourly = new ArrayList<>(dao.getHourly());

                    HourlyAdapter adapter = new HourlyAdapter(MainActivity.this, mHourly);
                    HourlyRecyclerViewID.setAdapter(adapter);
                    HourlyRecyclerViewID.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                }
            }

            @Override
            public void onFailure(Call<HourlyModel> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        , t.toString()
                        , Toast.LENGTH_SHORT)
                        .show();
                Log.e("ErrorHourlyData", t.getMessage());
            }
        });
    }

    private void SwipeRefreshLayout(){
        SwipeRefreshID.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCurrentData(SearchCity);
                getDailyData(lat, lon);
                getHourlyData(lat, lon);

                SwipeRefreshID.setRefreshing(false);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Initialize menu inflater
        MenuInflater menuInflater = getMenuInflater();
        //Inflate menu
        menuInflater.inflate(R.menu.menu_search, menu);
        //Initialize menu item
        MenuItem menuItem = menu.findItem(R.id.search_view);
        //Initialize search view
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchCity = searchView.getQuery().toString();
                Log.d("SearchView", SearchCity);
                getCurrentData(SearchCity);
                getDailyData(lat, lon);
                getHourlyData(lat, lon);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void setIcon(String icon){
        Glide.with(MainActivity.this)
                .load("http://openweathermap.org/img/wn/" + icon + "@2x.png")
                .into(iconTempID);
    }
} 