package com.example.standard.tictactoe;

import java.util.Stack;

class Board {
    private static Board instance;
    private int[] board;
    private int nextPlayer, winPosition;
    private Stack<Integer> history;

    private Board() {
        nextPlayer = 0;
        this.board = new int[9];
        history = new Stack<>();
    }

    boolean makeMove(int position) {
        if (position == -1) {
            return false;
        }
        if (nextPlayer == 1 && board[position] == 0) {
            board[position] = 1;
            nextPlayer = -1;
            history.push(position);
            return true;
        } else if (nextPlayer == -1) {
            board[position] = -1;
            nextPlayer = 1;
            history.push(position);
            return true;
        }
        return false;
    }

    void undoMove() {
        if (!history.isEmpty()) {
            board[history.pop()] = 0;
            nextPlayer *= -1;
        }
    }

    int gameOver() {
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

    static Board getInstance() {
        if (Board.instance == null) {
            Board.instance = new Board();
        }
        return Board.instance;
    }

    int getStatus(int position) {
        return board[position];
    }

    int getWinPosition() {
        return winPosition;
    }

    void setNextPlayer(boolean playerStarts) {
        if (playerStarts) {
            nextPlayer = 1;
        } else {
            nextPlayer = -1;
        }
        this.board = new int[9];
    }
}
