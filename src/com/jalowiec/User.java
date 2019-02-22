package com.jalowiec;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class User {

    private String userName;
    private Boolean isPC;
    private Scene userScene;
    private GridPane gridPane;
    private String styleCssFile;
    private UserDataStructures userDataStructures;


    public User(String userName, Boolean isPC) {
        this.userName = userName;
        this.isPC = isPC;
        userDataStructures = new UserDataStructures();
    }

    public Boolean getPC() {
        return isPC;
    }

    public void setUserScene(Scene userScene) {
        this.userScene = userScene;
    }

    public Scene getUserScene() {
        return userScene;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public String getStyleCssFile() {
        return styleCssFile;
    }

    public void setStyleCssFile(String styleCssFile) {
        this.styleCssFile = styleCssFile;
    }
}
