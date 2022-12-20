package com.example.hw2.Class;

import com.google.android.gms.maps.model.Marker;

public class Score implements Comparable<Score>{
    private int score;
    private double latitude;
    private double longitude;
    private boolean selected;
    private Marker marker;

    public Score(){
        this.score = 0;
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.marker = null;
    }

    public Score(int score, double latitude, double longitude){
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getScore() {
        return score;
    }

    public Score setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Score setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Score setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void addScore(int value){
        this.score += value;
    }

    public boolean isSelected() {
        return selected;
    }

    public Score setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public Marker getMarker() {
        return marker;
    }

    public Score setMarker(Marker marker) {
        this.marker = marker;
        return this;
    }

    @Override
    public int compareTo(Score score) {
        if( this.score > score.getScore())
            return -1;

        if(this.score < score.getScore())
            return 1;

        return 0;
    }
}
