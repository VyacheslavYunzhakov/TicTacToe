package com.slava.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class FirstPlayerWin extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstplayerwin);
        Intent intent = getIntent();
        String string = intent.getStringExtra("name");
        textView =  findViewById(R.id.textViewPlayer1);
        textView.setText(string +" Wins!!!");



    }
}
