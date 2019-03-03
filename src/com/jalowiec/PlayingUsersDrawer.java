package com.jalowiec;

import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayingUsersDrawer {


    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    List<Node> playingUsersNodeList = new ArrayList<>();



    public void drawPlayingUsers() {
        removePlayingUsersAllNodes();
        List<User> playingUsersList = commonDataStructure.getPlayingUsersList();
        for (User user : playingUsersList) {
            int i = 2;
            for (User element : playingUsersList) {
                Text cellUserNameText = new Text(element.getUserName());
                Text cellUserScoreText = new Text(element.getUserDataStructures().getScoreValue());
                cellUserNameText.setId("leftpanelplayingplayers");
                cellUserScoreText.setId("leftpanelplayingplayers");
                user.getGridPane().add(cellUserNameText, 1, i);
                user.getGridPane().add(cellUserScoreText, 2, i);
                playingUsersNodeList.add(cellUserNameText);
                playingUsersNodeList.add(cellUserScoreText);
                i++;
            }
        }

    }

    private void removePlayingUsersAllNodes(){
        List<User> playingUsersList = commonDataStructure.getPlayingUsersList();
        for(User user: playingUsersList){
            for(Node node : playingUsersNodeList) {
                user.getGridPane().getChildren().remove(node);
            }
        }

    }



}


