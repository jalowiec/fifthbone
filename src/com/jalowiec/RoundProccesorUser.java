package com.jalowiec;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Set;

public class RoundProccesorUser {

    private GridPane gridPane;
    private User user;





    public RoundProccesorUser(User user) {
        this.gridPane = user.getGridPane();
        this.user = user;
    }



    public void swapDieInSlots(int slotNumber) {


        if (!user.getRoundEnd().isRoundEnd()) {
            List<ImageView> imageViewList = user.getUserDataStructures().getImageViewList();
            List<DieSlot> diceSlotsList = user.getUserDataStructures().getDiceSlotsList();
            List<DieSlot> freeSlotsList = user.getUserDataStructures().getFreeSlotsList();
            if (isSlotNumberChosen(slotNumber)) {
                setEndTurnButtonDisabled();
                openFreeSlot(slotNumber);
                removeDieInSlot(slotNumber);
                gridPane.add(imageViewList.get(slotNumber),
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
                    gridPane.add(imageViewList.get(slotNumber),
                            freeSlotsList.get(firstFreeSlot).getColumnIndex(),
                            freeSlotsList.get(firstFreeSlot).getRowIndex(),
                            freeSlotsList.get(firstFreeSlot).getColumnSpan(),
                            freeSlotsList.get(firstFreeSlot).getRowSpan());
                }
            }

        }
    }

    public boolean isSlotNumberChosen(int slotNumber){
        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i] == slotNumber){
                return true;
            }
        }
        return false;
    }


    public void removeDieInSlot(int slotNumber) {
        List<ImageView> imageViewList = user.getUserDataStructures().getImageViewList();
        gridPane.getChildren().remove(imageViewList.get(slotNumber));

    }


    public int getFirstFreeSlotIndex(){
        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i] == -1){
                return i;
            }
        }
        return -1;
    }


    public boolean isFreeSlot(){
        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i]==-1){
                return true;
            }
        }

        return false;
    }

    public void closeFreeSlot(int freeSlotPosition, int slotNumber){
        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        freeSlotState[freeSlotPosition] = slotNumber;
    }

    public void openFreeSlot(int slotNumber){
        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i]==slotNumber){
                freeSlotState[i]=-1;
            }
        }
    }


    public void setEndTurnButtonDisabled(){
        user.getGameTableDrawer().getEndTurnButton().setDisable(true);
    }

    private void setEndTurnButtonEnabled(){
        if(!isFreeSlot() && isChosenFifthDieCorrect()){
            user.getGameTableDrawer().getEndTurnButton().setDisable(false);
        }
    }

    public boolean isFreeRound(Set<Integer> chosenFifthDiceSet) {
        Die[] diceList = user.getUserDataStructures().getDiceArray();
        for (int i = 0; i < diceList.length; i++) {
            if (chosenFifthDiceSet.contains(diceList[i].getDiceValue())) {
                return false;
            }
        }
        return true;
    }


    public boolean isChosenFifthDieCorrect(){
        int fifthDieValue = user.getRoundEnd().getFifthDieValue(user.getUserDataStructures().getFreeSlotState().clone());
        Set<Integer> chosenFifthDiceSet = user.getRoundEnd().getChosenFifthDiceSet();
        if(chosenFifthDiceSet.size()==3 && !chosenFifthDiceSet.contains(fifthDieValue) && !isFreeRound(chosenFifthDiceSet) ){
            return false;
        }

        return true;
    }

}
