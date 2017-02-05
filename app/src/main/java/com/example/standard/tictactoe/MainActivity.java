package com.example.standard.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TicTacToeView ticTacToeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add TicTacToe field to UI
        ticTacToeView = new TicTacToeView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout0);
        layout.addView(ticTacToeView);

        ((Button) findViewById(R.id.buttonQuit)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonAndroid)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonPlayer)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
