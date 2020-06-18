package com.slava.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class P2Name extends AppCompatActivity implements View.OnClickListener {
    EditText etName;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pname);

        etName = findViewById(R.id.editTextName);
        btnOK = findViewById(R.id.buttonOK);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name2", etName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}