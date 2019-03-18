package com.jalowiec;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.Serializable;

public class LeftPanelDrawer implements Serializable {

    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    private int refRowIndex = 13;
    private RankingRecordDrawer rankingRecordDrawer;
    private PlayingUsersDrawer playingUsersDrawer;


    public LeftPanelDrawer () {
        rankingRecordDrawer = new RankingRecordDrawer();
        playingUsersDrawer = new PlayingUsersDrawer();
    }

    public RankingRecordDrawer getRankingRecordDrawer() {
        return rankingRecordDrawer;
    }

    public void drawLeftPanelFrame(){

        int strokeWidth = 3;
        int strokeBoldWidth = 30;
        Color strokeColor = Color.rgb(221, 221, 221);

        for(User user : commonDataStructure.getPlayersInTheGame()) {
            Line hLeftLine = new Line(0, 0, 0, 747);
            GridPane.setHalignment(hLeftLine, HPos.LEFT);
            hLeftLine.setStrokeWidth(strokeWidth);
            hLeftLine.setStroke(strokeColor);
            user.getGridPane().add(hLeftLine, 0, 14);

            Line hRightLine = new Line(0, 0, 0, 747);
            GridPane.setHalignment(hRightLine, HPos.RIGHT);
            hRightLine.setStrokeWidth(strokeWidth);
            hRightLine.setStroke(strokeColor);
            user.getGridPane().add(hRightLine, 3, 14);

            Line vTopLine = new Line(0, 0, 308, 0);
            GridPane.setValignment(vTopLine, VPos.CENTER);
            vTopLine.setStrokeWidth(strokeBoldWidth);
            vTopLine.setStroke(strokeColor);
            user.getGridPane().add(vTopLine, 0, 2);

            Line vMiddleLine = new Line(0, 0, 308, 0);
            GridPane.setValignment(vMiddleLine, VPos.CENTER);
            vMiddleLine.setStrokeWidth(strokeBoldWidth);
            vMiddleLine.setStroke(strokeColor);
            user.getGridPane().add(vMiddleLine, 0, refRowIndex);

            Line vBottomLine = new Line(0, 0, 337, 0);
            GridPane.setValignment(vBottomLine, VPos.TOP);
            vBottomLine.setStrokeWidth(strokeWidth);
            vBottomLine.setStroke(strokeColor);
            user.getGridPane().add(vBottomLine, 0, 28);


            Label playersLabel = new Label("ZAWODNICY");
            GridPane.setHalignment(playersLabel,HPos.CENTER);
            playersLabel.setFont(new Font(20));

            Label recordsLabel = new Label("RANKING");
            GridPane.setHalignment(recordsLabel,HPos.CENTER);
            recordsLabel.setFont(new Font( 20));

            user.getGridPane().add(playersLabel, 0, 2, 4, 1);
            user.getGridPane().add(recordsLabel, 0, 13, 4, 1);


        }

    }



    public void drawInitPlayingUsersInPanel(){
        playingUsersDrawer.drawPlayingUsersInit();
    }

    public void drawPlayingUsersScoreInPanel(){
        playingUsersDrawer.drawPlayingUsersScores();
    }

    public void drawRankingRecordInPanel(){
        rankingRecordDrawer.drawRankingRecord();
    }


}
