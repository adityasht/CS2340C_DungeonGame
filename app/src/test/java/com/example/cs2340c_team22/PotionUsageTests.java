package com.example.cs2340c_team22;

import static org.junit.Assert.assertEquals;

import com.example.cs2340c_team22.models.GamePowerUp;
import com.example.cs2340c_team22.models.HealthPotionDecorator;
import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.ScorePotionDecorator;

import org.junit.Test;

public class PotionUsageTests {
    @Test
    public void useScorePotion() {
        GamePowerUp powerUp1 = new GamePowerUp();
        ScorePotionDecorator scorePotion = new ScorePotionDecorator(powerUp1);
        scorePotion.setUp();
        PlayerModel testModel = PlayerModel.getPlayer();
        testModel.setPlayerX(1);
        testModel.setPlayerY(1);
        // score potion is at (1, 12), so should not be used up
        assertEquals(powerUp1.getHasBeenUsed(), false);
    }
    @Test
    public void useHealthPotion() {
        GamePowerUp powerUp1 = new GamePowerUp();
        HealthPotionDecorator healthPotion = new HealthPotionDecorator(powerUp1);
        healthPotion.setUp();
        PlayerModel testModel = PlayerModel.getPlayer();
        testModel.setPlayerX(2);
        testModel.setPlayerY(5);
        // health potion is at (5, 8), so should not be used up
        assertEquals(powerUp1.getHasBeenUsed(), false);
    }
}
