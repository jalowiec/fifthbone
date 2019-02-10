package com.jalowiec;


import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class DiceGenerator {

    private GridPane gride;
    RandomDiceValue randomDiceValue = new RandomDiceValue();
    List<Die> dieList = new ArrayList<>();


    public DiceGenerator(GridPane gride) {
        this.gride = gride;
    }


    public void createDices(){

        Die die1 =  new Die("file:resources/dice1_white_64.png",1);
        Die die2 =  new Die("file:resources/dice2_white_64.png",2);
        Die die3 =  new Die("file:resources/dice3_white_64.png",3);
        Die die4 =  new Die("file:resources/dice4_white_64.png",4);
        Die die5 =  new Die("file:resources/dice5_white_64.png", 5);
        Die die6 =  new Die("file:resources/dice6_white_64.png", 6);
        dieList.add(die1);
        dieList.add(die2);
        dieList.add(die3);
        dieList.add(die4);
        dieList.add(die5);
        dieList.add(die6);

    }

    public Die getDiceFromValue(int diceValue){
        Die result = null;
        for(Die die : dieList){
            if(diceValue == die.getDiceValue()){
                result = die;
            }
        }
        return result;
    }


    public void generateDicesInSlots(){
        int[] dicesValues = randomDiceValue.getRandomArray(5);
        DieSlotsManager dieSlotsManager = new DieSlotsManager(gride);
        dieSlotsManager.generateSlots();

        for(int i=0; i<dicesValues.length; i++){
          dieSlotsManager.drawDieInSlot(getDiceFromValue(dicesValues[i]), i);
    }



   }

}
