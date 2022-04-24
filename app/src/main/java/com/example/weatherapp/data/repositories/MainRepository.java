package com.example.weatherapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.Main;
import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.data.remote.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;
    public  MainRepository (WeatherApi api){
        this.api =api;
    }
    public MutableLiveData<Resource<Main>> getWeather(){
        MutableLiveData<Resource<Main>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather("Bishkek","1e8bec11ba5be4f42b7129692433b744").enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if(response.isSuccessful() && response.body()!=null){
                    liveData.setValue(Resource.success(response.body()));
                }else{
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
        return liveData;
    }
}
