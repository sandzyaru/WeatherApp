package com.example.weatherapp.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.MainResponse;


public interface MainRepository {
    MutableLiveData<Resource<MainResponse>> getWeather(String cityName);
    MutableLiveData<Resource<MainResponse>> getWeatherByCoord(String lat, String lon);
}
