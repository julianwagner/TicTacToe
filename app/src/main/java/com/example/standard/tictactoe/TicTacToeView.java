package com.example.standard.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TicTacToeView extends View {
    private Board board;
    private Paint linePaint, iconPaint;
    private int cellSize, distance;
    private Bitmap androidIcon, playerIcon;

    public TicTacToeView(Context context) {
        super(context);

        setBackgroundResource(R.drawable.background);

        androidIcon = BitmapFactory.decodeResource(getResources(), R.drawable.android_icon);
        playerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.player_icon);

        board = Board.getInstance();

        iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        distance = canvas.getHeight() - canvas.getWidth();
        final int width = canvas.getWidth() / 3;
        final int height = (canvas.getHeight() - distance) / 3;
        cellSize = width;

        for (int i = 1; i <= 2; i++) {
            canvas.drawLine(0, height * i, getWidth(), height * i, linePaint);
            canvas.drawLine(width * i, 0, width * i, getHeight() - distance, linePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int xCell = (int) (event.getX() / cellSize);
            int yCell = (int) (event.getY());
            if (yCell <= cellSize) {
                yCell = 0;
            } else if (yCell <= cellSize * 2) {
                yCell = 1;
            } else if (yCell <= cellSize * 3) {
                yCell = 2;
            } else {
                return true;
            }
            if (board.makeMove(xCell, yCell)) {
            }
            if (board.makeMoveAndroid()) {
            }

            return true;
        }
        return super.onTouchEvent(event);
    }

    public void startWithPlayer(boolean playerStarts) {
        if (playerStarts) {
            board.setNextPlayer(1);
        } else {
            board.setNextPlayer(-1);
        }
    }
}