package com.jalowiec;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;

public class SceneMenuStart implements Serializable {


    transient private Stage mainStage;
    private SceneUsersInGame sceneUsersInGame;
    private SceneUsersManager sceneUsersManager;
    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();

    public SceneMenuStart(Stage mainStage) {
        this.mainStage = mainStage;
        sceneUsersInGame = new SceneUsersInGame(mainStage);
        sceneUsersManager = new SceneUsersManager(mainStage);
        commonDataStructure.setSceneMenuStart(this);
        showScene();
    }

    public void showScene(){
        VBox vBox = new VBox(40);

        Button newGameButton = new Button("Nowa gra");
        newGameButton.setMinWidth(150);
        newGameButton.setId("menustartbutton");
        newGameButton.setCursor(Cursor.HAND);
        newGameButton.setOnAction(e-> sceneUsersInGame.showScene());

        Button usersGameButton = new Button("Uzytkownicy");
        usersGameButton.setMinWidth(150);
        usersGameButton.setId("menustartbutton");
        usersGameButton.setCursor(Cursor.HAND);
        usersGameButton.setOnAction(e-> sceneUsersManager.showScene());

        Button loadGameButton = new Button("Wczytaj gre");
        loadGameButton.setMinWidth(150);
        loadGameButton.setId("menustartbutton");
        loadGameButton.setCursor(Cursor.HAND);
        File loadFile = new File("fifthdie.bin");
        if(!loadFile.exists()){
            loadGameButton.setDisable(true);
        }
        loadGameButton.setOnAction(e-> {
            GameLoader gameLoader = new GameLoader();
            gameLoader.readGame(commonDataStructure);
        });

        Button endGameButton = new Button("Zakoncz");
        endGameButton.setMinWidth(150);
        endGameButton.setId("menustartbutton");
        endGameButton.setCursor(Cursor.HAND);
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
