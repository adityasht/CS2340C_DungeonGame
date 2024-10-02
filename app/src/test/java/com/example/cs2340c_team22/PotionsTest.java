package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.FreezeEnemiesPotionDecorator;
import com.example.cs2340c_team22.models.GamePowerUp;
import com.example.cs2340c_team22.models.HealthPotionDecorator;
import com.example.cs2340c_team22.models.ScorePotionDecorator;

public class PotionsTest {

    @Test
    public void healthPotionSetUpTest() {
        GamePowerUp powerUp = new GamePowerUp();
        HealthPotionDecorator healthPotion = new HealthPotionDecorator(powerUp);
        healthPotion.setUp();
        assertEquals(powerUp.getX(), 5);
        assertEquals(powerUp.getY(), 8);
        assertEquals(powerUp.getHasBeenUsed(), false);
    }

    @Test
    public void freezePotionSetUpTest() {
        GamePowerUp powerUp = new GamePowerUp();
        FreezeEnemiesPotionDecorator freezePotion = new FreezeEnemiesPotionDecorator(powerUp);
        freezePotion.setUp();
        assertEquals(powerUp.getX(), 10);
        assertEquals(powerUp.getY(), 1);
        assertEquals(powerUp.getHasBeenUsed(), false);
    }

    @Test
    public void scorePotionSetUpTest() {
        GamePowerUp powerUp = new GamePowerUp();
        ScorePotionDecorator scorePotion = new ScorePotionDecorator(powerUp);
        scorePotion.setUp();
        assertEquals(powerUp.getX(), 1);
        assertEquals(powerUp.getY(), 12);
        assertEquals(powerUp.getHasBeenUsed(), false);
    }
}
