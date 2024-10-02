package com.example.cs2340c_team22;

import static org.junit.Assert.assertEquals;

import com.example.cs2340c_team22.models.FreezeEnemiesPotionDecorator;
import com.example.cs2340c_team22.models.GamePowerUp;
import com.example.cs2340c_team22.models.HealthPotionDecorator;
import com.example.cs2340c_team22.models.ScorePotionDecorator;

import org.junit.Test;

public class PotionStrengthTest {

    @Test
    public void healthPotionSetUpTest() {
        GamePowerUp powerUp = new GamePowerUp();
        HealthPotionDecorator healthPotion = new HealthPotionDecorator(powerUp);
        healthPotion.setUp();
        int easy = 0;
        int medium = 1;
        int hard = 2;
        assertEquals(healthPotion.getStrength(easy), 15);
        assertEquals(healthPotion.getStrength(medium), 10);
        assertEquals(healthPotion.getStrength(hard), 5);
    }


    @Test
    public void scorePotionSetUpTest() {
        GamePowerUp powerUp = new GamePowerUp();
        ScorePotionDecorator scorePotion = new ScorePotionDecorator(powerUp);
        scorePotion.setUp();
        int easy = 0;
        int medium = 1;
        int hard = 2;
        assertEquals(scorePotion.getStrength(easy), 12);
        assertEquals(scorePotion.getStrength(medium), 10);
        assertEquals(scorePotion.getStrength(hard), 8);
    }
}
