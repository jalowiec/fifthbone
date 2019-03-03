package com.jalowiec;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.List;

public class RoundInitCommon {

    private GridPane gridPane;
    private User user;
    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();



    public RoundInitCommon(User user) {
        this.gridPane = user.getGridPane();
        this.user = user;
     }



    public void generateDicesInSlots() {
        removeAllDiceFromSlots();
        RandomDiceValue randomDiceValue = new RandomDiceValue();
        int[] randomValues = randomDiceValue.getRandomArray(5);
        for (int i = 0; i < randomValues.length; i++) {
            drawDieInSlot(commonDataStructure.getDieFromValue(randomValues[i]), i);
        }
    }

    public void clearFreeSlotState(){
        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        for(int i=0; i<freeSlotState.length; i++){
            freeSlotState[i]=-1;
        }
    }


    public void drawDieInSlot(Die die, int slotNumber) {

        EventHandler<MouseEvent> mouseHandler = e -> {

            user.getRoundProccesorUser().swapDieInSlots(slotNumber);

        };
        Die[] diceList = user.getUserDataStructures().getDiceArray();
        List<ImageView> imageViewList = user.getUserDataStructures().getImageViewList();
        List<DieSlot> diceSlotsList = user.getUserDataStructures().getDiceSlotsList();

        diceList[slotNumber] = die;
        ImageView imageView = new ImageView(die.getDieImage());

        if(!user.getPC()) {
            imageView.setOnMouseClicked(mouseHandler);
            imageView.setCursor(Cursor.CLOSED_HAND);
        }
        imageViewList.add(imageView);

        gridPane.add(imageView, diceSlotsList.get(slotNumber).getColumnIndex(),
                diceSlotsList.get(slotNumber).getRowIndex(),
                diceSlotsList.get(slotNumber).getColumnSpan(),
                diceSlotsList.get(slotNumber).getRowSpan());
    }

    public void removeAllDiceFromSlots() {
        List<ImageView> imageViewList = user.getUserDataStructures().getImageViewList();
        for(ImageView element : imageViewList){
            gridPane.getChildren().remove(element);
        }
        imageViewList.clear();
    }




}
