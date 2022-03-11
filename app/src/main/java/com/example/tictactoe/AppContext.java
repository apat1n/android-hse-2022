package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class AppContext {
    public static float gameFieldSize;
    public static String userName;
    public static Player curPlayer;
    public static Player[][] board;
    public static List<Player> winsList = new ArrayList<>();

    public static void changePlayer() {
        curPlayer = (curPlayer == Player.Os) ? Player.Xs : Player.Os;
    }

    public static void init() {
        curPlayer = Player.Os;
        board = new Player[3][3];
    }

    public static long getXWins() {
        return winsList.stream().filter(player -> player == Player.Xs).count();
    }

    public static long getOWins() {
        return winsList.stream().filter(player -> player == Player.Os).count();
    }

    public static Player getWinner() {
        // horizontal check
        for (int i = 0; i < 3; i++) {
            boolean isWinner = true;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != board[i][0]) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner && board[i][0] != null) {
                return board[i][0];
            }
        }

        // vertical check
        for (int j = 0; j < 3; j++) {
            boolean isWinner = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][j] != board[0][j]) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner && board[0][j] != null) {
                return board[0][j];
            }
        }

        // main diagonal check
        {
            boolean isWinner = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] != board[0][0]) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner && board[0][0] != null) {
                return board[0][0];
            }
        }

        // anti diagonal check
        {
            boolean isWinner = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] != board[0][2]) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner && board[0][2] != null) {
                return board[0][2];
            }
        }

        // tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return null;
                }
            }
        }
        return Player.Tie;
    }

    public static void addWinner(Player player) {
        winsList.add(player);
    }
}
