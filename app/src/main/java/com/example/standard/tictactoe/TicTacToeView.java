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
    private Paint linePaint, iconPaint, winPaint;
    private int cellSize;
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

        winPaint = new Paint();
        winPaint.setColor(Color.YELLOW);
        winPaint.setStrokeWidth(10);
        winPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int distance = canvas.getHeight() - canvas.getWidth();
        final int width = canvas.getWidth() / 3;
        final int height = (canvas.getHeight() - distance) / 3;
        cellSize = width;
        //draw board
        for (int i = 1; i <= 2; i++) {
            canvas.drawLine(0, height * i, getWidth(), height * i, linePaint);
            canvas.drawLine(width * i, 0, width * i, getHeight() - distance, linePaint);
        }
        //draw moves
        for (int i = 0; i < 9; i++) {
            int xPosition = cellSize / 2;
            int yPosition = cellSize / 2;
            if (i % 3 == 1) {
                xPosition += cellSize;
            } else if (i % 3 == 2) {
                xPosition += cellSize * 2;
            }
            if (i >= 6) {
                yPosition += cellSize * 2;
            } else if (i >= 3) {
                yPosition += cellSize;
            }
            if (board.getStatus(i) == 1) {
                int iconSize = playerIcon.getWidth() / 2;
                canvas.drawBitmap(playerIcon, xPosition - iconSize, yPosition - iconSize, iconPaint);
            } else if (board.getStatus(i) == -1) {
                int iconSize = androidIcon.getWidth() / 2;
                canvas.drawBitmap(androidIcon, xPosition - iconSize, yPosition - iconSize, iconPaint);
            }
        }
        //winning line
        if (board.gameOver() != 0) {
            if (board.getWinPosition() == 0) {
                canvas.drawLine(cellSize / 2, 0, cellSize / 2, getHeight() - distance, winPaint);
            } else if (board.getWinPosition() == 1) {
                canvas.drawLine(cellSize / 2 + cellSize, 0, cellSize / 2 + cellSize, getHeight() - distance, winPaint);
            } else if (board.getWinPosition() == 2) {
                canvas.drawLine(cellSize / 2 + cellSize * 2, 0, cellSize / 2 + cellSize * 2, getHeight() - distance, winPaint);
            } else if (board.getWinPosition() == 3) {
                canvas.drawLine(0, cellSize / 2, getWidth(), cellSize / 2, winPaint);
            } else if (board.getWinPosition() == 4) {
                canvas.drawLine(0, cellSize / 2 + cellSize, getWidth(), cellSize / 2 + cellSize, winPaint);
            } else if (board.getWinPosition() == 5) {
                canvas.drawLine(0, cellSize / 2 + cellSize * 2, getWidth(), cellSize / 2 + cellSize * 2, winPaint);
            } else if (board.getWinPosition() == 6) {
                canvas.drawLine(0, 0, getHeight() - distance, getHeight() - distance, winPaint);
            } else if (board.getWinPosition() == 7) {
                canvas.drawLine(0, getHeight() - distance, getWidth(), 0, winPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int position = (int) (event.getY());
            if (position <= cellSize) {
                position = (int) (event.getX() / cellSize);
            } else if (position <= cellSize * 2) {
                position = 3 + (int) (event.getX() / cellSize);
            } else if (position <= cellSize * 3) {
                position = 6 + (int) (event.getX() / cellSize);
            } else {
                return true;
            }
            if (board.gameOver() == 0 && board.makeMove(position)) {
                invalidate();
            }
            if (board.gameOver() == 0 && board.makeMoveAndroid()) {
                invalidate();
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
            board.makeMoveAndroid();
        }
        invalidate();
    }
}