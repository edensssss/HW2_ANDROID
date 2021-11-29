package com.example.hw2.Class;


import com.example.hw2.Activity.GameActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyDB {
    private final String SCORES_KEY = "scores";

    private ArrayList<Score> scores = new ArrayList<>();

    public MyDB() { }

    public ArrayList<Score> getRecords() {
        return scores;
    }

    public MyDB setScores(ArrayList<Score> records) {
        this.scores = records;
        return this;
    }

    public ArrayList<Score> getScoresFromDB(){
        String scores_str = MSPV.getMe().getString(SCORES_KEY, "");

        if(scores_str != ""){
            Gson gson = new Gson();
            this.scores = gson.fromJson(scores_str, MyDB.class).getRecords();
        }else {

            this.scores = new ArrayList<Score>();
        }

        return this.scores;
    }

    public void saveScoresToDB(){
        Gson gson = new Gson();
        String json_str = gson.toJson(this);
        MSPV.getMe().putString(SCORES_KEY, json_str);
    }

    public ArrayList<Score> getTopN(int n) {
        Collections.sort(this.scores);
        ArrayList<Score> topN_scores = new ArrayList<>();
        for(int i =0 ; i<n  && i < this.scores.size(); i++){
            topN_scores.add(this.scores.get(i));
        }

        return topN_scores;
    }
}
