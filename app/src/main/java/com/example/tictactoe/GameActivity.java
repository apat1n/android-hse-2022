package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (AppContext.board == null) {
            AppContext.init();
        }
        AppContext.gameFieldSize = getResources().getDimension(R.dimen.game_field_size);

        TextView textView = findViewById(R.id.curPlayerTextView);
        {
            final String turnMessage = getResources().getString(R.string.turnPrefix) + " " + AppContext.curPlayer.getPlayerString();
            textView.setText(turnMessage);
        }

        GridView gridView = findViewById(R.id.gridView);
        gridView.setBackgroundResource(R.drawable.field);
        gridView.setAdapter(new GridViewAdapter(this));
        gridView.setOnItemClickListener(((adapterView, view, i, l) -> {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() != null) {
                return;
            }
            imageView.setImageResource(AppContext.curPlayer.getDrawableId());
            AppContext.board[i / 3][i % 3] = AppContext.curPlayer;

            Player winner = AppContext.getWinner();
            if (winner != null) {
                if (winner == Player.Tie) {
                    final String winnerText = getResources().getString(R.string.tie);
                    Toast.makeText(getApplicationContext(), winnerText, Toast.LENGTH_SHORT).show();
                } else {
                    final String winnerText = getResources().getString(R.string.winnerPrefix) + " " + winner.getPlayerString() + "!";
                    Toast.makeText(getApplicationContext(), winnerText, Toast.LENGTH_SHORT).show();
                    AppContext.addWinner(winner);
                }

                AppContext.init();
                finish();
            } else {
                AppContext.changePlayer();
                final String turnMessage = getResources().getString(R.string.turnPrefix) + " " + AppContext.curPlayer.getPlayerString();
                textView.setText(turnMessage);
            }
        }));

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(view -> {
            AppContext.init();
            gridView.setAdapter(new GridViewAdapter(this));

            final String turnMessage = getResources().getString(R.string.turnPrefix) + " " + AppContext.curPlayer.getPlayerString();
            textView.setText(turnMessage);
        });
    }
}