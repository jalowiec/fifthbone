package com.jalowiec;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.*;

public class DiceSlotsOperation {

    private static DiceSlotsOperation instance;
    private int[][] scoreSchema;


    private DiceSlotsOperation(){
        initScoreSchema();
    }

    public static DiceSlotsOperation getInstance(){
        if(instance==null){
            instance=new DiceSlotsOperation();
        }
        return instance;
    }


    public void generateDice(){
        DiceGenerator diceGenerator = new DiceGenerator();
        diceGenerator.createDices();
    }


    private void initScoreSchema(){
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



    public int getScoreFromSchema(Map<Integer, Integer>scorePointerMap){
        int result= 0;
        for(int i=0; i<11; i++){
            result += scoreSchema[i][scorePointerMap.get(scoreSchema[i][0])];

        }

        return result;
    }



    /*


    public boolean isSlotNumberChosen(int slotNumber){
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i] == slotNumber){
                return true;
            }
        }
        return false;
    }



    public void removeDieInSlot(int slotNumber) {
            grid.getChildren().remove(imageViewList.get(slotNumber));
    }


    public int getFirstFreeSlotIndex(){
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i] == -1){
                return i;
            }
        }
        return -1;
    }


    public boolean isFreeSlot(){
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i]==-1){
                return true;
            }
        }

        return false;
    }

    public void closeFreeSlot(int freeSlotPosition, int slotNumber){
        freeSlotState[freeSlotPosition] = slotNumber;
    }

    public void openFreeSlot(int slotNumber){
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i]==slotNumber){
                freeSlotState[i]=-1;
            }
        }
    }


    public void swapDieInSlots(int slotNumber) {
        if (isSlotNumberChosen(slotNumber)) {
            setEndTurnButtonDisabled();
            openFreeSlot(slotNumber);
            removeDieInSlot(slotNumber);
            grid.add(imageViewList.get(slotNumber),
                    diceSlotsList.get(slotNumber).getColumnIndex(),
                    diceSlotsList.get(slotNumber).getRowIndex(),
                    diceSlotsList.get(slotNumber).getColumnSpan(),
                    diceSlotsList.get(slotNumber).getRowSpan());
        } else {
            if (isFreeSlot()) {
                int firstFreeSlot = getFirstFreeSlotIndex();
                closeFreeSlot(firstFreeSlot, slotNumber);
                setEndTurnButtonEnabled();
                removeDieInSlot(slotNumber);
                grid.add(imageViewList.get(slotNumber),
                        freeSlotsList.get(firstFreeSlot).getColumnIndex(),
                        freeSlotsList.get(firstFreeSlot).getRowIndex(),
                        freeSlotsList.get(firstFreeSlot).getColumnSpan(),
                        freeSlotsList.get(firstFreeSlot).getRowSpan());
            }
        }
    }



    public void setEndTurnButtonDisabled(){
        TableDrawer.getEndTurnButton().setDisable(true);
    }

    private void setEndTurnButtonEnabled(){
        if(!isFreeSlot() && isChosenFifthDieCorrect()){
            TableDrawer.getEndTurnButton().setDisable(false);
        }
    }

    private boolean isFreeRound(Set<Integer> chosenFifthDiceSet) {
        for (int i = 0; i < diceLists.length; i++) {
            if (chosenFifthDiceSet.contains(diceLists[i].getDiceValue())) {
                return false;
            }
        }
        return true;
    }

    public boolean isChosenFifthDieCorrect(){
        int fifthDieValue = getFifthDieValue();
        Set<Integer> chosenFifthDiceSet = RoundEnd.getChosenFifthDiceSet();
        if(chosenFifthDiceSet.size()==3 && !chosenFifthDiceSet.contains(fifthDieValue) && !isFreeRound(chosenFifthDiceSet) ){
            return false;
        }

        return true;
    }


*/
}

