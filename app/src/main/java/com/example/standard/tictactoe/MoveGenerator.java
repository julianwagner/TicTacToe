package com.example.standard.tictactoe;

public class MoveGenerator {
    private static MoveGenerator instance;
    private int depth, bestMove;
    private int[] board;

    private MoveGenerator() {
    }

    public static MoveGenerator getInstance() {
        if (MoveGenerator.instance == null) {
            MoveGenerator.instance = new MoveGenerator();
        }
        return MoveGenerator.instance;
    }

    public int calculateMove(int[] board, int depth) {
        this.depth = depth;
        bestMove = -1;
        this.board = board;
        max(-1, depth);
        return bestMove;
    }
}
