package com.example.standard.tictactoe;

public class Board {
    private static Board instance;
    private int[] board;
    private int nextPlayer, winPosition;

    private Board() {
        nextPlayer = 0;
        this.board = new int[9];
    }

    public boolean makeMove(int position) {
        if (nextPlayer == 1 && board[position] == 0) {
            board[position] = 1;
            nextPlayer = -1;
            return true;
        }
        return false;
    }

    public boolean makeMoveAndroid() {
        if (nextPlayer == -1) {
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = -1;
                    nextPlayer = 1;
                    return true;
                }
            }
        }
        return false;
    }

    public int gameOver() {
        if (hasWon(1)) {
            return 1;
        } else if (hasWon(-1)) {
            return -1;
        }
        return 0;
    }

    private boolean hasWon(int player) {
        for (int i = 0; i < 3; i++) {
            if (board[i] == player && board[i + 3] == player && board[i + 6] == player) {
                winPosition = i;
                return true;
            }
        }
        for (int i = 0; i < 3; i += 3) {
            if (board[i] == player && board[i + 1] == player && board[i + 2] == player) {
                winPosition = i + 3;
                return true;
            }
        }
        if (board[0] == player && board[4] == player && board[8] == player) {
            winPosition = 6;
            return true;
        }
        if (board[2] == player && board[4] == player && board[6] == player) {
            winPosition = 7;
            return true;
        }
        return false;
    }

    public static Board getInstance() {
        if (Board.instance == null) {
            Board.instance = new Board();
        }
        return Board.instance;
    }

    public int getStatus(int position) {
        return board[position];
    }

    public int getWinPosition() {
        return winPosition;
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
        this.board = new int[9];
    }
}
