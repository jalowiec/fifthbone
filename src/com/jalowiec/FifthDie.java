package com.jalowiec;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class FifthDie extends Application {

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        GridPane userGride = new GridPane();
        GridPane computerGride = new GridPane();

        userGride.setGridLinesVisible(false);

        Scene userScene = new Scene(userGride, 1600, 900);
        Scene computerScene = new Scene(computerGride, 1000, 900);
        userScene.getStylesheets().add("userstyle.css");
        computerScene.getStylesheets().add("computerstyle.css");

        int firstColumnIndex = 1;

        TableProperties tableProperties = new TableProperties(userGride);
        tableProperties.setColumnProperties(120, 60,  firstColumnIndex);
        tableProperties.setRowsProperties(30);

        UserGameTableDrawer userGameTableDrawer = new UserGameTableDrawer(userGride, userScene);
        userGameTableDrawer.drawTableHeader(firstColumnIndex);
        userGameTableDrawer.drawPointsRow(firstColumnIndex);
        userGameTableDrawer.drawMinusSection(firstColumnIndex);
        userGameTableDrawer.drawPlusSection(firstColumnIndex);
        userGameTableDrawer.drawScoreHeader(firstColumnIndex);
        userGameTableDrawer.drawScore(firstColumnIndex, 0);
        userGameTableDrawer.drawFifthBoneHeader(firstColumnIndex);
        userGameTableDrawer.drawFifthBoneSection(firstColumnIndex);
        userGameTableDrawer.drawChosenPair(firstColumnIndex);
        userGameTableDrawer.drawHorizontalLines(firstColumnIndex);

        DiceSlotsManager diceSlotsManager = DiceSlotsManager.getInstance(userGride);
        diceSlotsManager.generateDice();
        diceSlotsManager.generateSlots();
        diceSlotsManager.generateDicesInSlots();

        ScoreManager scoreManager = new ScoreManager(userGride, userScene);


        Button userSceneButton = new Button("idz do planszy komputera");
        Button endTurnButton = new Button("zakoncz runde");
        Button removeDicesButton = new Button("usun kosci");
        Button computerSceneButton = new Button("idz do planszy uzytkownika");
        userSceneButton.setOnAction(e-> mainStage.setScene(computerScene));
        endTurnButton.setOnAction(e-> scoreManager.countScoreAfterRound());
        removeDicesButton.setOnAction(e->diceSlotsManager.removeAllDiceFromSlots());
        computerSceneButton.setOnAction(e-> mainStage.setScene(userScene));
        userGride.add(userSceneButton, 13, 17);
        userGride.add(endTurnButton, 13, 18);
        userGride.add(removeDicesButton, 15, 18);
        computerGride.add(computerSceneButton, 14, 17);

        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(userScene);
        mainStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

