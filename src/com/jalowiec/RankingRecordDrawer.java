package com.jalowiec;

import javafx.scene.text.Text;

public class RankingRecordDrawer {


    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    RankingFileReader rankingFileReader;



    public RankingRecordDrawer(RankingFileReader rankingFileReader) {
        this.rankingFileReader = rankingFileReader;


    }

    public void drawRankingRecord(){


        for(User user: commonDataStructure.getPlayingUsersList()){

            int i = 0;

             for(RankingRecord rankingRecord : rankingFileReader.getRankingList()){

                Text cellTextName = new Text(rankingRecord.getUserName());
                Text cellTextScore = new Text(rankingRecord.getPointsToString());
                user.getGridPane().add(cellTextName, 1, 14+i );
                user.getGridPane().add(cellTextScore, 2, 14+i );
                i++;
            }
        }

    }

}


