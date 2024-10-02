package com.example.cs2340c_team22;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team22.models.PlayerModel;

import org.junit.Test;

public class PlayerDeathTests {
    private final PlayerModel player = PlayerModel.getPlayer();


    @Test
    public void testPlayerDeathUpdate() {
        player.setDeathStatus(false); //reset

        player.setPlayerHealth(100);
        assertFalse(player.getDeathStatus());

        player.setPlayerHealth(0);
        assertTrue(player.getDeathStatus());

        player.setPlayerHealth(-50);
        assertTrue(player.getDeathStatus());
    }

    @Test
    public void testPlayerCannotRevive() {
        player.setDeathStatus(false); //reset

        player.setPlayerHealth(100);
        assertFalse(player.getDeathStatus());

        player.setPlayerHealth(0);
        assertTrue(player.getDeathStatus());

        player.setPlayerHealth(100);
        assertTrue(player.getDeathStatus());
    }

}
