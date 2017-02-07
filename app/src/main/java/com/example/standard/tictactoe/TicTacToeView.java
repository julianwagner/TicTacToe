package com.example.standard.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int distance = canvas.getHeight() - canvas.getWidth();
        final int width = canvas.getWidth() / 3;
        final int height = (canvas.getHeight() - distance) / 3;

        for (int i = 1; i <= 2; i++) {
            canvas.drawLine(0, height * i, getWidth(), height * i, linePaint);
            canvas.drawLine(width * i, 0, width * i, getHeight() - distance, linePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}