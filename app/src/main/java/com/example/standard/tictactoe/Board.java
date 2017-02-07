package com.example.standard.tictactoe;

public class Board {
    private static Board instance;
    private int[][] board;
    private int nextPlayer;

    private Board() {
        board = new int[3][3];
        nextPlayer = 0;
    }

    public static Board getInstance() {
        if (Board.instance == null) {
            Board.instance = new Board();
        }
        return Board.instance;
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
}
