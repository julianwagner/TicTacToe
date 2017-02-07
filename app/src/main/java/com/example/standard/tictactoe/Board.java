package com.example.standard.tictactoe;

public class Board {
    private static Board instance;
    private int[][] board;

    private Board() {
        board = new int[3][3];
    }

    public static Board getInstance() {
        if (Board.instance == null) {
            Board.instance = new Board();
        }
        return Board.instance;
    }
}
