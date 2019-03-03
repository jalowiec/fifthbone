package com.jalowiec;

import java.util.Date;

public class RankingRecord {
    String userName;
    int points;
    Date dateOfResult;

    public RankingRecord(String userName, int points, Date dateOfResult) {
        this.userName = userName;
        this.points = points;
        this.dateOfResult = dateOfResult;
    }


    public String getUserName() {
        return userName;
    }

    public String getPointsToString() {
        return Integer.toString(points);
    }

    public Date getDateOfResult() {
        return dateOfResult;
    }
}
