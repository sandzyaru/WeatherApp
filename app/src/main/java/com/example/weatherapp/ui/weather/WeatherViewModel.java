package com.example.weatherapp.ui.weather;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.domain.repository.MainRepository;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    public MutableLiveData<Resource<MainResponse>> liveData;
    private MainRepository repository;
    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository=repository;
    }
    public void getWeather(String cityName){
        liveData = repository.getWeather(cityName);
    }

}
