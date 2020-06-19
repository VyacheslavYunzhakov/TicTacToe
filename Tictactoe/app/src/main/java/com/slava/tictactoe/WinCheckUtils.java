package com.slava.tictactoe;


import android.widget.ImageButton;

import java.util.List;

public  class WinCheckUtils {

    public static int checkForWin(int position, List<int[]> arrayOfIndents, List<Integer> results) {

        int checkResult;
        checkResult = checking( position,  results, arrayOfIndents.get(position)[2],  100,
                arrayOfIndents.get(position)[3], 100, 1);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        checkResult = checking( position,  results, arrayOfIndents.get(position)[0],  100,
                arrayOfIndents.get(position)[1], 100, 20);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        checkResult = checking( position,  results, arrayOfIndents.get(position)[0],  arrayOfIndents.get(position)[3],
                arrayOfIndents.get(position)[1], arrayOfIndents.get(position)[2], 19);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        checkResult = checking( position,  results, arrayOfIndents.get(position)[0],  arrayOfIndents.get(position)[2],
                arrayOfIndents.get(position)[1], arrayOfIndents.get(position)[3], 21);
        if (checkResult==1){return 1;}
        if (checkResult==2){return 2;}

        return 0;
    }

    private static int checking(int position, List<Integer> results,
                         int firstIndentDirection, int secondIndentDirection, int thirdIndentDirection,
                         int fourthIndentDirection, int direction){
        for (int k = -(Math.min(firstIndentDirection,secondIndentDirection));
             k <= -(Math.max(4 - thirdIndentDirection,4 -fourthIndentDirection)); k++) {
            int counter = 0;
            for (int l = 0; l < 5; l++) {
                counter += results.get(position + (direction * k) + direction * l);
            }
            if (counter == 5) {
                return 2;
            }
            if (counter == 0) {
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
}
