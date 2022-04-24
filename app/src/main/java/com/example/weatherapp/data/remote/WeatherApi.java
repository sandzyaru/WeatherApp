package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.model.Main;
import com.example.weatherapp.data.model.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<Main> getWeather(@Query("q") String cityName, @Query("appid") String apiKey);

}
