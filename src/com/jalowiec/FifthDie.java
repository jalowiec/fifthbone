package com.jalowiec;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class FifthDie extends Application {



    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage mainStage) throws Exception {

        PlayingUsers plaingUsers = new PlayingUsers();
        plaingUsers.addUsers();


        for (User user : plaingUsers.getPlaingUsersList()) {
            user.setGridPane(new GridPane());
            user.getGridPane().setPadding(new Insets(50, 50, 50, 50));
            user.getGridPane().setGridLinesVisible(false);

            user.setUserScene(new Scene(user.getGridPane(), 880, 900));
            if(!user.getPC()){
                user.getUserScene().getStylesheets().add("userstyle.css");
            }else{
                user.getUserScene().getStylesheets().add("computerstyle.css");
            }

            TableProperties tableProperties = new TableProperties(user);
            tableProperties.setColumnProperties(120, 60);
            tableProperties.setRowsProperties(30);

            DiceSlotsOperation diceSlotsOperation = DiceSlotsOperation.getInstance();
            diceSlotsOperation.generateDice();
            user.setRoundEnd(new RoundEnd(user));
            user.getRoundEnd().setRoundEnd(false);

            user.setTableDrawer(new TableDrawer(user));
            TableDrawer tableDrawer = user.getTableDrawer();
            tableDrawer.drawTableHeader();
            tableDrawer.drawPointsRow();
            tableDrawer.drawMinusSection();
            tableDrawer.drawPlusSection();
            tableDrawer.drawUserName();
            //tableDrawer.drawScoreHeader();
            tableDrawer.drawScore(0);
            tableDrawer.drawFifthBoneHeader();
            tableDrawer.drawFifthBoneSection();
            tableDrawer.drawChosenPair();
            tableDrawer.drawHorizontalLines();



            tableDrawer.drawRoundEndButton(user.getRoundEnd());
            tableDrawer.drawNextPlayerButton(mainStage, plaingUsers);

            user.setRoundInitCommon(new RoundInitCommon(user));
            user.setRoundProccesorUser(new RoundProccesorUser(user));

            if(!user.getPC()) {
                tableDrawer.drawHelpMark();
            }

        }

        User firstUser = plaingUsers.getPlaingUsersList().get(0);
        firstUser.getRoundInitCommon().generateDicesInSlots();
        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(firstUser.getUserScene());
        mainStage.show();
    }





    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

