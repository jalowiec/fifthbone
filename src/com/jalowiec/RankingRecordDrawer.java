package com.jalowiec;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingRecordDrawer implements Serializable {


    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    transient private List<Node> rankingRecordNodesList = new ArrayList<>();

    public void drawRankingRecord() {
        removeRanking();
        for (User user : commonDataStructure.getPlayersInTheGame()) {

            int i = 0;

            for (RankingRecord rankingRecord : commonDataStructure.getRankingList()) {

                Text cellTextName = new Text(rankingRecord.getUserName());
                Text cellTextScore = new Text(rankingRecord.getPointsToString());
                Text cellTextDate = new Text(rankingRecord.getDateOfResult());

                cellTextName.setId("leftpanelrecords");
                user.getGridPane().add(cellTextName, 1, 15 + i);
                rankingRecordNodesList.add(cellTextName);

                cellTextScore.setId("leftpanelrecords");
                GridPane.setHalignment(cellTextScore, HPos.CENTER);
                user.getGridPane().add(cellTextScore, 3, 15 + i);
                rankingRecordNodesList.add(cellTextScore);

                cellTextDate.setId("leftpanelrecords");
                GridPane.setHalignment(cellTextDate, HPos.CENTER);
                user.getGridPane().add(cellTextDate, 2, 15 + i);
                rankingRecordNodesList.add(cellTextDate);
                i++;
            }
        }

    }

    public boolean isShouldBeAddedToRanking(RankingRecord rankingRecord) {
        List<RankingRecord> rankingList = commonDataStructure.getRankingList();
        if (rankingList.size() < 10 || rankingRecord.compareTo(rankingList.get(9)) == 1) {
            return true;
        }
        return false;
    }

    public void addToRanking(RankingRecord rankingRecord) {
        List<RankingRecord> rankingList = commonDataStructure.getRankingList();
        if(rankingList.size()<10){
            rankingList.add(rankingRecord);
        }else {
            rankingList.set(rankingList.size()-1, rankingRecord);
        }
        Collections.sort(rankingList, Collections.reverseOrder());
        commonDataStructure.getRankingRecordFileWriter().writeRankingListToFile(rankingList);
        drawRankingRecord();

    }


    private void removeRanking() {
        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        for (User user : playingUsersList) {
            for (Node node : rankingRecordNodesList) {
                user.getGridPane().getChildren().remove(node);
            }
        }

    }

}


