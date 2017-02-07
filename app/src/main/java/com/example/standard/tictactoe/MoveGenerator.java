package com.example.standard.tictactoe;

public class MoveGenerator {
    private static MoveGenerator instance;

    private MoveGenerator() {
    }

    public static MoveGenerator getInstance() {
        if (MoveGenerator.instance == null) {
            MoveGenerator.instance = new MoveGenerator();
        }
        return MoveGenerator.instance;
    }
}
