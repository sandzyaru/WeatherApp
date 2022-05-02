package com.example.weatherapp.data.local;



import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapp.data.converters.WeatherTypeConverter;

import com.example.weatherapp.data.model.MainResponse;


@Database(entities = {MainResponse.class}, version = 1)
@TypeConverters({WeatherTypeConverter.class })
public abstract class AppDatabase extends RoomDatabase {
    public  abstract WeatherDao weatherDao();
}
