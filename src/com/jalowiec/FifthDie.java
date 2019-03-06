package com.jalowiec;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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

        CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();



        for (User user : commonDataStructure.getPlayersInTheGame()) {
            user.setGridPane(new GridPane());
            user.getGridPane().setPadding(new Insets(30, 20, 30, 20));
            user.getGridPane().setGridLinesVisible(false);

            user.setUserScene(new Scene(user.getGridPane(), 1160, 900));
            user.getUserScene().getStylesheets().add("userstyle.css");

            TableProperties tableProperties = new TableProperties(user);
            tableProperties.setLeftPanelProperties(20, 200, 40);
            tableProperties.setColumnProperties(120,  60);
            tableProperties.setRowsProperties(30);

            commonDataStructure.generateDice();
            user.setRoundEnd(new RoundEnd(user));
            user.getRoundEnd().setRoundEnd(false);

            user.setTableDrawer(new TableDrawer(user));
            TableDrawer tableDrawer = user.getTableDrawer();
            tableDrawer.drawTableHeader();
            tableDrawer.drawPointsRow();
            tableDrawer.drawMinusSection();
            tableDrawer.drawPlusSection();
            tableDrawer.drawUserName();
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


        LeftPanelDrawer leftPanelDrawer = new LeftPanelDrawer();
        commonDataStructure.setLeftPanelDrawer(leftPanelDrawer);
        leftPanelDrawer.drawLeftPanelFrame();

        commonDataStructure.getRankingFromTheFile();

        leftPanelDrawer.drawRankingRecordInPanel();
        leftPanelDrawer.drawInitPlayingUsersInPanel();


        User firstUser = commonDataStructure.getPlayersInTheGame().get(0);
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

