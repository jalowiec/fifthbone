package com.jalowiec;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataStructures {

    private int[] freeSlotState = new int[4];
    private List<ImageView> imageViewList = new ArrayList<>();
    private Die[] diceList = new Die[5];
    private List<DieSlot> diceSlotsList;
    private List<DieSlot> freeSlotsList;
    private Map<Integer, Integer> scorePointerMap;
    private Text scoreText;


    public UserDataStructures() {
        generateSlots();
        initScorePointerMap();
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public Die[] getDiceList() {
        return diceList;
    }

    public Map<Integer, Integer> getScorePointerMap() {
        return scorePointerMap;
    }

    public int[] getFreeSlotState() {
        return freeSlotState;
    }

    public void setFreeSlotState(int[] freeSlotState) {
        this.freeSlotState = freeSlotState;
    }

    public List<DieSlot> getDiceSlotsList() {
        return diceSlotsList;
    }

    public List<DieSlot> getFreeSlotsList() {
        return freeSlotsList;
    }

    public Text getScoreText() {
        return scoreText;
    }

    public void setScoreText(Text scoreText) {
        this.scoreText = scoreText;
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

}
