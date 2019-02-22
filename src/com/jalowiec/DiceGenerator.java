package com.jalowiec;


import java.util.ArrayList;
import java.util.List;

public class DiceGenerator {

    private List<Die> diceList = new ArrayList<>();


    public void createDices(){
        String diePathBegining = "file:resources/dice";
        String diePathEnd = "_white_64.png";
        for (int i = 1; i < 7; i++) {
            diceList.add(new Die((diePathBegining + i + diePathEnd), i));
        }

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
