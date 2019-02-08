package com.jalowiec;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FifthBone extends Application {

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

        userGride.setGridLinesVisible(true);

        Scene userScene = new Scene(userGride, 1600, 900);
        Scene computerScene = new Scene(computerGride, 1000, 900);
        userScene.getStylesheets().add("userstyle.css");
        computerScene.getStylesheets().add("computerstyle.css");
//        userScene.getStylesheets().add(getClass().getResource("userstyle.css").toExternalForm());

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
        userGameTableDrawer.drawScore(firstColumnIndex);
        userGameTableDrawer.drawFifthBoneHeader(firstColumnIndex);
        userGameTableDrawer.drawFifthBoneSection(firstColumnIndex);
        Button userSceneButton = new Button("idz do planszy komputera");
        Button computerSceneButton = new Button("idz do planszy uzytkownika");
        userSceneButton.setOnAction(e-> mainStage.setScene(computerScene));
        computerSceneButton.setOnAction(e-> mainStage.setScene(userScene));
        userGride.add(userSceneButton, 13, 17);
        computerGride.add(computerSceneButton, 14, 17);


        mainStage.setTitle("The Fifth Bone");
        mainStage.setScene(userScene);
        mainStage.show();


    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

