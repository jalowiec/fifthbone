package com.jalowiec;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class SceneUserGameTable {

    Stage mainStage;
    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    List<User> usersInTheGame = commonDataStructure.getPlayersInTheGame();

    public SceneUserGameTable(Stage mainStage) {
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

            user.setUserScene(new Scene(user.getGridPane(), 1210, 900));
            user.getUserScene().getStylesheets().add("userstyle.css");

            GameTableProperties gameTableProperties = new GameTableProperties(user);
            gameTableProperties.setLeftPanelProperties(30, 110, 120, 80);
            gameTableProperties.setColumnProperties(120, 60);
            gameTableProperties.setRowsProperties(30);

            commonDataStructure.generateDice();
            user.setRoundEnd(new RoundEnd(user));
            user.getRoundEnd().setRoundEnd(false);

            user.setGameTableDrawer(new GameTableDrawer(user));
            GameTableDrawer gameTableDrawer = user.getGameTableDrawer();
            gameTableDrawer.drawTableHeader();
            gameTableDrawer.drawPointsRow();
            gameTableDrawer.drawMinusSection();
            gameTableDrawer.drawPlusSection();
            gameTableDrawer.drawUserName();
            gameTableDrawer.drawScore(0);
            gameTableDrawer.setAlternativeScore(0);
            gameTableDrawer.drawFifthBoneHeader();
            gameTableDrawer.drawFifthBoneSection();
            gameTableDrawer.drawChosenPair();
            gameTableDrawer.drawHorizontalLines();

            gameTableDrawer.drawRoundEndButton(user.getRoundEnd());
            gameTableDrawer.drawNextPlayerButton(mainStage, commonDataStructure.getPlayingUsers());

            user.setRoundInitCommon(new RoundInitCommon(user));
            user.setRoundProccesorUser(new RoundProccesorUser(user));

            if (!user.getPC()) {
                gameTableDrawer.drawHelpMark();
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
