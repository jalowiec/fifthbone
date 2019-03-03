package com.jalowiec;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class LeftPanelDrawer {

    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    int refRowIndex = 13;
    RankingRecordDrawer rankingRecordDrawer;
    PlayingUsersDrawer playingUsersDrawer;


    public LeftPanelDrawer() {
        rankingRecordDrawer = new RankingRecordDrawer(new RankingFileReader());
        playingUsersDrawer = new PlayingUsersDrawer();
    }


    public void drawLeftPanelFrame(){

        int strokeWidth = 3;
        int strokeBoldWidth = 30;
        Color strokeColor = Color.rgb(221, 221, 221);

        for(User user : commonDataStructure.getPlayingUsersList()) {
            Line hLeftLine = new Line(0, 0, 0, 805);
            GridPane.setHalignment(hLeftLine, HPos.LEFT);
            hLeftLine.setStrokeWidth(strokeWidth);
            hLeftLine.setStroke(strokeColor);
            user.getGridPane().add(hLeftLine, 0, 13);

            Line hRightLine = new Line(0, 0, 0, 805);
            GridPane.setHalignment(hRightLine, HPos.RIGHT);
            hRightLine.setStrokeWidth(strokeWidth);
            hRightLine.setStroke(strokeColor);
            user.getGridPane().add(hRightLine, 3, 13);

            Line vTopLine = new Line(0, 0, 310, 0);
            GridPane.setValignment(vTopLine, VPos.CENTER);
            vTopLine.setStrokeWidth(strokeBoldWidth);
            vTopLine.setStroke(strokeColor);
            user.getGridPane().add(vTopLine, 0, 0);

            Line vMiddleLine = new Line(0, 0, 310, 0);
            GridPane.setValignment(vMiddleLine, VPos.CENTER);
            vMiddleLine.setStrokeWidth(strokeBoldWidth);
            vMiddleLine.setStroke(strokeColor);
            user.getGridPane().add(vMiddleLine, 0, refRowIndex);

            Line vBottomLine = new Line(0, 0, 335, 0);
            GridPane.setValignment(vBottomLine, VPos.TOP);
            vBottomLine.setStrokeWidth(strokeWidth);
            vBottomLine.setStroke(strokeColor);
            user.getGridPane().add(vBottomLine, 0, 28);


            Label playersLabel = new Label("ZAWODNICY");
            GridPane.setHalignment(playersLabel,HPos.CENTER);
            playersLabel.setFont(new Font(20));

            Label recordsLabel = new Label("REKORDY");
            GridPane.setHalignment(recordsLabel,HPos.CENTER);
            recordsLabel.setFont(new Font( 20));

            user.getGridPane().add(playersLabel, 0, 0, 3, 1);
            user.getGridPane().add(recordsLabel, 0, 13, 3, 1);


        }

    }

    public void drawRankingRecordInPanel(){
        rankingRecordDrawer.drawRankingRecord();
    }

    public void drawPlayingUsersInPanel(){
        playingUsersDrawer.drawPlayingUsers();
    }


}
