package com.jalowiec;

import javafx.application.Application;
import javafx.scene.Scene;
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
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 1600, 900);
        scene.getStylesheets().add("style.css");

        TableProperties tableProperties = new TableProperties(grid);
        tableProperties.setColumnProperties(120, 40);
        tableProperties.setRowsProperties(50);

        UserGameTableDrawer userGameTableDrawer = new UserGameTableDrawer(grid, scene);
        userGameTableDrawer.drawFirstRow();
        userGameTableDrawer.drawSecondRow();


        mainStage.setTitle("The Fifth Bone");
        mainStage.setScene(scene);
        mainStage.show();


    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

