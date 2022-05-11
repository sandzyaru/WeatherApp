package com.example.weatherapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.local.WeatherDao;
import com.example.weatherapp.data.model.MainResponse;

import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.domain.repository.MainRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositoryImpl implements MainRepository {
    private final WeatherApi api;
    public final String apiKey = "1e8bec11ba5be4f42b7129692433b744";
    private final WeatherDao dao;





    @Inject
    public MainRepositoryImpl(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao=dao;
    }

    public MutableLiveData<Resource<MainResponse>> getWeather(String cityName) {
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather(cityName, apiKey).enqueue(new Callback<MainResponse>() {

            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());


                } else {
                    liveData.setValue(Resource.error(response.message(), null));

                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                if ( dao.getMainResponse()!=null) {
                    liveData.setValue(Resource.success(dao.getMainResponse()));

                } else {
                    liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                }



            }
        });
        return liveData;
    }


    public MutableLiveData<Resource<MainResponse>> getWeatherByCoord(String lat, String lon){
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeatherByCoord(lat,lon,apiKey).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());


                } else {
                    liveData.setValue(Resource.error(response.message(), null));

                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                if (dao.getMainResponse()!=null) {
                    liveData.setValue(Resource.success(dao.getMainResponse()));

                } else {
                    liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                }
            }
        });
        return liveData;
    }





}
