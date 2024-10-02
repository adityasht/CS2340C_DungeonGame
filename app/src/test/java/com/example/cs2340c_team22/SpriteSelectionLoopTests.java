package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.ConfigModel;

/**
 * Loops through Sprite carousel in ConfigModel
 */
public class SpriteSelectionLoopTests {
    @Test
    public void spriteLoopForwardTest() {
        ConfigModel testModel = ConfigModel.getInstance();
        int initialSprite = testModel.getCurrentSprite();
        for (int i = 0; i < 9; i++) {
            // Loop forward to select new sprite
            testModel.selectNewSprite(false);
            int newSprite = testModel.getCurrentSprite();
            if (i % 3 == 2) {
                // same sprite within sprite list
                assertEquals(initialSprite, newSprite);
            } else {
                // different sprites within list
                assertNotEquals(initialSprite, newSprite);
            }
        }
    }

    @Test
    public void spriteLoopBackwardTest() {
        ConfigModel testModel = ConfigModel.getInstance();
        int initialSprite = testModel.getCurrentSprite();
        for (int i = 0; i < 9; i++) {
            // Loop backward to select new sprite through sprite list
            testModel.selectNewSprite(true);
            int newSprite = testModel.getCurrentSprite();
            if (i % 3 == 2) {
                // Same sprite in sprite list
                assertEquals(initialSprite, newSprite);
            } else {
                // Different sprite in sprite list
                assertNotEquals(initialSprite, newSprite);
            }
        }
    }
}