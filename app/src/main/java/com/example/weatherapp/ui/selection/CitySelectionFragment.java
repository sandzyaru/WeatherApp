package com.example.weatherapp.ui.selection;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.databinding.FragmentCitySelectionBinding;




public class CitySelectionFragment extends BaseFragment<FragmentCitySelectionBinding> {

     private String cityName;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected FragmentCitySelectionBinding bind() {
        return FragmentCitySelectionBinding.inflate(getLayoutInflater());
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etCity.getText()!=null) {
                    cityName = binding.etCity.getText().toString();
                    navController.navigate(CitySelectionFragmentDirections.actionCitySelectionFragmentToWeatherFragment(cityName));
                }
            }
        });

    }





    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {


    }

    @Override
    protected void setupListener() {




    }


    @Override
    protected void setupObservers() {

    }

}