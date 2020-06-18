package com.slava.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondPlayerWin extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.slava.tictactoe.R.layout.activity_secondplayerwin);
        Intent intent = getIntent();
        String string = intent.getStringExtra("name");
        textView = findViewById(R.id.textViewPlayer2);
        textView.setText(string +" Wins!!!");
    }
}



