package com.jalowiec;

import java.util.Date;

public class RankingRecord implements Comparable<RankingRecord> {
    private String userName;
    private int points;
    private Date dateOfResult;

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

    @Override
    public int compareTo(RankingRecord o) {
        if (this.points > o.points) {
            return 1;
        } else if (this.points < o.points) {
            return -1;
        } else return 0;
    }
}