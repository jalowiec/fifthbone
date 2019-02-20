package com.jalowiec;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class UserGameTableDrawer {

    private Scene scene;
    //TODO - sprawdzic czy nie usunac scene z tej klasy
    private GridPane grid;
    private DiceSlotsManager diceSlotsManager;
    private static Button endTurnButton;

    public UserGameTableDrawer(GridPane grid, Scene scene) {
        this.scene = scene;
        this.grid = grid;
        diceSlotsManager = DiceSlotsManager.getInstance(grid, scene);
    }

    public void drawTableHeader(){
        String[] rowValues = {"SUMA", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tableheader");
            grid.add(cellText,  i, 0);
        }
    }

    public void drawPointsRow(){
        String[] rowValues = {"PUNKTY", "10", "7", "6", "5", "4", "3", "4", "5", "6", "7", "10"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tablerow");
            grid.add(cellText,  i, 1);
        }
    }
    public void drawMinusSection() {
        Text cellText = new Text("-");
        cellText.setId("cellminus");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText, 0, 2, 1,4);
        grid.getChildren().add(cellText);
    }


    public void drawPlusSection( ) {
        Text cellText = new Text("+");
        cellText.setId("cellplus");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText,0, 6, 1,5);
        grid.getChildren().add(cellText);
    }

    public void drawFifthBoneHeader() {
        Text cellText = new Text(" PIĄTA KOŚĆ");
        cellText.setId("tableheader");
        cellText.getTransforms().add(new Rotate(-90));
        GridPane.setHalignment(cellText, HPos.RIGHT);
        grid.add(cellText,  1, 15);
    }

    public void drawFifthBoneSection() {
        Text cellText = new Text("*");
        for(int i=0; i<3; i++){
            cellText = new Text("*");
            GridPane.setHalignment(cellText, HPos.RIGHT);
            cellText.setId("tableheader");
            grid.add(cellText,  0, 12+i);
        }
    }

    public void drawChosenPair() {
        Text cellFirstText = new Text("PIERWSZA PARA:");
        Text cellSecondText = new Text("DRUGA PARA:");
        cellFirstText.setId("tablerow");
        cellSecondText.setId("tablerow");
        GridPane.setHalignment(cellFirstText, HPos.CENTER);
        GridPane.setHalignment(cellSecondText, HPos.CENTER);
        grid.add(cellFirstText,  2, 20);
        grid.add(cellSecondText,  8, 20);



    }

    public void drawHorizontalLines(){
        for(int i=0; i<5; i++){
            Line line = new Line(0, 0, 780, 0);
            GridPane.setValignment(line, VPos.BOTTOM);
            line.setStrokeWidth(1);
            switch(i){
                case 0 :
                    grid.add(line, 0, 0);
                    break;
                case 1:
                    grid.add(line, 0, 1);
                    break;
                case 2:
                    grid.add(line, 0, 5);
                    break;
                case 3:
                    grid.add(line, 0, 10);
                    break;
                case 4:
                    grid.add(line, 0, 15);
                    break;
            }

        }
        Line vLine = new Line(0,0,0,90);
        vLine.setStrokeWidth(1);
        grid.add(vLine, 10, 13);
    }


    public void drawScoreHeader() {
        Text cellText = new Text("WYNIK");
        cellText.setId("tableheader");
        GridPane.setHalignment(cellText, HPos.CENTER);
        grid.add(cellText, 10, 12, 2, 1);
    }


    public void drawScore(int score) {
        grid.getChildren().remove(diceSlotsManager.getScoreText());
        diceSlotsManager.setScoreText(new Text(Integer.toString(score)));
        Text cellText = diceSlotsManager.getScoreText();
        cellText.setId("score");
        GridPane.setHalignment(cellText, HPos.CENTER);
        grid.add(cellText, 10, 13, 2, 2);
    }

    public void drawUsedSlotsAfterRound(int pairSum, int rowPairPosition) {
        ImageView usedSlotCrossBlack = new ImageView("file:resources/markx_black.png");
        ImageView usedSlotCrossRed = new ImageView("file:resources/markx_red.png");
        if(rowPairPosition < 10) {
            GridPane.setHalignment(usedSlotCrossBlack, HPos.CENTER);
            grid.add(usedSlotCrossBlack, pairSum - 1, rowPairPosition);
        }else {
            GridPane.setHalignment(usedSlotCrossRed, HPos.CENTER);
            grid.add(usedSlotCrossRed, pairSum - 1, rowPairPosition);
        }
    }

    public void drawChosenFifthDie(int fifthDieValue, int row) {
        Text cellText = new Text(Integer.toString(fifthDieValue));
        GridPane.setHalignment(cellText, HPos.CENTER);
        cellText.setId("fifthdie");
        grid.add(cellText, 1, 12+row);
    }

    public void drawChosenFifthDieSlots(int fifthDiePointer, int fifthDieRow) {
        ImageView usedSlotCrossBlack = new ImageView("file:resources/markx_black.png");
        ImageView usedSlotCrossRed = new ImageView("file:resources/markx_red.png");
        if(fifthDiePointer < 8) {
            GridPane.setHalignment(usedSlotCrossBlack, HPos.CENTER);
            grid.add(usedSlotCrossBlack, fifthDiePointer+1, fifthDieRow+12);
        } else {
            GridPane.setHalignment(usedSlotCrossRed, HPos.CENTER);
            grid.add(usedSlotCrossRed, fifthDiePointer+1, fifthDieRow+12);
        }
    }

    public static Button getEndTurnButton() {
        return endTurnButton;
    }

    public void drawRoundEndButton(EndRoundManager endRoundManager){
        endTurnButton = new Button("zakoncz runde");
        endTurnButton.setOnAction(e-> endRoundManager.countScoreAfterRound(this));
        endTurnButton.setDisable(true);
        grid.add(endTurnButton, 5, 20, 2, 1);
    }

}


