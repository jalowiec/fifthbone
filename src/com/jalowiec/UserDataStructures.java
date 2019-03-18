package com.jalowiec;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataStructures implements Serializable {

    private int[] freeSlotState = new int[4];
    private int[] alternativeSlotState = new int[4];
    private transient List<ImageView> imageViewList = new ArrayList<>();
    private Die[] diceArray = new Die[5];
    private List<DieSlot> diceSlotsList;
    private List<DieSlot> freeSlotsList;
    private Map<Integer, Integer> scorePointerMap;
    private Map<Integer, Integer> alternativeScorePointerMap;
    private transient Text scoreText;
    private transient Text alternativeScoreText;
    private int scoreValue;
    private int alternativeScoreValue;


    public UserDataStructures() {
        generateSlots();
        initScorePointerMap();
    }


    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public void setImageViewList(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
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

    public String getStringScoreValue(){
        return scoreText.getText();
    }

    public void setScoreText(Text scoreText) {
        this.scoreText = scoreText;
    }

    public String getStringAlternativeScoreValue(){
        return alternativeScoreText.getText();
    }

    public void setAlternativeScoreText(Text alternativeScoreText) {
        this.alternativeScoreText = alternativeScoreText;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getAlternativeScoreValue() {
        return alternativeScoreValue;
    }

    public void setAlternativeScoreValue(int alternativeScoreValue) {
        this.alternativeScoreValue = alternativeScoreValue;
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
