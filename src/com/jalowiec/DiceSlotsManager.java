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
    private List<Die> diceLists;
    private List<DieSlot> dieSlotsList;
    private List<DieSlot> dieFreeSlotsList;
    private List<DieSlot> dieBusySlotsList = new ArrayList<>();
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

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public void generateDice(){
        diceGenerator.createDices();
        diceLists = diceGenerator.getDiceList();
    }

    public void generateSlots(){
        DieSlotsGenerator dieSlotsGenerator = new DieSlotsGenerator();
        dieSlotsGenerator.generateSlots();
        dieSlotsList = dieSlotsGenerator.getSlotsList();
        dieFreeSlotsList = dieSlotsGenerator.getFreeSlotsList();
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
            hideDieFromSlot(slotNumber);
            showDieInFreeSlot(slotNumber);
        };

        ImageView imageView = new ImageView(die.getDieImage());
        imageView.setOnMouseClicked(mouseHandler);
        imageViewList.add(imageView);

        grid.add(imageView, dieSlotsList.get(slotNumber).getColumnIndex(),
                dieSlotsList.get(slotNumber).getRowIndex(),
                dieSlotsList.get(slotNumber).getColumnSpan(),
                dieSlotsList.get(slotNumber).getRowSpan());
    }


    public void removeAllDiceFromSlots() {
        for(ImageView element : imageViewList){
            grid.getChildren().remove(element);
        }
        imageViewList.clear();

    }

    public void hideDieFromSlot(int slotNumber) {
            grid.getChildren().remove(imageViewList.get(slotNumber));
    }

    public void showDieInFreeSlot(int slotNumber) {
        int firstFreeSlot = dieBusySlotsList.size();
        System.out.println(firstFreeSlot);
        grid.add(imageViewList.get(slotNumber),
                dieFreeSlotsList.get(firstFreeSlot).getColumnIndex(),
                dieFreeSlotsList.get(firstFreeSlot).getRowIndex(),
                dieFreeSlotsList.get(firstFreeSlot).getColumnSpan(),
                dieFreeSlotsList.get(firstFreeSlot).getRowSpan());
        dieBusySlotsList.add(dieFreeSlotsList.get(firstFreeSlot));
        //TODO cos chyba mozna prosciej
    }




}
