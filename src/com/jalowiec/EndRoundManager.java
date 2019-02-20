package com.jalowiec;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.util.*;

import static java.util.Arrays.sort;

public class EndRoundManager {

    private GridPane gridPane;
    private Scene scene;
    private int[][] scoreSchema;
    private List<Integer> fifthDiceList = new ArrayList<>();
    private Map<Integer, Integer> scorePointerMap;
    private Map<Integer, Integer> chosenFiftDice = new HashMap<>();
    private DiceSlotsManager diceSlotsManager;
    private UserGameTableDrawer userGameTableDrawer;


    public EndRoundManager(GridPane gridPane, Scene scene) {
        this.gridPane = gridPane;
        this.scene = scene;
        initScoreSchema();
        initScorePointerMap();
        diceSlotsManager = DiceSlotsManager.getInstance(gridPane, scene);
        userGameTableDrawer = new UserGameTableDrawer(gridPane, scene);
    }


    public void initScoreSchema(){
        scoreSchema = new int[][] {
                {2, 0, -30, -20, -10, 0, 10, 20, 30, 40, 50},
                {3, 0, -21, -14, -7, 0, 7, 14, 21, 28, 35},
                {4, 0, -18, -12, -6, 0, 6, 12, 18, 24, 30},
                {5, 0, -15, -10, -5, 0, 5, 10, 15, 20, 25},
                {6, 0, -12, -8, -4, 0, 4, 8, 12, 16, 20},
                {7, 0, -9, -6, -3, 0, 3, 6, 9, 12, 15},
                {8, 0, -12, -8, -4, 0, 4, 8, 12, 16, 20},
                {9, 0, -15, -10, -5, 0, 5, 10, 15, 20, 25},
                {10, 0, -18, -12, -6, 0, 6, 12, 18, 24, 30},
                {11, 0, -21, -14, -7, 0, 7, 14, 21, 28, 35},
                {12, 0, -30, -20, -10, 0, 10, 20, 30, 40, 50}};
    }

    public int getScoreFromSchema(){
        int result= 0;
        for(int i=0; i<11; i++){
            result += scoreSchema[i][scorePointerMap.get(scoreSchema[i][0])];

        }

        return result;
    }


    public void initScorePointerMap(){
        scorePointerMap = new HashMap<>();
        scorePointerMap.put(2, 1);
        scorePointerMap.put(3, 1);
        scorePointerMap.put(4, 1);
        scorePointerMap.put(5, 1);
        scorePointerMap.put(6, 1);
        scorePointerMap.put(7, 1);
        scorePointerMap.put(8, 1);
        scorePointerMap.put(9, 1);
        scorePointerMap.put(10, 1);
        scorePointerMap.put(11, 1);
        scorePointerMap.put(12, 1);
    }




    private void processFifthDie(int fifthDieValue){
        if(!fifthDiceList.contains(fifthDieValue)){
            if(fifthDiceList.size()<3){
                fifthDiceList.add(fifthDieValue);
                userGameTableDrawer.drawChosenFifthDie(fifthDieValue, chosenFiftDice.size());
                chosenFiftDice.put(fifthDieValue, 0);
            }
        } else{
            chosenFiftDice.put(fifthDieValue, chosenFiftDice.get(fifthDieValue)+1);
            userGameTableDrawer.drawChosenFifthDieSlots(chosenFiftDice.get(fifthDieValue), fifthDiceList.indexOf(fifthDieValue));
        }
    }

    public boolean isFifthSlotFree(int fifthDieValue){
        if(chosenFiftDice.containsKey(fifthDieValue) && chosenFiftDice.get(fifthDieValue) == 8){
            return false;
        }
        return true;
    }


    public boolean isPairSlotFree(int pairSum){

        if(scorePointerMap.get(pairSum)>9){
            return false;
        }
        return true;
    }



    public void countScoreAfterRound(UserGameTableDrawer userGameTableDrawer){

        int chosenFifthDieValue = diceSlotsManager.getFifthDieValue();
        int firstPairSum = diceSlotsManager.getFirstPairSum();
        int secondPairSum = diceSlotsManager.getSecondPairSum();
        int firstCouplePointer = scorePointerMap.get(firstPairSum);
        if(isPairSlotFree(firstPairSum)) {
            scorePointerMap.replace(firstPairSum, ++firstCouplePointer);
            userGameTableDrawer.drawUsedSlotsAfterRound(firstPairSum, firstCouplePointer);
        }

        int secondCouplePointer = scorePointerMap.get(secondPairSum);
        if(isPairSlotFree(secondPairSum)) {
            scorePointerMap.replace(secondPairSum, ++secondCouplePointer);
            userGameTableDrawer.drawUsedSlotsAfterRound(secondPairSum, secondCouplePointer);
        }
        userGameTableDrawer.drawScore(getScoreFromSchema());

        if(isFifthSlotFree(chosenFifthDieValue)) {
            processFifthDie(chosenFifthDieValue);
        }

        diceSlotsManager.generateSlots();
        diceSlotsManager.generateDicesInSlots();
        diceSlotsManager.setEndTurnButtonDisabled();

    }




}
