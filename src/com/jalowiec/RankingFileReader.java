package com.jalowiec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class RankingFileReader {

    List<RankingRecord> rankingList = new ArrayList<>();

    public RankingFileReader() {
        readRankingListFromFile();
    }

    public List<RankingRecord> readRankingListFromFile() {

        File file = new File("c://Dice/diceranking.txt");

        try (BufferedReader b = new BufferedReader(new FileReader(file))) {
            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                List<String> record = Pattern.compile("\\|").splitAsStream(readLine).collect(Collectors.toList());
                RankingRecord rankingRecord = null;
                rankingRecord = new RankingRecord(record.get(0), Integer.parseInt(record.get(1)), new SimpleDateFormat("yyyy-mm-dd").parse(record.get(2)));

                rankingList.add(rankingRecord);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return rankingList;
    }

    public List<RankingRecord> getRankingList() {
        return rankingList;
    }
}
