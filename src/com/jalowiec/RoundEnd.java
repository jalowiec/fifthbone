package com.jalowiec;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.*;

public class RoundEnd {

    private GridPane gridPane;
    private List<Integer> fifthDiceList = new ArrayList<>();
    private static Map<Integer, Integer> chosenFiftDice = new HashMap<>();
    private DiceSlotsOperation diceSlotsOperation;
    private TableDrawer tableDrawer;
    private Text scoreText;


    public RoundEnd(GridPane gridPane) {
        this.gridPane = gridPane;
        diceSlotsOperation = DiceSlotsOperation.getInstance();
        tableDrawer = new TableDrawer(gridPane);
    }


    public static Set<Integer> getChosenFifthDiceSet(){
        return  chosenFiftDice.keySet();
            }




    private void processFifthDie(int fifthDieValue){
        if(!fifthDiceList.contains(fifthDieValue)){
            if(fifthDiceList.size()<3){
                fifthDiceList.add(fifthDieValue);
                tableDrawer.drawChosenFifthDie(fifthDieValue, chosenFiftDice.size());
                chosenFiftDice.put(fifthDieValue, 0);
            }
        } else{
            chosenFiftDice.put(fifthDieValue, chosenFiftDice.get(fifthDieValue)+1);
            tableDrawer.drawChosenFifthDieSlots(chosenFiftDice.get(fifthDieValue), fifthDiceList.indexOf(fifthDieValue));
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



    public void countScoreAfterRound(TableDrawer tableDrawer){
        RoundInit roundInit = new RoundInit(gridPane);

        int chosenFifthDieValue = diceSlotsOperation.getFifthDieValue();
        int firstPairSum = diceSlotsOperation.getFirstPairSum();
        int secondPairSum = diceSlotsOperation.getSecondPairSum();
        int firstCouplePointer = scorePointerMap.get(firstPairSum);
        if(isPairSlotFree(firstPairSum)) {
            scorePointerMap.replace(firstPairSum, ++firstCouplePointer);
            tableDrawer.drawUsedSlotsAfterRound(firstPairSum, firstCouplePointer);
        }

        int secondCouplePointer = scorePointerMap.get(secondPairSum);
        if(isPairSlotFree(secondPairSum)) {
            scorePointerMap.replace(secondPairSum, ++secondCouplePointer);
            tableDrawer.drawUsedSlotsAfterRound(secondPairSum, secondCouplePointer);
        }
        tableDrawer.drawScore(getScoreFromSchema());

        if(isFifthSlotFree(chosenFifthDieValue)) {
            processFifthDie(chosenFifthDieValue);
        }

        roundInit.generateSlots();
        roundInit.generateDicesInSlots();
   //     roundInit.setEndTurnButtonDisabled();

    }

    public int getFifthDieValue(){

        int[] chosenSlots = freeSlotState.clone();
        Arrays.sort(chosenSlots);
        int fifthDieIndex = 4;
        for(int i=0; i<4; i++){
            if(i!=chosenSlots[i]){
                fifthDieIndex =  i;
                break;
            }
        }
        return diceLists[fifthDieIndex].getDiceValue();
    }

    public int getFirstPairSum(){
        return diceLists[freeSlotState[0]].getDiceValue() + diceLists[freeSlotState[1]].getDiceValue();
    }

    public int getSecondPairSum(){
        return diceLists[freeSlotState[2]].getDiceValue() + diceLists[freeSlotState[3]].getDiceValue();
    }

    public Text getScoreText() {
        return scoreText;
    }

    public void setScoreText(Text scoreText) {
        this.scoreText = scoreText;
    }



}
