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
import java.util.Random;

public class GameVsComputer extends AppCompatActivity  {

    final int REQUEST_CODE = 1;
    private List<ImageButton> buttonList = new ArrayList<>();
    protected  List<Integer> results = new ArrayList<>();
    private List<int[]> arrayOfIndents = new ArrayList<>();
    protected List<int[]> arrayOfIndentsForPrises = new ArrayList<>();
    protected List<Integer> prices = new ArrayList<>();
    int leftIndent = 4, rightIndent = 4, topIndent = 4, botIndent = 4;
    int Rows = 20;
    int Columns = 20;
    int[] checkablePositions = {-(Rows + 1), -Rows, -(Rows -1), -1, 1, Rows - 1,Rows , Rows +1};
    Random random = new Random();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final PutPrices putPrices = new PutPrices();


        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                results.add(6); //6-unfilled board, will fill it with 1 and 0
            }
        }
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                prices.add(random.nextInt(400));
            }
        }

        int indent = 4;
        for (int i = 0; i < Rows*Columns; i++){
            if (i % Rows < indent) {
                topIndent = i % Rows;
            }
            if (i % Rows >= indent){
                topIndent = indent;
            }
            if (i % Rows < Rows - indent) {
                botIndent = indent;
            }
            if (i % Rows >= Rows - indent){
                botIndent = (Rows -1) - (i % Rows);
            }
            if (i / Columns < indent) {
                leftIndent = i / Columns;
            }
            if (i / Columns >= indent){
                leftIndent = indent;
            }
            if (i / Columns < Columns - indent) {
                rightIndent = indent;
            }
            if (i / Columns >= Columns - indent){
                rightIndent = (Columns -1) - (i / Columns);
            }
            arrayOfIndents.add(arrayOfIndents.size(), new int[]{leftIndent, rightIndent, topIndent, botIndent});
        }

        for (int i = 0; i < Rows*Columns; i++){
                topIndent = i % Rows;
                botIndent = (Rows - 1) - (i % Rows);
                leftIndent = i / Columns;
                rightIndent = (Columns - 1) - (i / Columns);
            arrayOfIndentsForPrises.add(arrayOfIndentsForPrises.size(), new int[]{leftIndent, rightIndent, topIndent, botIndent});
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
                button.setOnClickListener(new View.OnClickListener() {  // Устанавливаем слушателя
                    @Override
                    public void onClick(View v) {
                        int position = v.getId();
                        ImageButton button = buttonList.get(position);

                            button.setImageResource(R.drawable.cross);
                            button.setClickable(false);
                            results.set(position, 0);


                        int chkForWin = WinCheckUtils.checkForWin(position, arrayOfIndents,  results,
                                buttonList);
                        if  (chkForWin == 1) {
                            Intent intent = new Intent(getApplicationContext(), PlayerWin.class);
                            intent.putExtra("name", "Player");
                            intent.putExtra("game", "vsComputer");
                            startActivityForResult(intent, REQUEST_CODE);
                        }

                        prices.set(position,0);
                        for (int chkPos :checkablePositions)
                        {
                            if (position + chkPos < Rows*Columns & position + chkPos >= 0) {
                                if (results.get(position + chkPos) == 6) {
                                    prices.set(position + chkPos, prices.get(position + chkPos) + 400);
                                }
                            }
                        }

                        putPrices.putPricesDef(position, results, prices, arrayOfIndentsForPrises);

                        for (int index = 0; index < results.size(); index ++)
                        {
                            if (results.get(index) == 1){
                                putPrices.putPricesAttack(index, results, prices, arrayOfIndentsForPrises);
                            }

                        }

                        if  (chkForWin!=1) {
                            int maxIndex = computerTurn();
                            if (chkForWin == 2) {
                                Intent intent = new Intent(getApplicationContext(), PlayerWin.class);
                                intent.putExtra("name", "Computer");
                                intent.putExtra("game", "vsComputer");
                                startActivityForResult(intent, REQUEST_CODE);
                            }
                            else{putPrices.putPricesAfterDef(maxIndex, results, prices, arrayOfIndentsForPrises);}
                        }


                    }

                    // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

                });
                buttonList.add(button);
                linearLayout.addView(button);
            }

            tableLayout.addView(linearLayout, params);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            finish();
        }
    }

    public int computerTurn(){
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < prices.size(); i++) {
            if(maxValue <= prices.get(i)){
                maxValue = prices.get(i);
                maxIndex = i;
            }
        }


        ImageButton button = buttonList.get(maxIndex);

        button.setImageResource(R.drawable.toe);
        button.setClickable(false);
        results.set(maxIndex, 1);
        prices.set(maxIndex, 0);
        return maxIndex;
    }

}
