package com.example.standard.tictactoe;

public class MoveGenerator {
    private static MoveGenerator instance;
    private int depth, bestMove;
    private Board board;

    private MoveGenerator() {
    }

    public static MoveGenerator getInstance() {
        if (MoveGenerator.instance == null) {
            MoveGenerator.instance = new MoveGenerator();
        }
        return MoveGenerator.instance;
    }

    public int calculateMove(Board board, int depth) {
        this.depth = depth;
        bestMove = -1;
        this.board = board;
        max(-1, depth);
        return bestMove;
    }

    private int max(int player, int depth) {
        if (board.gameOver() == player * -1) {
            return -1;
        } else if (board.gameOver() == player) {
            return 1;
        } else if (depth == 0) {
            return 0;
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            board.makeMove(i, player);
            int value = min(player * -1, depth - 1);
            board.undoMove();
            if (value > maxValue) {
                maxValue = value;
                if (depth == 0) {
                    bestMove = i;
                }
            }
        }
        return maxValue;
    }

    private int min(int player, int depth) {
        if (board.gameOver() == player * -1) {
            return 1;
        } else if (board.gameOver() == player) {
            return -1;
        } else if (depth == 0) {
            return 0;
        }
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            board.makeMove(i, player);
            int value = min(player * -1, depth - 1);
            board.undoMove();
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }
}
