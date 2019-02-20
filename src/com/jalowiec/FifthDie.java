package com.jalowiec;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        userGride.setPadding(new Insets(-80, 50, 50, 50));
        GridPane computerGride = new GridPane();

        userGride.setGridLinesVisible(true);

        Scene userScene = new Scene(userGride, 880, 900);
        Scene computerScene = new Scene(computerGride, 1000, 900);
        userScene.getStylesheets().add("userstyle.css");
        computerScene.getStylesheets().add("computerstyle.css");

        TableProperties tableProperties = new TableProperties(userGride);
        tableProperties.setColumnProperties(120, 60);
        tableProperties.setRowsProperties(30);

        UserGameTableDrawer userGameTableDrawer = new UserGameTableDrawer(userGride, userScene);
        userGameTableDrawer.drawTableHeader();
        userGameTableDrawer.drawPointsRow();
        userGameTableDrawer.drawMinusSection();
        userGameTableDrawer.drawPlusSection();
        userGameTableDrawer.drawScoreHeader();
        userGameTableDrawer.drawScore(0);
        userGameTableDrawer.drawFifthBoneHeader();
        userGameTableDrawer.drawFifthBoneSection();
        userGameTableDrawer.drawChosenPair();
        userGameTableDrawer.drawHorizontalLines();

        DiceSlotsManager diceSlotsManager = DiceSlotsManager.getInstance(userGride, userScene);
        diceSlotsManager.generateDice();
        diceSlotsManager.generateSlots();
        diceSlotsManager.generateDicesInSlots();

        EndRoundManager endRoundManager = new EndRoundManager(userGride, userScene);
        userGameTableDrawer.drawRoundEndButton(endRoundManager);


        Button userSceneButton = new Button("idz do planszy komputera");
        Button computerSceneButton = new Button("idz do planszy uzytkownika");
        userSceneButton.setOnAction(e-> mainStage.setScene(computerScene));
        computerSceneButton.setOnAction(e-> mainStage.setScene(userScene));
        userGride.add(userSceneButton, 8, 25, 3, 1);
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

