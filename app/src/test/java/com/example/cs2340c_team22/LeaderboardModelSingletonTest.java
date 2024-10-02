package com.example.cs2340c_team22;

import com.example.cs2340c_team22.models.LeaderboardModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardModelSingletonTest {
    @Test
    public void modelIsSingleton() {
        LeaderboardModel leaderboardModel1 = LeaderboardModel.createLeaderboard();
        LeaderboardModel leaderboardModel2 = LeaderboardModel.createLeaderboard();

        assertEquals(leaderboardModel1, leaderboardModel2);

        leaderboardModel1.setPlayerName("Valid name", 1);
        leaderboardModel2.setSize(1);

        assertEquals(leaderboardModel1.getPlayerName(1), leaderboardModel2.getPlayerName(1));
        assertEquals(leaderboardModel1.getSize(), leaderboardModel2.getSize());
    }
}