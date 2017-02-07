package com.example.standard.tictactoe;

class MoveGenerator {
    private static MoveGenerator instance;
    private int bestMove;
    private Board board;

    private MoveGenerator() {
    }

    static MoveGenerator getInstance() {
        if (MoveGenerator.instance == null) {
            MoveGenerator.instance = new MoveGenerator();
        }
        return MoveGenerator.instance;
    }

    int calculateMove() {
        bestMove = -1;
        board = Board.getInstance();
        max(-1, 4);
        return bestMove;
    }

    private int max(int player, int depth) {
        int gameOver = board.gameOver();
        if (gameOver == player * -1) {
            return -100;
        } else if (gameOver == player) {
            return 100;
        } else if (depth == 0) {
            return 0;
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board.getStatus(i) == 0) {
                board.makeMove(i);
                int value = min(player * -1, depth - 1);
                board.undoMove();
                if (value > maxValue) {
                    maxValue = value;
                    bestMove = i;
                }
            }
        }
        return maxValue;
    }

    private int min(int player, int depth) {
        int gameOver = board.gameOver();
        if (gameOver == player * -1) {
            return 100;
        } else if (gameOver == player) {
            return -100;
        } else if (depth == 0) {
            return 0;
        }
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board.getStatus(i) == 0) {
                board.makeMove(i);
                int value = max(player * -1, depth - 1);
                board.undoMove();
                if (value < minValue) {
                    minValue = value;
                }
            }
        }
        return minValue;
    }
}
