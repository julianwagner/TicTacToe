package com.example.standard.tictactoe;

public class Board {
    private static Board instance;

    private Board() {
    }

    public static Board getInstance() {
        if (Board.instance == null) {
            Board.instance = new Board();
        }
        return Board.instance;
    }
}
