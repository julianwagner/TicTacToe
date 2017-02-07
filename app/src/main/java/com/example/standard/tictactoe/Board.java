package com.example.standard.tictactoe;

public class Board {
    private static Board instance;
    private int[][] board;
    private int nextPlayer;

    private Board() {
        board = new int[3][3];
        nextPlayer = 0;
    }

    public boolean makeMove(int x, int y) {
        if (nextPlayer == 1 && board[x][y] == 0) {
            board[x][y] = 1;
            nextPlayer = -1;
            return true;
        }
        return false;
    }

    public boolean makeMoveAndroid() {
        if (nextPlayer == -1) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = -1;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Board getInstance() {
        if (Board.instance == null) {
            Board.instance = new Board();
        }
        return Board.instance;
    }

    public int getStatus(int x, int y) {
        return board[x][y];
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
}
