package com.jalowiec;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RankingRecordFileWriter {

    public void writeRankingListToFile(List<RankingRecord> rankingRecordList) {
        Path path = Paths.get("c://Dice/diceranking.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            for(RankingRecord element : rankingRecordList){
                writer.write("Janek|100|1998-02-02\n");
            }
        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }

    }

}
