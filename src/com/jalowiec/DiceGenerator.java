package com.jalowiec;


import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class DiceGenerator {

    private GridPane gride;
    private List<Die> diceList = new ArrayList<>();


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
        diceList.add(die1);
        diceList.add(die2);
        diceList.add(die3);
        diceList.add(die4);
        diceList.add(die5);
        diceList.add(die6);

    }

    public List<Die> getDiceList() {
        return diceList;
    }

    public Die getDieFromValue(int dieValue){
        Die result = null;
        for(Die element : diceList){
            if(element.getDiceValue()==dieValue){
                return element;
            }
        }
        return result;
    }

}
