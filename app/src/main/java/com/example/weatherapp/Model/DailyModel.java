package com.example.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyModel {

    @Expose
    @SerializedName("daily")
    private List<Daily> daily;
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

    public List<Daily> getDaily() {
        return daily;
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

    public static class Daily {
        @Expose
        @SerializedName("uvi")
        private double uvi;
        @Expose
        @SerializedName("rain")
        private double rain;
        @Expose
        @SerializedName("pop")
        private double pop;
        @Expose
        @SerializedName("clouds")
        private int clouds;
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
        private Feels_like feels_like;
        @Expose
        @SerializedName("temp")
        private Temp temp;
        @Expose
        @SerializedName("moon_phase")
        private double moon_phase;
        @Expose
        @SerializedName("moonset")
        private int moonset;
        @Expose
        @SerializedName("moonrise")
        private int moonrise;
        @Expose
        @SerializedName("sunset")
        private int sunset;
        @Expose
        @SerializedName("sunrise")
        private int sunrise;
        @Expose
        @SerializedName("dt")
        private int dt;

        public double getUvi() {
            return uvi;
        }

        public double getRain() {
            return rain;
        }

        public double getPop() {
            return pop;
        }

        public int getClouds() {
            return clouds;
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

        public double getDew_point() {
            return dew_point;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getPressure() {
            return pressure;
        }

        public Feels_like getFeels_like() {
            return feels_like;
        }

        public Temp getTemp() {
            return temp;
        }

        public double getMoon_phase() {
            return moon_phase;
        }

        public int getMoonset() {
            return moonset;
        }

        public int getMoonrise() {
            return moonrise;
        }

        public int getSunset() {
            return sunset;
        }

        public int getSunrise() {
            return sunrise;
        }

        public int getDt() {
            return dt;
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

    public static class Feels_like {
        @Expose
        @SerializedName("morn")
        private double morn;
        @Expose
        @SerializedName("eve")
        private double eve;
        @Expose
        @SerializedName("night")
        private double night;
        @Expose
        @SerializedName("day")
        private double day;

        public double getMorn() {
            return morn;
        }

        public double getEve() {
            return eve;
        }

        public double getNight() {
            return night;
        }

        public double getDay() {
            return day;
        }
    }

    public static class Temp {
        @Expose
        @SerializedName("morn")
        private double morn;
        @Expose
        @SerializedName("eve")
        private double eve;
        @Expose
        @SerializedName("night")
        private double night;
        @Expose
        @SerializedName("max")
        private double max;
        @Expose
        @SerializedName("min")
        private double min;
        @Expose
        @SerializedName("day")
        private double day;

        public double getMorn() {
            return morn;
        }

        public double getEve() {
            return eve;
        }

        public double getNight() {
            return night;
        }

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }

        public double getDay() {
            return day;
        }
    }
}
