package com.slava.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity  {
    private List<ImageButton> buttonList = new ArrayList<>();
    private List<Integer> results = new ArrayList<>();
    private List<int[]> arrayOfIndents = new ArrayList<>();
    int leftIndent = 4, rightIndent = 4, topIndent = 4, botIndent = 4;

    int counterForGame = 1;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent prvIntent = getIntent();
        final String name1 = prvIntent.getStringExtra("name1");
        final String name2 = prvIntent.getStringExtra("name2");

        int Rows = 20;
        int Columns = 20;

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                results.add(6);
            }
        }

        for (int i = 0; i < Rows*Columns; i++){
            if (i % Rows < 4) {
                topIndent = i % 20;
            }
            if (i % Rows >= 4){
                topIndent = 4;
            }
            if (i % Rows < 16) {
                botIndent = 4;
            }
            if (i % Rows >= 16){
                botIndent = 19 - (i % 20);
            }
            if (i / Columns < 4) {
                leftIndent = i / 20;
            }
            if (i / Columns >= 4){
                leftIndent = 4;
            }
            if (i / Columns < 16) {
                rightIndent = 4;
            }
            if (i / Columns >= 16){
                rightIndent = 19 - (i / 20);
            }
            arrayOfIndents.add(arrayOfIndents.size(), new int[]{leftIndent, rightIndent, topIndent, botIndent});
        }


        LinearLayout tableLayout = findViewById(R.id.LinearLayoutGame);

        for (int i = 0; i < Rows; i++) {
            LinearLayout linearLayout = new LinearLayout(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            params.weight = 1;
            params.leftMargin -= 3;
            params.rightMargin -= 1;
            params.topMargin -= 10;
            params.bottomMargin -= 10;


            for (int j = 0; j < Columns; j++) {

                ImageButton button = new ImageButton(this);

                button.setLayoutParams(params);

                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor));

                button.setImageResource(R.drawable.clear);
                button.setId(buttonList.size());

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = v.getId();
                        ImageButton button = buttonList.get(position);
                        if (counterForGame % 2 != 0) {
                            button.setImageResource(R.drawable.cross);
                            button.setClickable(false);
                            results.set(position, 0);
                            counterForGame++;
                        } else {
                            button.setImageResource(R.drawable.toe);
                            button.setClickable(false);
                            results.set(position, 1);
                            counterForGame++;
                        }

                        int checkForWin =WinCheckUtils.checkForWin(position, arrayOfIndents,  results);
                      if  (checkForWin == 1) {
                          Intent intent = new Intent(getApplicationContext(), PlayerWin.class);
                          intent.putExtra("name", name1);
                          startActivity(intent);
                      }
                      if (checkForWin == 2) {
                          Intent intent = new Intent(getApplicationContext(), PlayerWin.class);
                          intent.putExtra("name", name2);
                          startActivity(intent);
                      }

                    }


                });
                buttonList.add(button);
                linearLayout.addView(button);
            }

            tableLayout.addView(linearLayout, params);
        }


    }



}
