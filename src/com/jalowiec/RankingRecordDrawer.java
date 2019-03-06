package com.jalowiec;

import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingRecordDrawer {


    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    private List<Node> rankingRecordNodesList = new ArrayList<>();

    public void drawRankingRecord() {
        removeRanking();
        for (User user : commonDataStructure.getPlayersInTheGame()) {

            int i = 0;

            for (RankingRecord rankingRecord : commonDataStructure.getRankingList()) {

                Text cellTextName = new Text(rankingRecord.getUserName());
                Text cellTextScore = new Text(rankingRecord.getPointsToString());
                user.getGridPane().add(cellTextName, 1, 14 + i);
                rankingRecordNodesList.add(cellTextName);
                user.getGridPane().add(cellTextScore, 2, 14 + i);
                rankingRecordNodesList.add(cellTextScore);
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
        rankingList.set(9, rankingRecord);
        Collections.sort(rankingList);
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


