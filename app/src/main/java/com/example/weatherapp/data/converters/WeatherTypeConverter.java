package com.example.weatherapp.data.converters;


import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.Clouds;
import com.example.weatherapp.data.model.Coord;
import com.example.weatherapp.data.model.Main;
import com.example.weatherapp.data.model.Rain;
import com.example.weatherapp.data.model.Sys;
import com.example.weatherapp.data.model.Weather;
import com.example.weatherapp.data.model.Wind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class WeatherTypeConverter {
    @TypeConverter
    public  String fromCoordToString (Coord coord){
        if (coord==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<Coord>(){}.getType();
        return gson.toJson(coord,type); }
    @TypeConverter
    public Coord fromStringToCoord(String coord){
        if (coord==null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>(){}.getType();
        return  gson.fromJson(coord,type);
    }
    @TypeConverter
    public  String fromWeatherToString (List<Weather> weather){
        if (weather==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<List<Weather>>(){}.getType();
        return gson.toJson(weather,type); }
    @TypeConverter
    public List<Weather> fromStringToWeather(String weather){
        if (weather==null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>(){}.getType();
        return  gson.fromJson(weather,type);
    }
    @TypeConverter
    public  String fromMainToString (Main main){
        if (main==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<Main>(){}.getType();
        return gson.toJson(main,type); }
    @TypeConverter
    public Main fromStringToMain(String main){
        if (main==null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>(){}.getType();
        return  gson.fromJson(main,type);
    }
    @TypeConverter
    public  String fromWindToString (Wind wind){
        if (wind==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<Wind>(){}.getType();
        return gson.toJson(wind,type); }
    @TypeConverter
    public Wind fromStringToWind(String wind){
        if (wind==null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>(){}.getType();
        return  gson.fromJson(wind,type);
    }
    @TypeConverter
    public  String fromRainToString (Rain rain){
        if (rain==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<Rain>(){}.getType();
        return gson.toJson(rain,type); }
    @TypeConverter
    public Rain fromStringToRain(String rain){
        if (rain==null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Rain>(){}.getType();
        return  gson.fromJson(rain,type);
    }
    @TypeConverter
    public  String fromCloudsToString (Clouds clouds){
        if (clouds==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<Clouds>(){}.getType();
        return gson.toJson(clouds,type); }
    @TypeConverter
    public Clouds fromStringToClouds(String clouds){
        if (clouds==null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>(){}.getType();
        return  gson.fromJson(clouds,type);
    }
    @TypeConverter
    public  String fromSysToString (Sys sys){
        if (sys==null){
            return null;
        }
        Gson gson= new Gson();
        Type type= new TypeToken<Sys>(){}.getType();
        return gson.toJson(sys,type); }
    @TypeConverter
    public Sys fromStringToSys(String sys){
        if (sys==null){
            return null;
        }
            Gson gson = new Gson();
            Type type = new TypeToken<Sys>(){}.getType();
            return  gson.fromJson(sys,type);


    }
}
