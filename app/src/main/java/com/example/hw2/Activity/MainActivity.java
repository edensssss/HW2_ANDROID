package com.example.hw2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.hw2.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivity extends AppCompatActivity {
    public static final String SENSOR_MODE = "sensor_mode";
    public static final String BUTTONS_MODE = "buttons_mode";
    public static final String SPEED_FAST = "fast";
    public static final String SPEED_SLOW = "slow";


    private MaterialButton btn_start_game;
    private MaterialButton btn_scores;
    private RadioGroup rBtn_group_modesOptions, rBtn_group_speedOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        btn_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("mode", getSelectedMode());
                intent.putExtra("speed", getSelectedSpeed());

                startActivity(intent);
            }
        });

        btn_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getSelectedMode() {
        String mode = "";
        switch (rBtn_group_modesOptions.getCheckedRadioButtonId()){
            case R.id.rBtn_buttons: mode = BUTTONS_MODE; break;
            case R.id.rBtn_sensor: mode = SENSOR_MODE; break;
        }
        return mode;
    }
    private String getSelectedSpeed() {
        String mode = "";
        switch (rBtn_group_speedOptions.getCheckedRadioButtonId()){
            case R.id.rBtnSpeedFast: mode = SPEED_FAST; break;
            case R.id.rBtnSpeedSlow: mode = SPEED_SLOW; break;
        }
        return mode;
    }
    private void findViews() {
        btn_scores = findViewById(R.id.btn_scores);
        btn_start_game = findViewById(R.id.btn_startGame);
        rBtn_group_modesOptions = findViewById(R.id.rBtnModeOptions);
        rBtn_group_speedOptions = findViewById(R.id.rBtnSpeedOptions);
    }
}