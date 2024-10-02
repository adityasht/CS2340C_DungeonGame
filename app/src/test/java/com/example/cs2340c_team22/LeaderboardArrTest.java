package com.example.cs2340c_team22;

import com.example.cs2340c_team22.models.LeaderboardModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardArrTest {
    private LeaderboardModel leaderboardModel = LeaderboardModel.createLeaderboard();
    @Test
    public void arrOnlyKeepsHighest() {

        updateLeaderboard("try 1", 99);
        updateLeaderboard("try 2", 100);
        updateLeaderboard("try 3", 98);
        updateLeaderboard("try 4", 97);
        updateLeaderboard("try 5", 96);
        updateLeaderboard("try 6", 95);
        updateLeaderboard("try 7", 94);
        updateLeaderboard("try 8", 93);
        updateLeaderboard("try 9", 92);
        updateLeaderboard("try 10", 75);
        updateLeaderboard("try 11", 70);
        updateLeaderboard("try 12", 80);

        assertEquals(100, leaderboardModel.getPlayerScore(0));
        assertEquals("try 2", leaderboardModel.getPlayerName(0));
        assertEquals(80, leaderboardModel.getPlayerScore(9));
        assertEquals("try 12", leaderboardModel.getPlayerName(9));
    }

    public void updateLeaderboard(String playerName, int score) {
        leaderboardModel.setFinalscore(score, leaderboardModel.getSize());
        leaderboardModel.setDate(leaderboardModel.getSize());
        leaderboardModel.setPlayerName(playerName, leaderboardModel.getSize());
        leaderboardModel.setSize(leaderboardModel.getSize() + 1);
        leaderboardModel.sort();
    }
}