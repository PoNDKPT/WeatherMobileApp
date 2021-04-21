package com.example.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyModel {

    @Expose
    @SerializedName("hourly")
    private List<Hourly> hourly;
    @Expose
    @SerializedName("timezone_offset")
    private int timezone_offset;
    @Expose
    @SerializedName("timezone")
    private String timezone;
    @Expose
    @SerializedName("lon")
    private double lon;
    @Expose
    @SerializedName("lat")
    private double lat;

    public List<Hourly> getHourly() {
        return hourly;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public String getTimezone() {
        return timezone;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public static class Hourly {
        @Expose
        @SerializedName("rain")
        private Rain rain;
        @Expose
        @SerializedName("pop")
        private double pop;
        @Expose
        @SerializedName("weather")
        private List<Weather> weather;
        @Expose
        @SerializedName("wind_gust")
        private double wind_gust;
        @Expose
        @SerializedName("wind_deg")
        private int wind_deg;
        @Expose
        @SerializedName("wind_speed")
        private double wind_speed;
        @Expose
        @SerializedName("visibility")
        private int visibility;
        @Expose
        @SerializedName("clouds")
        private int clouds;
        @Expose
        @SerializedName("uvi")
        private double uvi;
        @Expose
        @SerializedName("dew_point")
        private double dew_point;
        @Expose
        @SerializedName("humidity")
        private int humidity;
        @Expose
        @SerializedName("pressure")
        private int pressure;
        @Expose
        @SerializedName("feels_like")
        private double feels_like;
        @Expose
        @SerializedName("temp")
        private double temp;
        @Expose
        @SerializedName("dt")
        private int dt;

        public Rain getRain() {
            return rain;
        }

        public double getPop() {
            return pop;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public double getWind_gust() {
            return wind_gust;
        }

        public int getWind_deg() {
            return wind_deg;
        }

        public double getWind_speed() {
            return wind_speed;
        }

        public int getVisibility() {
            return visibility;
        }

        public int getClouds() {
            return clouds;
        }

        public double getUvi() {
            return uvi;
        }

        public double getDew_point() {
            return dew_point;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getPressure() {
            return pressure;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public double getTemp() {
            return temp;
        }

        public int getDt() {
            return dt;
        }
    }

    public static class Rain {
        @Expose
        @SerializedName("oneh")
        private double oneh;

        public double getOneh() {
            return oneh;
        }
    }

    public static class Weather {
        @Expose
        @SerializedName("icon")
        private String icon;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("main")
        private String main;
        @Expose
        @SerializedName("id")
        private int id;

        public String getIcon() {
            return icon;
        }

        public String getDescription() {
            return description;
        }

        public String getMain() {
            return main;
        }

        public int getId() {
            return id;
        }
    }
}
