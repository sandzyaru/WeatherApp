package com.example.weatherapp;

import android.app.Application;
import android.content.Context;


import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {
    private static App instance;
    public static Context getContext(){
        return instance.getApplicationContext();
    }
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
