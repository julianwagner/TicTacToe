package com.example.standard.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TicTacToeView extends View {
    private Board board;
    private Paint linePaint, iconPaint, winPaint;
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

        winPaint = new Paint();
        winPaint.setColor(Color.YELLOW);
        winPaint.setStrokeWidth(10);
        winPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        distance = canvas.getHeight() - canvas.getWidth();
        final int width = canvas.getWidth() / 3;
        final int height = (canvas.getHeight() - distance) / 3;
        cellSize = width;
        //draw board
        for (int i = 1; i <= 2; i++) {
            canvas.drawLine(0, height * i, getWidth(), height * i, linePaint);
            canvas.drawLine(width * i, 0, width * i, getHeight() - distance, linePaint);
        }
        //draw moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int xPosition = (cellSize / 2) + (cellSize * i);
                int yPosition = (cellSize / 2) + (cellSize * j);
                if (board.getStatus(i, j) == 1) {
                    int iconSize = playerIcon.getWidth() / 2;
                    canvas.drawBitmap(playerIcon, xPosition - iconSize, yPosition - iconSize, iconPaint);
                } else if (board.getStatus(i, j) == -1) {
                    int iconSize = androidIcon.getWidth() / 2;
                    canvas.drawBitmap(androidIcon, xPosition - iconSize, yPosition - iconSize, iconPaint);
                }
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
            if (board.gameOver() == 0 && board.makeMove(xCell, yCell)) {
                invalidate();
            }
            if (board.gameOver() == 0 && board.makeMoveAndroid()) {
                invalidate();
            }
            int gameOver = board.gameOver();
            if (gameOver == 1) {
                Toast.makeText(getContext(), "Player wins!", Toast.LENGTH_SHORT).show();
            } else if (gameOver == -1) {
                Toast.makeText(getContext(), "Android wins!", Toast.LENGTH_SHORT).show();
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