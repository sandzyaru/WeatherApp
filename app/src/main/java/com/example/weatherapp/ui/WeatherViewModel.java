package com.example.weatherapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.App;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.MainResponse;

public class WeatherViewModel extends ViewModel {
    public MutableLiveData<Resource<MainResponse>> liveData;
    public void getWeather(){
        liveData = App.repository.getWeather();
    }
    public WeatherViewModel() {
    }

}
