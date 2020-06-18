package com.slava.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    Button buttonPlayer1;
    Button buttonPlayer2;
    Button buttonStart;
    Button buttonComputer;
    final int REQUEST_CODE_P1 = 1;
    final int REQUEST_CODE_P2 = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        buttonPlayer1 = findViewById(R.id.buttonPlayer1);
        buttonPlayer2 = findViewById(R.id.buttonPlayer2);
        buttonStart = findViewById(R.id.buttonStart);
        buttonComputer = findViewById(R.id.buttonComputer) ;

        buttonComputer.setOnClickListener(this);
        buttonPlayer1.setOnClickListener(this);
        buttonPlayer2.setOnClickListener(this);
        buttonStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonPlayer1:
                intent = new Intent(this, P1Name.class);
                startActivityForResult(intent, REQUEST_CODE_P1);
                break;
            case R.id.buttonPlayer2:
                intent = new Intent(this, P2Name.class);
                startActivityForResult(intent, REQUEST_CODE_P2);
                break;
            case R.id.buttonStart:
                intent = new Intent(this, Game.class);
                intent.putExtra("name1", buttonPlayer1.getText().toString());
                intent.putExtra("name2", buttonPlayer2.getText().toString());
                startActivity(intent);
                break;
            case R.id.buttonComputer:
                intent = new Intent(this, GameVsComputer.class);
                intent.putExtra("name1", buttonPlayer1.getText().toString());
                intent.putExtra("name2", buttonPlayer2.getText().toString());
                startActivity(intent);
            break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_P1:
                    String string1 = data.getStringExtra("name1");
                    buttonPlayer1.setText(string1);

                    break;
                case REQUEST_CODE_P2:
                    String string2 = data.getStringExtra("name2");
                    buttonPlayer2.setText(string2);
                    break;
            }
        }
    }
}
