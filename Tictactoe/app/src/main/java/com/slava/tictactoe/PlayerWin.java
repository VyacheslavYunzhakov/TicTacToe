package com.slava.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PlayerWin extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button buttonMainMenu;
    Button buttonRematch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        //Give users two seconds to understand that one player won
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        String stringWinner = intent.getStringExtra("winner");

        buttonMainMenu = findViewById(R.id.buttonMainMenu);
        buttonRematch = findViewById(R.id.buttonRematch);
        textView =  findViewById(R.id.textViewPlayer1);
        textView.setText(stringWinner +" Wins!!!");

        buttonMainMenu.setOnClickListener(this);
        buttonRematch.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();;
        String game = intent.getStringExtra("game");
        String name1 = intent.getStringExtra("name1");
        String name2 = intent.getStringExtra("name2");
        switch (v.getId()) {
            case (R.id.buttonRematch):
                    intent = new Intent(this, Game.class);
                    intent.putExtra("name1", name1);
                    intent.putExtra("name2", name2);
                    intent.putExtra("game", game);

                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
                break;
            case (R.id.buttonMainMenu):
                intent = new Intent(this, MainMenu.class);
                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
                break;
        }
    }
}
