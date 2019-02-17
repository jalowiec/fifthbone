package com.jalowiec;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UserGameTableDrawer {

    private Scene scene;
    //TODO - sprawdzic czy nie usunac scene z tej klasy
    private GridPane grid;
    private DiceSlotsManager diceSlotsManager;

    public UserGameTableDrawer(GridPane grid, Scene scene) {
        this.scene = scene;
        this.grid = grid;
        diceSlotsManager = DiceSlotsManager.getInstance(grid);
    }

    public void drawTableHeader(int firstColumnIndex){
        String[] rowValues = {"SUMA", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tableheader");
            grid.add(cellText,  i+firstColumnIndex, 0);
        }
    }

    public void drawPointsRow(int firstColumnIndex){
        String[] rowValues = {"PUNKTY", "10", "7", "6", "5", "4", "3", "4", "5", "6", "7", "10"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tablerow");
            grid.add(cellText,  i+firstColumnIndex, 1);
        }
    }
    public void drawMinusSection(int firstColumnIndex) {
        Text cellText = new Text("-");
        cellText.setId("cellminus");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText, firstColumnIndex, 2, 1,4);
        grid.getChildren().add(cellText);
    }


    public void drawPlusSection(int firstColumnIndex) {
        Text cellText = new Text("+");
        cellText.setId("cellplus");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText,firstColumnIndex, 6, 1,5);
        grid.getChildren().add(cellText);
    }

    public void drawFifthBoneHeader(int firstColumnIndex) {
        Text cellText = new Text("PIĄTA KOŚĆ");
        cellText.setId("tableheader");
        GridPane.setHalignment(cellText, HPos.CENTER);
        grid.add(cellText,  firstColumnIndex, 11);
    }

    public void drawFifthBoneSection(int firstColumnIndex) {
        Text cellText = new Text("*");
        for(int i=0; i<3; i++){
            cellText = new Text("*");
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tableheader");
            grid.add(cellText,  firstColumnIndex, 12+i);
        }
    }

    public void drawChosenPair(int firstColumnIndex) {
        Text cellFirstText = new Text("PIERWSZA PARA:");
        Text cellSecondText = new Text("DRUGA PARA:");
        cellFirstText.setId("tablerow");
        cellSecondText.setId("tablerow");
        GridPane.setHalignment(cellFirstText, HPos.CENTER);
        GridPane.setHalignment(cellSecondText, HPos.CENTER);
        grid.add(cellFirstText,  firstColumnIndex+2, 20);
        grid.add(cellSecondText,  firstColumnIndex+8, 20);



    }

    public void drawHorizontalLines(int firstColumnIndex){
        for(int i=0; i<5; i++){
            Line line = new Line(0, 0, 780, 0);
            GridPane.setValignment(line, VPos.BOTTOM);
            line.setStrokeWidth(1);
            switch(i){
                case 0 :
                    grid.add(line, firstColumnIndex, 0);
                    break;
                case 1:
                    grid.add(line, firstColumnIndex, 1);
                    break;
                case 2:
                    grid.add(line, firstColumnIndex, 5);
                    break;
                case 3:
                    grid.add(line, firstColumnIndex, 10);
                    break;
                case 4:
                    grid.add(line, firstColumnIndex, 14);
                    break;
            }

        }
    }


    public void drawScoreHeader(int firstColumnIndex) {
        Text cellText = new Text("WYNIK");
        cellText.setId("tableheader");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText,10+firstColumnIndex, 11);
        grid.getChildren().add(cellText);
    }


    public void drawScore(int firstColumnIndex, int score) {
        grid.getChildren().remove(diceSlotsManager.getScoreText());
        diceSlotsManager.setScoreText(new Text(Integer.toString(score)));
        Text cellText = diceSlotsManager.getScoreText();
        cellText.setId("score");
        GridPane.setHalignment(cellText, HPos.CENTER);
        grid.add(cellText, 11, 12, 2, 2);
    }

    public void drawUsedSlotsAfterRound(int pairSum, int rowPairPosition) {
        ImageView usedSlotCross = new ImageView("file:resources/markx.png");
        GridPane.setHalignment(usedSlotCross, HPos.CENTER);
        grid.add(usedSlotCross, pairSum, rowPairPosition);
    }

    public void drawChosenFifthDie(int fifthDieValue, int row) {
        Text cellText = new Text(Integer.toString(fifthDieValue));
        GridPane.setHalignment(cellText, HPos.RIGHT);
        cellText.setId("fifthdie");
        grid.add(cellText, 1, 12+row);
    }

    public void drawChosenFifthDieSlots(int fifthDiePointer, int fifthDieRow) {
        ImageView usedSlotCross = new ImageView("file:resources/markx.png");
        GridPane.setHalignment(usedSlotCross, HPos.CENTER);
        grid.add(usedSlotCross, fifthDiePointer+1, fifthDieRow+12);

    }

}


