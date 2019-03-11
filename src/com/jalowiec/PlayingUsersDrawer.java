package com.jalowiec;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayingUsersDrawer {


    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    List<Node> playingUsersNameList = new ArrayList<>();
    List<Node> playingUsersScoreList = new ArrayList<>();


    public void drawPlayingUsersInit() {
        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        int j = 4;
        for (User user : playingUsersList) {
            int i = 4;
            for (User element : playingUsersList) {
                Text cellUserNameText = new Text(element.getUserName());
                Text cellUserScoreText = new Text(element.getUserDataStructures().getScoreValue());
                cellUserNameText.setId("leftpanelplayingplayers");
                cellUserScoreText.setId("leftpanelscore");
                GridPane.setHalignment(cellUserScoreText, HPos.CENTER);
                user.getGridPane().add(cellUserNameText, 1, i);
                user.getGridPane().add(cellUserScoreText, 2, i, 2, 1);
                playingUsersNameList.add(cellUserNameText);
                playingUsersScoreList.add(cellUserScoreText);

                if(element.getPC()){
                    ImageView pcUser = new ImageView("file:resources/pc.png");
                    GridPane.setHalignment(pcUser, HPos.CENTER);
                    user.getGridPane().add(pcUser, 0, i);
                }

                i++;



            }
            ImageView diceUserMove = new ImageView("file:resources/dice_user_move.png");
            GridPane.setHalignment(diceUserMove, HPos.CENTER);
            user.getGridPane().add(diceUserMove, 2, j);
            j++;

        }

    }


    public void drawPlayingUsersScores() {
        removePlayingUsersScores();
        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        int j = 2;
        for (User user : playingUsersList) {
            int i = 4;
            for (User element : playingUsersList) {
                Text cellUserScoreText = new Text(element.getUserDataStructures().getScoreValue());
                cellUserScoreText.setId("leftpanelscore");
                GridPane.setHalignment(cellUserScoreText, HPos.CENTER);
                if (element.getRoundEnd().isGameEnd()) {
                    cellUserScoreText.setFill(Color.RED);
                }
                user.getGridPane().add(cellUserScoreText, 2, i, 2, 1);

                if (!user.getPC() && (!element.getPC()) && element.equals(user)) {
                    Text cellAlternativeScoreText = new Text("(" + element.getUserDataStructures().getAlternativeScoreValue() + ")");
                    cellAlternativeScoreText.setId("leftpanelalternaivescore");
                    GridPane.setHalignment(cellAlternativeScoreText, HPos.CENTER);
                    user.getGridPane().add(cellAlternativeScoreText, 3, i);
                    playingUsersScoreList.add(cellAlternativeScoreText);

                }

                playingUsersScoreList.add(cellUserScoreText);
                i++;

            }
        }

    }


    private void removePlayingUsersScores() {
        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        for (User user : playingUsersList) {
            for (Node node : playingUsersScoreList) {
                user.getGridPane().getChildren().remove(node);
            }
        }

    }


}


