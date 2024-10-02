package com.example.cs2340c_team22.models;

import java.util.*;

public class LeaderboardModel {


    private final int[] finalScore;
    private final String[] playerName;
    private final Date[] date;
    private int size;
    private static final int INITIAL_CAPACITY = 11;
    private static LeaderboardModel leaderboardInstance;

    // default constructor
    private LeaderboardModel() {
        finalScore = new int[INITIAL_CAPACITY];
        playerName = new String[INITIAL_CAPACITY];
        date = new Date[INITIAL_CAPACITY];
        size = 0;
    }

    public static LeaderboardModel createLeaderboard() {
        if (leaderboardInstance == null) {
            synchronized (LeaderboardModel.class) {
                if (leaderboardInstance == null) {
                    leaderboardInstance = new LeaderboardModel();
                }
            }
        }
        return leaderboardInstance;
    }
    // return player name
    public String getPlayerName(int idx) {
        if (idx > 9 || idx < 0) {
            throw new IndexOutOfBoundsException("Leaderboard only holds 10 values!");
        }
        return playerName[idx];
    }

    // setter method for player name
    public void setPlayerName(String name, int idx) {
        if (idx >= 10) {
            idx = 10;
        }
        playerName[idx] = name;
    }

    // getter method for final score
    public int getPlayerScore(int idx) {
        if (idx > 9 || idx < 0) {
            throw new IndexOutOfBoundsException("Leaderboard only holds 10 values!");
        }
        return finalScore[idx];
    }

    // setter method for final Score
    public void setFinalscore(int score, int idx) {
        if (idx >= 10) {
            idx = 10;
        }
        finalScore[idx] = Math.max(score, 0);
    }

    // getter for date
    public Date getDate(int idx) {
        if (idx > 9 || idx < 0) {
            throw new IndexOutOfBoundsException("Leaderboard only holds 10 values!");
        }
        return date[idx];
    }

    // setter for date
    public void setDate(int idx) {
        if (idx >= 10) {
            idx = 10;
        }
        date[idx] = new Date();
    }

    // getter for size
    public int getSize() {
        return size;
    }

    // setter for size
    public void setSize(int siz) {
        size = siz;
    }

    //sorts all arrays in descending order
    public void sort() {
        boolean swapped = true;
        int j = 0;
        int tmp;
        String tmp2;
        Date tmp3;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < size - j; i++) {
                if (finalScore[i] < finalScore[i + 1]) {
                    tmp = finalScore[i];
                    finalScore[i] = finalScore[i + 1];
                    finalScore[i + 1] = tmp;
                    tmp2 = playerName[i];
                    playerName[i] = playerName[i + 1];
                    playerName[i + 1] = tmp2;
                    tmp3 = date[i];
                    date[i] = date[i + 1];
                    date[i + 1] = tmp3;
                    swapped = true;
                }
            }
        }
        if (size >= 11) {
            size = 10;
            finalScore[10] = 0;
            date[10] = null;
            playerName[10] = null;
        }
    }
}

