package com.example.weatherapp;

import android.app.Application;

import com.example.weatherapp.data.remote.RetrofitClient;
import com.example.weatherapp.data.repositories.MainRepository;

public class App extends Application {
     private RetrofitClient retrofitClient;
     public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient= new RetrofitClient();
        repository= new MainRepository(retrofitClient.provideApi());
    }
}
