package com.jalowiec;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneMenuStart {


    private Stage mainStage;
    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();

    public SceneMenuStart(Stage mainStage) {
        this.mainStage = mainStage;
        showScene();
    }

    public void showScene(){
        VBox vBox = new VBox(40);

        Button newGameButton = new Button("Nowa gra");
        newGameButton.setMinWidth(150);
        newGameButton.setId("menustartbutton");
        newGameButton.setCursor(Cursor.CLOSED_HAND);
        newGameButton.setOnAction(e-> new SceneUsersInGame(mainStage));

        Button usersGameButton = new Button("Uzytkownicy");
        usersGameButton.setMinWidth(150);
        usersGameButton.setId("menustartbutton");
        usersGameButton.setCursor(Cursor.HAND);
        usersGameButton.setOnAction(e-> System.exit(0));


        Button loadGameButton = new Button("Wczytaj gre");
        loadGameButton.setMinWidth(150);
        loadGameButton.setId("menustartbutton");
        loadGameButton.setCursor(Cursor.CLOSED_HAND);

        Button endGameButton = new Button("Zakoncz");
        endGameButton.setMinWidth(150);
        endGameButton.setId("menustartbutton");
        endGameButton.setCursor(Cursor.CLOSED_HAND);
        endGameButton.setOnAction(e->System.exit(0));


        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(newGameButton, usersGameButton, loadGameButton, endGameButton);
        Scene mainMenuScene = new Scene(vBox, 400, 600);
        mainMenuScene.getStylesheets().add("scenemenustart.css");

        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(mainMenuScene);
        mainStage.show();
    }

}
