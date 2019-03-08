package com.jalowiec;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class SceneUser {

    Stage mainStage;
    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    List<User> usersInTheGame = commonDataStructure.getPlayersInTheGame();

    public SceneUser(Stage mainStage) {
        this.mainStage = mainStage;
        generateScenesForUsers();
        showSceneForFirstUser();
    }

    public void showSceneForFirstUser() {
        mainStage.setScene(usersInTheGame.get(0).getUserScene());
    }

    public void generateScenesForUsers() {

        for (User user : usersInTheGame) {

            user.setGridPane(new GridPane());
            user.getGridPane().setPadding(new Insets(30, 20, 30, 20));
            user.getGridPane().setGridLinesVisible(false);

            user.setUserScene(new Scene(user.getGridPane(), 1180, 900));
            user.getUserScene().getStylesheets().add("userstyle.css");

            TableProperties tableProperties = new TableProperties(user);
            tableProperties.setLeftPanelProperties(30, 100, 90, 90);
            tableProperties.setColumnProperties(120, 60);
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
            tableDrawer.drawNextPlayerButton(mainStage, commonDataStructure.getPlayingUsers());

            user.setRoundInitCommon(new RoundInitCommon(user));
            user.setRoundProccesorUser(new RoundProccesorUser(user));

            if (!user.getPC()) {
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

    }


}
