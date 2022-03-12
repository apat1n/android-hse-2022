package com.example.tictactoe;

import androidx.annotation.DrawableRes;

public enum Player {
    Xs("X", R.drawable.xs),
    Os("O", R.drawable.os),
    Tie("Tie", 0);

    private final String playerString;
    private final int drawableId;

    Player(String playerString, @DrawableRes int drawableId) {
        this.playerString = playerString;
        this.drawableId = drawableId;
    }

    public String getPlayerString() {
        return playerString;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
