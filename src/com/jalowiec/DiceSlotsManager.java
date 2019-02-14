package com.jalowiec;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class DiceSlotsManager {

    private GridPane grid;
    private List<ImageView> imageViewList = new ArrayList<>();
    private Die[] diceLists = new Die[5];
    private List<DieSlot> diceSlotsList;
    private List<DieSlot> freeSlotsList;
    private int[] freeSlotState = new int[4];
    private DiceGenerator diceGenerator = new DiceGenerator(grid);

    private static DiceSlotsManager instance;

    private DiceSlotsManager(GridPane grid){
        this.grid = grid;
    }
    public static DiceSlotsManager getInstance(GridPane grid){
        if(instance==null){
            instance=new DiceSlotsManager(grid);
        }
        return instance;
    }

    public int[] getFreeSlotState() {
        return freeSlotState;
    }

    public void generateDice(){
        diceGenerator.createDices();
    }

    public void generateSlots(){
        DieSlotsGenerator dieSlotsGenerator = new DieSlotsGenerator();
        dieSlotsGenerator.generateSlots();
        diceSlotsList = dieSlotsGenerator.getSlotsList();
        freeSlotsList = dieSlotsGenerator.getFreeSlotsList();
        for(int i=0; i<freeSlotState.length; i++){
             freeSlotState[i] = -1;
        }
    }

    public void generateDicesInSlots() {
        removeAllDiceFromSlots();
        RandomDiceValue randomDiceValue = new RandomDiceValue();
        int[] randomValues = randomDiceValue.getRandomArray(5);
        for (int i = 0; i < randomValues.length; i++) {
            drawDieInSlot(diceGenerator.getDieFromValue(randomValues[i]), i);
        }
    }

    public void drawDieInSlot(Die die, int slotNumber) {
        EventHandler<MouseEvent> mouseHandler = e -> {
            swapDieInSlots(slotNumber);
        };

        diceLists[slotNumber] = die;
        ImageView imageView = new ImageView(die.getDieImage());
        imageView.setOnMouseClicked(mouseHandler);
        imageViewList.add(imageView);

        grid.add(imageView, diceSlotsList.get(slotNumber).getColumnIndex(),
                diceSlotsList.get(slotNumber).getRowIndex(),
                diceSlotsList.get(slotNumber).getColumnSpan(),
                diceSlotsList.get(slotNumber).getRowSpan());
    }

    public boolean isSlotNumberChosen(int slotNumber){
        for(int i=0; i<freeSlotState.length; i++){
            if(freeSlotState[i] == slotNumber){
                return true;
            }
        }
        return false;
    }

    public void removeAllDiceFromSlots() {
        for(ImageView element : imageViewList){
            grid.getChildren().remove(element);
        }
        imageViewList.clear();
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


    public Die[] getDiceLists() {
        return diceLists;
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
                removeDieInSlot(slotNumber);
                grid.add(imageViewList.get(slotNumber),
                        freeSlotsList.get(firstFreeSlot).getColumnIndex(),
                        freeSlotsList.get(firstFreeSlot).getRowIndex(),
                        freeSlotsList.get(firstFreeSlot).getColumnSpan(),
                        freeSlotsList.get(firstFreeSlot).getRowSpan());
            }
        }
    }
}

