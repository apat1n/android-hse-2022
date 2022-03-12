package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView scoreXTextView = findViewById(R.id.scoreXTextView);
        String scoreXText = getResources().getString(R.string.scoreXPrefix) + " " + AppContext.getXWins();
        scoreXTextView.setText(scoreXText);

        TextView scoreOTextView = findViewById(R.id.scoreOTextView);
        String scoreOText = getResources().getString(R.string.scoreOPrefix) + " " + AppContext.getOWins();
        scoreOTextView.setText(scoreOText);
    }
}