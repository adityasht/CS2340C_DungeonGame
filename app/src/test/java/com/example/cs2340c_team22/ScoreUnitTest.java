package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.PlayerModel;

public class ScoreUnitTest {
    @Test
    public void testScoreRange() {
        PlayerModel player = PlayerModel.getPlayer();
        player.setPlayerScore(100);
        assertEquals(100, player.getPlayerScore());

        player.setPlayerScore(0);
        assertEquals(0, player.getPlayerScore());

        player.setPlayerScore(-50);
        assertEquals(0, player.getPlayerScore());
    }
}
