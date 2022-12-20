package com.example.hw2.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw2.Class.Score;
import com.example.hw2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private AppCompatActivity activity;
    private MapView frag_map_scores;
    private final String MAPVIEW_BUNDLE_KEY = "AIzaSyCce_VtQX2XnQuinrMtuugEwq8VGTwwITM";
    private GoogleMap map;

    public Fragment_Map() {
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);        
        findViews(view);
        initGoogleMap(savedInstanceState);

        return view;
    }

    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        frag_map_scores.onCreate(mapViewBundle);
        frag_map_scores.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        frag_map_scores.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        frag_map_scores.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        frag_map_scores.onStop();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.map = map;
        this.map.setMyLocationEnabled(true);

    }

    @Override
    public void onPause() {
        frag_map_scores.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        frag_map_scores.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        frag_map_scores.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }


    }

    private void findViews(View view) {
        frag_map_scores = view.findViewById(R.id.frag_map_scores);
    }


    public Marker setMarker(Score score){
        LatLng sydney = new LatLng(score.getLatitude(), score.getLongitude());
        return this.map.addMarker(new MarkerOptions()
                .position(sydney)
                .title(score.getScore() + ""));
    }

    public void removeMarker(Score score){
        Marker marker = score.getMarker();
        if(marker != null)
            marker.remove();
    }


}