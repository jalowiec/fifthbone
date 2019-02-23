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
    private DiceSlotsOperation diceSlotsOperation = DiceSlotsOperation.getInstance();


    public RoundInitCommon(User user) {
        this.gridPane = user.getGridPane();
        this.user = user;
    }



    public void generateDicesInSlots() {

        removeAllDiceFromSlots();
        RandomDiceValue randomDiceValue = new RandomDiceValue();
        int[] randomValues = randomDiceValue.getRandomArray(5);
        for (int i = 0; i < randomValues.length; i++) {
            drawDieInSlot(diceSlotsOperation.getDieFromValue(randomValues[i]), i);
        }
    }

    public void drawDieInSlot(Die die, int slotNumber) {
        RoundProccesorUser roundProccesorUser = new RoundProccesorUser(user);
        EventHandler<MouseEvent> mouseHandler = e -> {
            roundProccesorUser.swapDieInSlots(slotNumber);

        };
        Die[] diceList = user.getUserDataStructures().getDiceList();
        List<ImageView> imageViewList = user.getUserDataStructures().getImageViewList();
        List<DieSlot> diceSlotsList = user.getUserDataStructures().getDiceSlotsList();


        diceList[slotNumber] = die;
        ImageView imageView = new ImageView(die.getDieImage());

        imageView.setOnMouseClicked(mouseHandler);
        imageView.setCursor(Cursor.CLOSED_HAND);
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
