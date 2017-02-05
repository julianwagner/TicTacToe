package com.example.standard.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class TicTacToeView extends View {
    private Paint linePaint;

    public TicTacToeView(Context context) {
        super(context);

        setBackgroundResource(R.drawable.background);

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);
    }
}