package com.jalowiec;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class User {

    private String userName;
    private Boolean isPC;
    private Scene userScene;
    private GridPane gridPane;
    private TableDrawer tableDrawer;
    private String styleCssFile;
    private UserDataStructures userDataStructures;
    private RoundInitCommon roundInitCommon;
    private RoundProccesorUser roundProccesorUser;
    private RoundEnd roundEnd;


    public User(String userName, Boolean isPC) {
        this.userName = userName;
        this.isPC = isPC;
        userDataStructures = new UserDataStructures();
    }

    public String getUserName() {
        return userName;
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

    public TableDrawer getTableDrawer() {
        return tableDrawer;
    }

    public void setTableDrawer(TableDrawer tableDrawer) {
        this.tableDrawer = tableDrawer;
    }

    public void setRoundInitCommon(RoundInitCommon roundInitCommon) {
        this.roundInitCommon = roundInitCommon;
    }

    public RoundInitCommon getRoundInitCommon() {
        return roundInitCommon;
    }

    public RoundProccesorUser getRoundProccesorUser() {
        return roundProccesorUser;
    }

    public void setRoundProccesorUser(RoundProccesorUser roundProccesorUser) {
        this.roundProccesorUser = roundProccesorUser;
    }

    public RoundEnd getRoundEnd() {
        return roundEnd;
    }

    public void setRoundEnd(RoundEnd roundEnd) {
        this.roundEnd = roundEnd;
    }

    public UserDataStructures getUserDataStructures() {
        return userDataStructures;
    }

    public String getStyleCssFile() {
        return styleCssFile;
    }

    public void setStyleCssFile(String styleCssFile) {
        this.styleCssFile = styleCssFile;
    }
}
