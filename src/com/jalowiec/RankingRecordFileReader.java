package com.jalowiec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class RankingRecordFileReader implements Serializable {


    public List<RankingRecord> readRankingListFromFile() {

        List<RankingRecord> rankingList = new ArrayList<>();

        File file = new File("c://Dice/diceranking.txt");

        try (BufferedReader b = new BufferedReader(new FileReader(file))) {
            String readLine = "";

            while ((readLine = b.readLine()) != null ) {
                List<String> record = Pattern.compile("\\|").splitAsStream(readLine).collect(Collectors.toList());
                RankingRecord rankingRecord = null;
                rankingRecord = new RankingRecord(record.get(0), Integer.parseInt(record.get(1)), record.get(2));

                rankingList.add(rankingRecord);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rankingList;
    }


}
