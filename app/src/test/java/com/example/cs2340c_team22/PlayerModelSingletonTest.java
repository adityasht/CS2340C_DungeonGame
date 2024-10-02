package com.example.cs2340c_team22;

import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.viewmodels.ConfigViewModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerModelSingletonTest {
    @Test
    public void modelIsSingleton() {
        // initialize two playerModel objects
        PlayerModel testModel1 = PlayerModel.getPlayer();
        PlayerModel testModel2 = PlayerModel.getPlayer();

        // check to see if singleton holds true
        assertEquals(testModel1, testModel2);

        // change attributes in either test model
        testModel1.setPlayerName("Valid name");
        testModel2.setPlayerDifficulty(ConfigViewModel.Difficulty.HARD);
        testModel1.setPlayerScore(50);

        // compare attributes to ensure singleton model holds true
        assertEquals(testModel1.getPlayerName(), testModel2.getPlayerName());
        assertEquals(testModel1.getPlayerDifficulty(), testModel2.getPlayerDifficulty());
        assertEquals(testModel1.getPlayerHealth(), testModel2.getPlayerHealth());
        assertEquals(testModel1.getPlayerScore(), testModel2.getPlayerScore());
    }
}
