package com.example.cs2340c_team22;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.cs2340c_team22.viewmodels.ConfigViewModel;
import com.example.cs2340c_team22.viewmodels.GameScreenViewModel;

public class HealthUpdateDifficultyTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Test
    public void isHealthCorrect() {
        // initialize view-model test objects
        GameScreenViewModel testModel1 = new GameScreenViewModel();
        GameScreenViewModel testModel2 = new GameScreenViewModel();
        testModel1.init(
            "test-bot",
            ConfigViewModel.Difficulty.EASY,
            null,
            0,
            1,
            1
        );
        testModel2.init(
            "test-bot",
            ConfigViewModel.Difficulty.HARD,
            null,
            0,
            1,
            1
        );

        // update difficulty of test object 1
        testModel1.setPlayerDifficulty(ConfigViewModel.Difficulty.HARD);
        int testHealth1 = testModel1.getPlayerHealth().getValue();

        // check correct health
        assertEquals(5, testHealth1);

        // update difficulty of test object 2
        testModel2.setPlayerDifficulty(ConfigViewModel.Difficulty.MEDIUM);
        int testHealth2 = testModel2.getPlayerHealth().getValue();

        // check correct health
        assertEquals(7, testHealth2);
    }
}