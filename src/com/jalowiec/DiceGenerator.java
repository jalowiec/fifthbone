package com.jalowiec;


import java.util.ArrayList;
import java.util.List;

public class DiceGenerator {

    private List<Die> diceForPlaying = new ArrayList<>();


    public void createDiceForPlaying(){
        String diePathBegining = "file:resources/dice";
        String diePathEnd = "_white_64.png";
        for (int i = 1; i < 7; i++) {
            diceForPlaying.add(new Die((diePathBegining + i + diePathEnd), i));
        }

    }

    public List<Die> getDiceForPlaying() {
        return diceForPlaying;
    }



}
