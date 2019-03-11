package com.jalowiec;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataStructures {

    private int[] freeSlotState = new int[4];
    private int[] alternativeSlotState = new int[4];
    private List<ImageView> imageViewList = new ArrayList<>();
    private Die[] diceArray = new Die[5];
    private List<DieSlot> diceSlotsList;
    private List<DieSlot> freeSlotsList;
    private Map<Integer, Integer> scorePointerMap;
    private Map<Integer, Integer> alternativeScorePointerMap;
    private Text scoreText;
    private Text alternativeScoreText;


    public UserDataStructures() {
        generateSlots();
        initScorePointerMap();
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public Die[] getDiceArray() {
        return diceArray;
    }

    public Map<Integer, Integer> getScorePointerMap() {
        return scorePointerMap;
    }

    public Map<Integer, Integer> getAlternativeScorePointerMap() {
        return alternativeScorePointerMap;
    }

    public int[] getFreeSlotState() {
        return freeSlotState;
    }

    public void setFreeSlotState(int[] freeSlotState) {
        this.freeSlotState = freeSlotState;
    }

    public int[] getAlternativeSlotState() {
        return alternativeSlotState;
    }

    public void setAlternativeSlotState(int[] alternativeSlotState) {
        this.alternativeSlotState = alternativeSlotState;
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

    public String getScoreValue(){
        return scoreText.getText();
    }

    public void setScoreText(Text scoreText) {
        this.scoreText = scoreText;
    }


    public Text getAlternativeScoreText() {
        return alternativeScoreText;
    }

    public String getAlternativeScoreValue(){
        return alternativeScoreText.getText();
    }

    public void setAlternativeScoreText(Text alternativeScoreText) {
        this.alternativeScoreText = alternativeScoreText;
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
        alternativeScorePointerMap = new HashMap<>();
        for(int i=2; i<13; i++){
            scorePointerMap.put(i, 1);
            alternativeScorePointerMap.put(i, 1);
        }

    }

}
