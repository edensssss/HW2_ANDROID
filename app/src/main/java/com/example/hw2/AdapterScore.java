package com.example.hw2;


import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hw2.Class.Score;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class AdapterScore extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String[] RANKS = {
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Eo_circle_red_number-1.svg/1200px-Eo_circle_red_number-1.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Eo_circle_blue_number-2.svg/1200px-Eo_circle_blue_number-2.svg.png",
            "https://cdn.picpng.com/three/three-number-3-symbol-count-106982.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Eo_circle_red_number-4.svg/768px-Eo_circle_red_number-4.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Eo_circle_blue_number-5.svg/1024px-Eo_circle_blue_number-5.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Eo_circle_blue_number-6.svg/480px-Eo_circle_blue_number-6.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Eo_circle_red_number-7.svg/2048px-Eo_circle_red_number-7.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Eo_circle_blue_number-8.svg/2048px-Eo_circle_blue_number-8.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Eo_circle_brown_number-9.svg/1200px-Eo_circle_brown_number-9.svg.png",
            "https://listimg.pinclipart.com/picdir/s/15-154556_grand-number-10-clipart-number-10-transparent-background.png"
    };

    private Activity activity;
    private ArrayList<Score> scores = new ArrayList<>();
    private ScoreItemClickListener scoreItemClickListener;

    public AdapterScore(Activity activity, ArrayList<Score> movies) {
        this.activity = activity;
        this.scores = movies;
    }

    public AdapterScore setScoreItemClickListener(ScoreItemClickListener scoreItemClickListener) {
        this.scoreItemClickListener = scoreItemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scroe_list, viewGroup, false);
        return new ScoreViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ScoreViewHolder scoreViewHolder = (ScoreViewHolder) holder;
        Score score = getItem(position);

        Glide
                .with(activity)
                .load(RANKS[position])
                .into(scoreViewHolder.score_IMG_order);

        scoreViewHolder.score_TXT_score.setText(score.getScore()+"");
        if(score.isSelected()){
            scoreViewHolder.score_IMG_mark.setImageResource(R.drawable.mark_fill);
        }else{
            scoreViewHolder.score_IMG_mark.setImageResource(R.drawable.mark_empty);
        }

    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    private Score getItem(int position) {
        return scores.get(position);
    }

    public interface ScoreItemClickListener {
        void scoreItemClicked(Score score, int position);
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        public AppCompatImageView score_IMG_order;
        public MaterialTextView score_TXT_score;
        public AppCompatImageView score_IMG_mark;

        public ScoreViewHolder(final View itemView) {
            super(itemView);
            this.score_IMG_order = itemView.findViewById(R.id.score_IMG_order);
            this.score_TXT_score = itemView.findViewById(R.id.score_TXT_score);
            this.score_IMG_mark = itemView.findViewById(R.id.score_IMG_mark);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Score score = getItem(getAdapterPosition());
                    score.setSelected(!score.isSelected());
                    scoreItemClickListener.scoreItemClicked(score, getAdapterPosition());
                }
            });
        }
    }
}

