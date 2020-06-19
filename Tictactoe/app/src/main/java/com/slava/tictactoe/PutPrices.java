package com.slava.tictactoe;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class PutPrices {

    Random random = new Random();
    int randomBound = 400;

    int[] checkablePositions = {-21, -20, -19, -1, 1, 19, 20, 21};

    List<int[]> countOFPriceRaises = new ArrayList<>();

    private void puttingPricesOfCombinationsDef(int[] combination, List<Integer> prices, int position,
                                                 int checkingPosition, int priceIncrease,
                                                 int indexIncrease, List<int[]> arrayOfIndentsForPrises,
                                                List<Integer> results)
    {
        int Indent1 = placerOfIndents(position,checkingPosition,combination.length - 1, arrayOfIndentsForPrises)[0];
        int Indent2 = placerOfIndents(position,checkingPosition,combination.length - 1, arrayOfIndentsForPrises)[1];

        for (int i = Indent1; i <= Indent2; i++) {
            int[] combinationArray = new int[combination.length];
            for (int j = 0; j < combination.length; j++) {
                combinationArray[j] = results.get(position + (j + i) * checkingPosition);
            }
            if (Arrays.equals(combinationArray, combination)) {
                if (prices.get(position + (i + indexIncrease) * checkingPosition) <= priceIncrease) {
                    prices.set(position + (i+ indexIncrease) * checkingPosition, priceIncrease + random.nextInt(randomBound));
                }
            }
        }
    }
    private List<int[]> puttingPricesOfCombinationsAttack(int[] combination, List<Integer> prices,
                                                          int position,int checkingPosition,
                                                          int priceIncrease, int indexIncrease,
                                                          List<int[]> arrayOfIndentsForPrises,
                                                          List<Integer> results) {
        int Indent1 = placerOfIndents(position,checkingPosition,combination.length - 1, arrayOfIndentsForPrises)[0];
        int Indent2 = placerOfIndents(position,checkingPosition,combination.length - 1, arrayOfIndentsForPrises)[1];

        for (int i = Indent1; i <= Indent2;i++) {
            int[] combinationArray = new int[combination.length];
            for (int j = 0; j < combination.length; j++) {
                combinationArray[j] = results.get(position + (j + i) * checkingPosition);
            }
            if (Arrays.equals(combinationArray, combination)) {
                //Проверяем увеличивалась ли стоимость на данной линии (-21 и +21- одна линия (checkingPosition(checkingPosition)))
                if (countOFPriceRaises.contains(new int[]{chkPos(checkingPosition),
                        position + (i + indexIncrease) * checkingPosition})) {
                } else {
                    prices.set(position + (i + indexIncrease) * checkingPosition,
                            prices.get(position + (i + indexIncrease) * checkingPosition) + priceIncrease);
                    countOFPriceRaises.add(new int[]{chkPos(checkingPosition),
                            position + (i + indexIncrease) * checkingPosition});
                }
            }
        }
        return countOFPriceRaises;
    }

    protected void putPricesDef(int position, List<Integer> results, List<Integer> prices, List<int[]> arrayOfIndentsForPrises) {

        for (int checkingPosition : checkablePositions){

            //open and half closed 4
                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 0, 0, 6},  prices, position,
                        checkingPosition, 999999,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 0, 0, 6}, prices, position,
                        checkingPosition,999999,5, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 0, 0, 0, 6},  prices, position,
                        checkingPosition, 999999,5, arrayOfIndentsForPrises, results);

            //4 with a gap
                puttingPricesOfCombinationsDef(new int[]{1, 0, 0, 0, 6, 0, 6}, prices, position,
                        checkingPosition, 888888,4, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 0, 0, 6, 0, 6}, prices, position,
                        checkingPosition, 20000,6, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 0, 6, 0, 0, 6},  prices, position,
                        checkingPosition, 888888,3, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 0, 6, 0, 0, 6}, prices, position,
                        checkingPosition, 20000,6, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 6, 0, 0, 0, 6}, prices,
                        position,  checkingPosition, 888888,2, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 6, 0, 0, 0, 6}, prices, position,
                        checkingPosition, 20000,6, arrayOfIndentsForPrises, results);


            //open and half closed 3

                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 0, 6}, prices, position,
                        checkingPosition, 30000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 0, 6}, prices, position,
                        checkingPosition, 30000,4, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{1, 0, 0, 0, 6}, prices, position,
                        checkingPosition, 15000,4, arrayOfIndentsForPrises, results);

            //3 with a gap
                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 6, 0, 1}, prices, position,
                        checkingPosition, 8000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 6, 0, 1}, prices, position,
                        checkingPosition, 8000,3, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{6, 0, 6, 0, 0, 1}, prices, position,
                        checkingPosition, 8000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{6, 0, 6, 0, 0, 1}, prices, position,
                        checkingPosition, 8000,2, arrayOfIndentsForPrises, results);

            //Open 2

                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 6}, prices, position,
                        checkingPosition, 2000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsDef(new int[]{6, 0, 0, 6}, prices, position,
                        checkingPosition, 2000,3, arrayOfIndentsForPrises, results);


        }
    }

    protected void putPricesAttack(int position, List<Integer> results, List<Integer> prices, List<int[]> arrayOfIndentsForPrises) {
        for (int checkingPosition : checkablePositions){


            //open and half closed 4
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 1, 1, 1, 6}, prices, position,
                        checkingPosition, 999999,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 1, 1, 1, 6}, prices, position,
                        checkingPosition, 999999,5, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{0, 1, 1, 1, 1, 6}, prices, position,
                        checkingPosition, 999999,5, arrayOfIndentsForPrises, results);

            //half closed 4 with a gap
                puttingPricesOfCombinationsAttack(new int[]{0, 1, 1, 1, 6, 1, 6}, prices, position,
                        checkingPosition, 999999,4, arrayOfIndentsForPrises, results);

                puttingPricesOfCombinationsAttack(new int[]{0, 1, 1, 1, 6, 1, 6}, prices, position,
                        checkingPosition, 20000,4, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{0, 1, 1, 6, 1, 1, 6},prices, position,
                        checkingPosition, 999999,3, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{0, 1, 6, 1, 1, 1, 6},prices, position,
                        checkingPosition, 999999,2, arrayOfIndentsForPrises, results);

            //open and half closed 3
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 1, 1, 6}, prices, position,
                        checkingPosition, 30000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 1, 1, 6}, prices, position,
                        checkingPosition, 30000,4, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{0, 1, 1, 1, 6}, prices, position,
                        checkingPosition, 20000,4, arrayOfIndentsForPrises, results);


            // half closed 3 with a gap
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 1, 6, 1, 0}, prices, position,
                        checkingPosition, 28000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 1, 6, 1, 0},prices, position,
                        checkingPosition, 32000,3, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 6, 1, 1, 0}, prices, position,
                        checkingPosition, 20000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{6, 1, 6, 1, 1, 0},prices, position,
                        checkingPosition, 32000,2, arrayOfIndentsForPrises, results);



            //open 2
                puttingPricesOfCombinationsAttack(new int[]{6, 0, 0, 6}, prices, position,
                        checkingPosition, 28000,0, arrayOfIndentsForPrises, results);
                puttingPricesOfCombinationsAttack(new int[]{6, 0, 0, 6}, prices, position,
                        checkingPosition, 28000,3, arrayOfIndentsForPrises, results);

        }

    }

    protected void putPricesAfterDef(int position, List<Integer> results, List<Integer> prices, List<int[]> arrayOfIndentsForPrises) {
        int Intendent1 = 0;
        int Intendent2 = 0;
        for (int checkingPosition : checkablePositions){


            //открытая и полузакрытая  тройки
            Intendent1 = placerOfIndents(position,checkingPosition,5, arrayOfIndentsForPrises)[0];
            Intendent2 = placerOfIndents(position,checkingPosition,5, arrayOfIndentsForPrises)[1];
            for (int i = Intendent1; i <= Intendent2;i++)
            {
                int[] combinationArray = new int[5];
                for (int j = 0; j <=  4; j++)
                {
                    combinationArray[j] = results.get(position + (j+i) * checkingPosition);
                }
                if (Arrays.equals(combinationArray, new int[]{1, 0, 0, 0, 6}))
                {
                    prices.set(position + (i + 4) * checkingPosition, 15000 + random.nextInt(400));
                }
            }

            Intendent1 = placerOfIndents(position,checkingPosition,6, arrayOfIndentsForPrises)[0];
            Intendent2 = placerOfIndents(position,checkingPosition,6, arrayOfIndentsForPrises)[1];
            // Полузакрытая тройка с брешью
            for (int i = Intendent1; i <= Intendent2;i++)
            {
                int[] combinationArray = new int[6];
                for (int j = 0; j <=  5; j++)
                {
                    combinationArray[j] = results.get(position + (j+i) * checkingPosition);
                }
                if (Arrays.equals(combinationArray, new int[]{1, 0, 0, 6, 0, 1}))
                {
                    prices.set(position + (i + 3) * checkingPosition, 0);
                }
                if (Arrays.equals(combinationArray, new int[]{1, 0, 6, 0, 0, 1}))
                {
                    prices.set(position + (i + 2) * checkingPosition, 0);
                }
            }

            //Открытая двойка
            Intendent1 = placerOfIndents(position,checkingPosition,4, arrayOfIndentsForPrises)[0];
            Intendent2 = placerOfIndents(position,checkingPosition,4, arrayOfIndentsForPrises)[1];
            for (int i = Intendent1; i <= Intendent2;i++)
            {
                int[] combinationArray = new int[4];
                for (int j = 0; j <=  3; j++)
                {
                    combinationArray[j] = results.get(position + (j+i) * checkingPosition);
                }
                if (Arrays.equals(combinationArray, new int[]{1, 0, 0, 6}))
                {
                    prices.set(position + (i + 3) * checkingPosition, 0);
                }
            }

        }
    }

    public  int[] placerOfIndents(int position, int checkingPosition, int int1,  List<int[]> arrayOfIndentsForPrises)
    {
        int Indent1 = 0;
        int Indent2 = 0;
        int[] Indents;

        if (checkingPosition == 21)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[0],arrayOfIndentsForPrises.get(position)[2],
                    arrayOfIndentsForPrises.get(position)[1],arrayOfIndentsForPrises.get(position)[3], int1);
            Indent1=Indents[0];
            Indent2=Indents[1];

        }

        if (checkingPosition == 20)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[0],100,
                    arrayOfIndentsForPrises.get(position)[1],100, int1);
            Indent1=Indents[0];
            Indent2=Indents[1];
        }

        if (checkingPosition == 19)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[0],arrayOfIndentsForPrises.get(position)[3],
                    arrayOfIndentsForPrises.get(position)[1],arrayOfIndentsForPrises.get(position)[2], int1);
            Indent1=Indents[0];
            Indent2=Indents[1];

        }

        if (checkingPosition == 1)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[2],100,
                    arrayOfIndentsForPrises.get(position)[3],100, int1);
            Indent1=Indents[0];
            Indent2=Indents[1];
        }

        if (checkingPosition == -1)
        {Indents = Indents(arrayOfIndentsForPrises.get(position)[3],100,
                arrayOfIndentsForPrises.get(position)[2],100, int1);
            Indent1=Indents[0];
            Indent2=Indents[1];
        }

        if (checkingPosition == -19)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[1],arrayOfIndentsForPrises.get(position)[2],
                    arrayOfIndentsForPrises.get(position)[0],arrayOfIndentsForPrises.get(position)[3], int1);
            Indent1=Indents[0];
            Indent2=Indents[1];
        }

        if (checkingPosition == -20)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[1],100,
                    arrayOfIndentsForPrises.get(position)[0],100, int1);
            Indent1=Indents[0];
            Indent2=Indents[1];
        }

        if (checkingPosition == -21)
        {
            Indents = Indents(arrayOfIndentsForPrises.get(position)[1],arrayOfIndentsForPrises.get(position)[3],
                    arrayOfIndentsForPrises.get(position)[0],arrayOfIndentsForPrises.get(position)[2], int1);
            Indent1=Indents[0];
            Indent2=Indents[1];
        }
        return new int[]{Indent1,Indent2};
    }

    private int[] Indents (int firstIndentDirection, int secondIndentDirection,
                           int thirdIndentDirection, int fourthIndentDirection,  int int1){
        int Indent1;
        int Indent2;
        Indent1 = -Math.min(firstIndentDirection, secondIndentDirection);
            if (Indent1 < -int1){ Indent1 = -int1;}
        Indent2 = Math.min(thirdIndentDirection, fourthIndentDirection) - int1;
            if (Indent2 > 0){Indent2 = 0;}
        return new int[]{Indent1,Indent2};
    }
    private static int chkPos(int chkPos){
        if (chkPos == 21 || chkPos == -21){
            return 21;
        }
        if (chkPos == 20 || chkPos == -20){
            return 20;
        }
        if (chkPos == 19 || chkPos == -19){
            return 19;
        }
        if (chkPos == 1 || chkPos == -1){
            return 1;
        }
        if (chkPos == 0){
            return 0;
        }
        return 1;
    }

}
