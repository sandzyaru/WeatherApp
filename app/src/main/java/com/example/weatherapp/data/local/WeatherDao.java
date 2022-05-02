package com.example.weatherapp.data.local;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weatherapp.data.model.MainResponse;

@Dao
public interface WeatherDao {
    @Query("SELECT * FROM mainresponse")
    MainResponse getMainResponse();
    @Insert
    void insert(MainResponse mainResponse);


}
