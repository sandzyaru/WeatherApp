package com.example.weatherapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.Main;
import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.data.model.Weather;
import com.example.weatherapp.data.repositories.MainRepository;
import com.example.weatherapp.databinding.FragmentWeatherBinding;


public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherViewModel viewModel;
    Main weather = new Main();

    public void setWeather(Main weather) {
        this.weather = weather;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= new ViewModelProvider(requireActivity())
                .get(WeatherViewModel.class);
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
        viewModel.getWeather();
    }

    @Override
    protected void setupListener() {

    }

    @Override
    protected void setupObservers() {

        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Main>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<Main> resource) {
                switch (resource.status){
                    case LOADING:{
                        break;
                    }
                    case SUCCESS:{
                        setWeather(resource.data);
                        binding.tvTMin.setText(weather.getTempMin().toString());
                        binding.tvTMin.setVisibility(View.VISIBLE);
                        break;
                    }
                    case ERROR:{
                        break;
                    }
                }

            }
        });
    }
}