package com.example.hw2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hw2.Class.Score;
import com.example.hw2.Fragment.Fragment_Map;
import com.example.hw2.Fragment.Fragment_ScoreList;
import com.example.hw2.Interface.CallBack_ScoreList;
import com.example.hw2.R;


public class ScoreActivity extends AppCompatActivity {
    private Fragment_ScoreList fragment_scoreList;
    private Fragment_Map fragment_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        fragment_scoreList = new Fragment_ScoreList();
        fragment_scoreList.setActivity(this);
        fragment_scoreList.setCallBackList(callBack_scoreList);
        getSupportFragmentManager().beginTransaction().add(R.id.score_frame_ScoreList, fragment_scoreList).commit();

        fragment_map = new Fragment_Map();
        fragment_map.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.score_frame_Map, fragment_map).commit();


    }

    CallBack_ScoreList callBack_scoreList = new CallBack_ScoreList() {
        @Override
        public void showInMap(Score score) {
            if(score.isSelected()){
                score.setMarker(fragment_map.setMarker(score));
            }
            else
                fragment_map.removeMarker(score);
        }
    };

}