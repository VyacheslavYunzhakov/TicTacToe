package com.slava.tictactoe;


import android.widget.ImageButton;

import java.util.List;

public  class WinCheckUtils {


    public static int checkForWin(int position, List<int[]> arrayOfIndents, List<Integer> results,
                                  List<ImageButton> buttonList, int Rows) {

        int checkResult;
        checkResult = checking( position,  results, arrayOfIndents.get(position)[2],  100,
                arrayOfIndents.get(position)[3], 100, 1, buttonList);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        checkResult = checking( position,  results, arrayOfIndents.get(position)[0],  100,
                arrayOfIndents.get(position)[1], 100, Rows, buttonList);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        checkResult = checking( position,  results, arrayOfIndents.get(position)[0],  arrayOfIndents.get(position)[3],
                arrayOfIndents.get(position)[1], arrayOfIndents.get(position)[2], Rows - 1, buttonList);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        checkResult = checking( position,  results, arrayOfIndents.get(position)[0],  arrayOfIndents.get(position)[2],
                arrayOfIndents.get(position)[1], arrayOfIndents.get(position)[3], Rows + 1, buttonList);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}
        return 0;

    }

    private static int checking(int position, List<Integer> results,
                         int firstIndentDirection, int secondIndentDirection, int thirdIndentDirection,
                         int fourthIndentDirection, int direction,  List<ImageButton> buttonList){
        for (int k = -(Math.min(firstIndentDirection,secondIndentDirection));
             k <= -(Math.max(4 - thirdIndentDirection,4 -fourthIndentDirection)); k++) {
            int counter = 0;
            for (int l = 0; l < 5; l++) {
                counter += results.get(position + (direction * k) + direction * l);
            }
            if (counter == 5) {
                PaintTheWinner(k, direction, position, buttonList, counter);
                return 2;
            }
            if (counter == 0) {
                PaintTheWinner(k, direction, position, buttonList, counter);
                return 1;
            }
        }
        return 0;
    }

    public static void setButtonsNotClickable (List<ImageButton> buttonList, List<Integer> results) {
        for (int k = 0; k < results.size(); k++){
            ImageButton buttonNotClickable = buttonList.get(k);
            buttonNotClickable.setClickable(false);
        }
    }
    private static void PaintTheWinner (int k, int direction, int position, List<ImageButton> buttonList,
                                 int counter){
        for (int l = 0; l < 5; l++) {
            int positionOfPantingButton = position + (direction * k) + direction * l;
            if (counter == 5) {
                ImageButton buttonPainted = buttonList.get(positionOfPantingButton);
                buttonPainted.setImageResource(R.drawable.painted_toe);
                buttonPainted.setClickable(false);
            }
            if (counter == 0) {
                ImageButton buttonPainted = buttonList.get(positionOfPantingButton);
                buttonPainted.setImageResource(R.drawable.painted_cross);
                buttonPainted.setClickable(false);
            }
        }
    }
}

