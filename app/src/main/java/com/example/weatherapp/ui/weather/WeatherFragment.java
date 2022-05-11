package com.example.weatherapp.ui.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;



import androidx.annotation.Nullable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;




import android.view.View;


import com.example.weatherapp.App;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.common.Resource;

import com.example.weatherapp.data.model.MainResponse;


import com.example.weatherapp.data.model.Weather;
import com.example.weatherapp.databinding.FragmentWeatherBinding;
import com.example.weatherapp.di.AppModule;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding>  {

    private WeatherViewModel viewModel;
    private WeatherFragmentArgs args;
    private ArrayList<Weather> weatherArrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= new ViewModelProvider(requireActivity())
                .get(WeatherViewModel.class);
        args = WeatherFragmentArgs.fromBundle(getArguments());




    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }




    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {
            viewModel.getWeatherByCoord(args.getLat(),args.getLon());
    }

    @Override
    protected void setupListener() {
        binding.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(WeatherFragmentDirections.actionWeatherFragmentToCitySelectionFragment());


            }
        });

    }


    @Override
    protected void setupObservers() {

        viewModel.liveData.observe(getViewLifecycleOwner(), (Observer<? super Resource<MainResponse>>) new Observer<Resource<MainResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<MainResponse> resource) {
                switch (resource.status){
                    case LOADING:{
                        break;
                    }

                    case SUCCESS:{
                        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEEE, d MMM yyyy | HH:mm");
                        String date = df.format(Calendar.getInstance().getTime());
                        binding.date.setText(date);
                        Date sr = new Date(resource.data.getSys().getSunrise()*1000L);
                        Date ss = new Date(resource.data.getSys().getSunset()*1000L);
                        Date dt = new Date(resource.data.getDt()*1000L);
                        @SuppressLint("SimpleDateFormat") DateFormat tm = new SimpleDateFormat("h:mm a");
                        @SuppressLint("SimpleDateFormat") DateFormat hm = new SimpleDateFormat("hh'h' mm'm'");
                        tm.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                        hm.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                        String tmSunrise = tm.format(sr);
                        String tmSunSet =tm.format(ss);
                        String dayTime =hm.format(dt);


                        weatherArrayList =(ArrayList<Weather>) resource.data.getWeather();
                        binding.city.setText(resource.data.getSys().getCountry()+","+resource.data.getName());
                        binding.sunny.setText(weatherArrayList.get(0).getMain());
                        binding.tvTemp.setText(resource.data.getMain().getTemp().toString());
                        binding.tvTempMax.setText(resource.data.getMain().getTempMax().toString()+"°C");
                        binding.tvTempMin.setText(resource.data.getMain().getTempMin().toString()+"°C");
                        binding.tvHumidity.setText(resource.data.getMain().getHumidity().toString()+"%");
                        binding.tvPressure.setText(resource.data.getMain().getPressure().toString()+"hPa");
                        binding.tvWind.setText(resource.data.getWind().getSpeed().toString()+"meter/sec");
                        binding.tvSunrise.setText(tmSunrise);
                        binding.tvSunset.setText(tmSunSet);
                        binding.tvDaytime.setText(dayTime);


                        binding.tvTemp.setVisibility(View.VISIBLE);
                        binding.tvTempMax.setVisibility(View.VISIBLE);
                        binding.tvTempMin.setVisibility(View.VISIBLE);
                        binding.tvHumidity.setVisibility(View.VISIBLE);
                        binding.tvPressure.setVisibility(View.VISIBLE);
                        binding.tvWind.setVisibility(View.VISIBLE);
                        binding.tvSunrise.setVisibility(View.VISIBLE);
                        binding.tvSunset.setVisibility(View.VISIBLE);
                        binding.tvDaytime.setVisibility(View.VISIBLE);
                        binding.sunny.setVisibility(View.VISIBLE);
                        binding.city.setVisibility(View.VISIBLE);

                        break;
                    }
                    case ERROR:{
                    }
                }

            }
        });
    }




}