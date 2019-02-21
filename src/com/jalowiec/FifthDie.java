package com.jalowiec;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class FifthDie extends Application {

    private List<User> plaingUsers = new ArrayList<>();
    private List<Scene> usersScenes = new ArrayList<>();

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        plaingUsers.add(new User("Lukasz", false));
        plaingUsers.add(new User("PC", true));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(-10, 50, 50, 50));
        gridPane.setGridLinesVisible(false);

        for(User user : plaingUsers){

          //  user.getUserScene() = new Scene(gridPane, 880, 900);
        }

       Scene userScene = new Scene(gridPane, 880, 900);
    //    Scene computerScene = new Scene(computerGride, 1000, 900);
        userScene.getStylesheets().add("userstyle.css");
     //   computerScene.getStylesheets().add("computerstyle.css");

        TableProperties tableProperties = new TableProperties(gridPane);
        tableProperties.setColumnProperties(120, 60);
        tableProperties.setRowsProperties(30);

        UserGameTableDrawer userGameTableDrawer = new UserGameTableDrawer(gridPane, userScene);
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

        DiceSlotsManager diceSlotsManager = DiceSlotsManager.getInstance(gridPane, userScene);
        diceSlotsManager.generateDice();
        diceSlotsManager.generateSlots();
        diceSlotsManager.generateDicesInSlots();

        EndRoundManager endRoundManager = new EndRoundManager(gridPane, userScene);
        userGameTableDrawer.drawRoundEndButton(endRoundManager);
        userGameTableDrawer.drawHelpMark();



        Button userSceneButton = new Button("idz do planszy komputera");
        Button computerSceneButton = new Button("idz do planszy uzytkownika");
    //    userSceneButton.setOnAction(e-> mainStage.setScene(computerScene));
        computerSceneButton.setOnAction(e-> mainStage.setScene(userScene));
        gridPane.add(userSceneButton, 8, 25, 3, 1);
      //  computerGride.add(computerSceneButton, 14, 17);

        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(userScene);
        mainStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

