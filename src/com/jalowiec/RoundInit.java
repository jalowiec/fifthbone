package com.jalowiec;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class RoundInit {

    private GridPane gridPane;
    private List<DieSlot> diceSlotsList;
    private List<DieSlot> freeSlotsList;
    private User user;


    public RoundInit(User user) {
        this.gridPane = user.getGridPane();
        this.user = user;
    }



    public void generateDicesInSlots() {
        DiceGenerator diceGenerator = new DiceGenerator();
        removeAllDiceFromSlots();
        RandomDiceValue randomDiceValue = new RandomDiceValue();
        int[] randomValues = randomDiceValue.getRandomArray(5);
        for (int i = 0; i < randomValues.length; i++) {
            drawDieInSlot(diceGenerator.getDieFromValue(randomValues[i]), i);
        }
    }

    public void drawDieInSlot(Die die, int slotNumber) {
        EventHandler<MouseEvent> mouseHandler = e -> {
        //    swapDieInSlots(slotNumber);

        };

        diceLists[slotNumber] = die;
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
        for(ImageView element : imageViewList){
            gridPane.getChildren().remove(element);
        }
        imageViewList.clear();
    }




}
