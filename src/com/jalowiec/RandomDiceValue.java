package com.jalowiec;

import java.util.Random;

public class RandomDiceValue {
    Random random = new Random();


    public int getRandomNumber(){
        int randInt = random.nextInt(6)+1;
        return randInt;
    }

    public int[] getRandomArray(int arrayRange){
        int [] randomArray = new int[arrayRange];

        for(int i=0; i<arrayRange; i++){
            randomArray[i] = getRandomNumber();
        }
        return randomArray;
    }

}
