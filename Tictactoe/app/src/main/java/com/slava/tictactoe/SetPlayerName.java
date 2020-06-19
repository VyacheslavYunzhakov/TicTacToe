package com.slava.tictactoe;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class SetPlayerName extends AppCompatActivity implements View.OnClickListener {
    EditText editTextName;
    Button buttonOK;
    Intent previousIntent = new Intent();
    String player = previousIntent.getStringExtra("player");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pname);


        editTextName = findViewById(R.id.editTextName);
        buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (player == "player1");
        {
            intent.putExtra("name1", editTextName.getText().toString());
        }
        if (player == "player2");
        {
            intent.putExtra("name2", editTextName.getText().toString());
        }
        setResult(RESULT_OK, intent);
        finish();
    }

}