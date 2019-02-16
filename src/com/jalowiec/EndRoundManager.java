package com.jalowiec;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class EndRoundManager {
    private GridPane gridPane;
    private Scene scene;
    private ScoreManager scoreManager;
    private DiceSlotsManager diceSlotsManager;
    private UserGameTableDrawer userGameTableDrawer;

    public EndRoundManager(GridPane gridPane, Scene scene) {
        this.gridPane = gridPane;
        this.scene = scene;
        scoreManager = new ScoreManager(gridPane, scene);
        diceSlotsManager = DiceSlotsManager.getInstance(gridPane);
        userGameTableDrawer = new UserGameTableDrawer(gridPane, scene);
    }


    public void endRound(){
        scoreManager.countScoreAfterRound();


    }



}
