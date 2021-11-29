package com.example.hw2.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw2.AdapterScore;
import com.example.hw2.Class.MyDB;
import com.example.hw2.Class.Score;
import com.example.hw2.Interface.CallBack_ScoreList;
import com.example.hw2.R;

import java.util.ArrayList;
import java.util.Collections;


public class Fragment_ScoreList extends Fragment {

    private RecyclerView fragScoreList_LST_scores;

    private AppCompatActivity activity;

    private CallBack_ScoreList callBack_scoreList;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBackList(CallBack_ScoreList callBackList) {
        this.callBack_scoreList = callBackList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_list, container, false);
        findViews(view);
        initViews();

        return view;
    }

    private void initViews() {
        MyDB myDB = new MyDB();
        myDB.getScoresFromDB();
        ArrayList<Score> scores = myDB.getTopN(10);


        AdapterScore adapter_score = new AdapterScore(activity, scores);

        // Vertically
        fragScoreList_LST_scores.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));

        fragScoreList_LST_scores.setHasFixedSize(true);
        fragScoreList_LST_scores.setItemAnimator(new DefaultItemAnimator());
        fragScoreList_LST_scores.setAdapter(adapter_score);

        adapter_score.setScoreItemClickListener(new AdapterScore.ScoreItemClickListener() {
            @Override
            public void scoreItemClicked(Score score, int position) {
                fragScoreList_LST_scores.getAdapter().notifyItemChanged(position);
                callBack_scoreList.showInMap(score);
            }
        });

    }

    private void findViews(View view) {
        fragScoreList_LST_scores = view.findViewById(R.id.fragScoreList_LST_scores);
    }


}