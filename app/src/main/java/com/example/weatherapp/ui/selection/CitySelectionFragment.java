package com.example.weatherapp.ui.selection;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.databinding.FragmentCitySelectionBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class CitySelectionFragment extends BaseFragment<FragmentCitySelectionBinding> implements OnMapReadyCallback, LocationListener {

    //private String cityName;
    private GoogleMap gMap;
    private LocationManager locationManager;
    private final String[] PERMS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected FragmentCitySelectionBinding bind() {
        return FragmentCitySelectionBinding.inflate(getLayoutInflater());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSelect.setOnClickListener(view1 -> {
            if (binding.etCity.getText() != null) {
/*                cityName = binding.etCity.getText().toString();
                navController.navigate(CitySelectionFragmentDirections.actionCitySelectionFragmentToWeatherFragment(cityName));*/
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    10000, 0, this);
        }


    }

    @Override
    protected void setupViews() {
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void callRequests() {
        ActivityCompat.requestPermissions(requireActivity(),PERMS,1);
    }

    @Override
    protected void setupListener() { }


    @Override
    protected void setupObservers() {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;
        getCurrentLocation();
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                gMap.clear();
                MarkerOptions options= new MarkerOptions();
                options.draggable(true);
                options.title(latLng.toString());
                options.position(latLng);
                gMap.addMarker(options);
            }
        });
        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });
        gMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                marker.setTitle(marker.getPosition().toString());
                marker.showInfoWindow();
            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {
                marker.hideInfoWindow();
            }
        });
        gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                String lat = String.valueOf(marker.getPosition().latitude);
                String lon = String.valueOf(marker.getPosition().longitude);
                marker.getPosition();
                navController.navigate(CitySelectionFragmentDirections.actionCitySelectionFragmentToWeatherFragment(lat,lon));
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        LatLng latLng= new LatLng(location.getLatitude(),location.getLongitude());
        if(gMap!=null){
            gMap.addMarker(new MarkerOptions().position(latLng));
        }
    }
}