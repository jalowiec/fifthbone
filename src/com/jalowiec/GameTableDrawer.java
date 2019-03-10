package com.jalowiec;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class GameTableDrawer {


    private GridPane grid;
    private Button endTurnButton;
    private Button nextPlayerButton;
    private User user;

    public GameTableDrawer(User user) {
        this.user = user;
        this.grid = user.getGridPane();
    }

    public void drawTableHeader(){
        String[] rowValues = {"SUMA", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tableheader");
            grid.add(cellText,  i+5, 0);
        }

        MenuItem menuItem1 = new MenuItem("Option 1");
        MenuItem menuItem2 = new MenuItem("Option 2");
        MenuButton menuButton = new MenuButton("Options", null, menuItem1, menuItem2);
        grid.add(menuButton, 0 , 0, 3, 1);
    }

    public void drawPointsRow(){
        String[] rowValues = {"PUNKTY", "10", "7", "6", "5", "4", "3", "4", "5", "6", "7", "10"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            GridPane.setHalignment(cellText, HPos.CENTER);
            cellText.setId("tablerow");
            grid.add(cellText,  i+5, 1);
        }
    }
    public void drawMinusSection() {
        Text cellText = new Text("-");
        cellText.setId("cellminus");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText, 5, 2, 1,4);
        grid.getChildren().add(cellText);
    }


    public void drawPlusSection( ) {
        Text cellText = new Text("+");
        cellText.setId("cellplus");
        GridPane.setHalignment(cellText, HPos.CENTER);
        GridPane.setConstraints(cellText,5, 6, 1,5);
        grid.getChildren().add(cellText);
    }

    public void drawFifthBoneHeader() {
        Text cellText = new Text(" PIĄTA KOŚĆ");
        cellText.setId("tableheader");
        cellText.getTransforms().add(new Rotate(-90));
        GridPane.setHalignment(cellText, HPos.RIGHT);
        grid.add(cellText,  6, 15);
    }

    public void drawFifthBoneSection() {
        Text cellText = new Text("*");
        for(int i=0; i<3; i++){
            cellText = new Text("*");
            GridPane.setHalignment(cellText, HPos.RIGHT);
            cellText.setId("tableheader");
            grid.add(cellText,  5, 12+i);
        }
    }

    public void drawChosenPair() {
        Text cellFirstText = new Text("PIERWSZA PARA:");
        Text cellSecondText = new Text("DRUGA PARA:");
        cellFirstText.setId("tablerow");
        cellSecondText.setId("tablerow");
        GridPane.setHalignment(cellFirstText, HPos.CENTER);
        GridPane.setHalignment(cellSecondText, HPos.CENTER);
        grid.add(cellFirstText,  7, 20);
        grid.add(cellSecondText,  13, 20);



    }

    public void drawHorizontalLines(){
        for(int i=0; i<5; i++){
            Line line = new Line(0, 0, 780, 0);
            GridPane.setValignment(line, VPos.BOTTOM);
            line.setStrokeWidth(1);
            switch(i){
                case 0 :
                    grid.add(line, 5, 0);
                    break;
                case 1:
                    grid.add(line, 5, 1);
                    break;
                case 2:
                    grid.add(line, 5, 5);
                    break;
                case 3:
                    grid.add(line, 5, 10);
                    break;
                case 4:
                    grid.add(line, 5, 15);
                    break;
            }

        }
        Line vLine = new Line(1,0,1,90);
        vLine.setStrokeWidth(1);
        grid.add(vLine, 15, 13);
    }

    public void drawUserName() {
        Text cellText = new Text(user.getUserName());
        cellText.setId("scorename");
        GridPane.setHalignment(cellText, HPos.CENTER);
        grid.add(cellText, 15, 12, 2, 1);
    }




    public void drawScore(int score) {

        grid.getChildren().remove(user.getUserDataStructures().getScoreText());
        user.getUserDataStructures().setScoreText(new Text(Integer.toString(score)));
        Text cellText = user.getUserDataStructures().getScoreText();
        cellText.setId("score");
        GridPane.setHalignment(cellText, HPos.CENTER);
        if (user.getRoundEnd().isGameEnd()) {
            cellText.setFill(Color.RED);
        }
        grid.add(cellText, 15, 13, 2, 2);
    }

    public void drawUsedSlotsAfterRound(int pairSum, int rowPairPosition) {
        ImageView usedSlotCrossBlack = new ImageView("file:resources/markx_black.png");
        ImageView usedSlotCrossRed = new ImageView("file:resources/markx_red.png");
        if(rowPairPosition < 10) {
            GridPane.setHalignment(usedSlotCrossBlack, HPos.CENTER);
            grid.add(usedSlotCrossBlack, pairSum+4, rowPairPosition);
        }else {
            GridPane.setHalignment(usedSlotCrossRed, HPos.CENTER);
            grid.add(usedSlotCrossRed, pairSum+4, rowPairPosition);
        }
    }

    public void drawChosenFifthDie(int fifthDieValue, int row) {
        Text cellText = new Text(Integer.toString(fifthDieValue));
        GridPane.setHalignment(cellText, HPos.CENTER);
        cellText.setId("fifthdie");
        grid.add(cellText, 6, 12+row);
    }

    public void drawChosenFifthDieSlots(int fifthDiePointer, int fifthDieRow) {
        ImageView usedSlotCrossBlack = new ImageView("file:resources/markx_black.png");
        ImageView usedSlotCrossRed = new ImageView("file:resources/markx_red.png");
        if(fifthDiePointer < 8) {
            GridPane.setHalignment(usedSlotCrossBlack, HPos.CENTER);
            grid.add(usedSlotCrossBlack, fifthDiePointer+6, fifthDieRow+12);
        } else {
            GridPane.setHalignment(usedSlotCrossRed, HPos.CENTER);
            grid.add(usedSlotCrossRed, fifthDiePointer+6, fifthDieRow+12);
        }
    }

    public Button getEndTurnButton() {
        return endTurnButton;
    }

    public Button getNextPlayerButton() {
        return nextPlayerButton;
    }

    public void drawRoundEndButton(RoundEnd roundEnd){
        endTurnButton = new Button("Zakoncz runde");
        endTurnButton.setOnAction(e-> roundEnd.countScoreAfterRound(this));
        if(!user.getPC()) {
            endTurnButton.setDisable(true);
        }
        endTurnButton.setId("button");
        endTurnButton.setMinWidth(150);
        GridPane.setHalignment(endTurnButton, HPos.CENTER);
        grid.add(endTurnButton, 9, 25, 3, 1);
    }

    public void drawNextPlayerButton(Stage mainStage, PlayingUsers playingUsers) {

        nextPlayerButton = new Button("Kolejny zawodnik >>");
        nextPlayerButton.setOnAction(e -> {

            User nextPlayingUser = playingUsers.getNextUserWhoNotFinished(user);
            mainStage.setScene(nextPlayingUser.getUserScene());
            nextPlayingUser.getRoundInitCommon().generateDicesInSlots();
            nextPlayingUser.getRoundInitCommon().clearFreeSlotState();
            user.getGameTableDrawer().getNextPlayerButton().setDisable(true);


            user.getRoundEnd().setRoundEnd(false);
            if(nextPlayingUser.getPC()){
                nextPlayingUser.getGameTableDrawer().getEndTurnButton().setDisable(false);
            }

        });
        nextPlayerButton.setId("button");
        nextPlayerButton.setMinWidth(150);
        nextPlayerButton.setDisable(true);
        GridPane.setHalignment(nextPlayerButton, HPos.CENTER);
        user.getGridPane().add(nextPlayerButton, 12, 25, 3, 1);

    }

    public void drawHelpMark() {
        ImageView helpMark = new ImageView("file:resources/help.png");
        GridPane.setHalignment(helpMark, HPos.CENTER);
        Tooltip helpTooltip = new Tooltip("Warunki ukonczenia rundy:\n 1. Dwie pary musza byc wybrane\n" +
                " 2. Piata kosc musi nalezec do wczesniej wybranych");

        Tooltip.install(helpMark, helpTooltip);
        grid.add(helpMark, 10, 22, 1, 2);
    }


}


