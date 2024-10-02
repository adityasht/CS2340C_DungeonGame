package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.viewmodels.KeyMovementStrategy;

public class MovementTests {

    @Test
    public void moveUpDown() {
        PlayerModel testModel = PlayerModel.getPlayer();
        Tile[][] testLayout = new Tile[10][10];
        KeyMovementStrategy testStrategy = new KeyMovementStrategy(testModel);
        testModel.setPlayerMoveStrategy(testStrategy);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testLayout[i][j] = new Tile(null, false);
                if ((i == 0) || (i == 9)
                        ||
                        (j == 0) || (j == 9)) {
                    testLayout[i][j].setCollideable(true);
                }
            }
        }
        testModel.setPlayerLayout(testLayout);
        int yLoc = 1;
        for (int i = 0; i < 7; i++) {
            yLoc += 1;
            testModel.moveDown();
            assertEquals(yLoc, testModel.getPlayerY());
        }

        for (int i = 0; i < 7; i++) {
            yLoc -= 1;
            testModel.moveUp();
            assertEquals(yLoc, testModel.getPlayerY());
        }

    }

    @Test
    public void moveLeftRight() {
        PlayerModel testModel = PlayerModel.getPlayer();
        Tile[][] testLayout = new Tile[10][10];
        KeyMovementStrategy testStrategy = new KeyMovementStrategy(testModel);
        testModel.setPlayerMoveStrategy(testStrategy);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testLayout[i][j] = new Tile(null, false);
                if ((i == 0) || (i == 9)
                        ||
                        (j == 0) || (j == 9)) {
                    testLayout[i][j].setCollideable(true);
                }
            }
        }
        testModel.setPlayerLayout(testLayout);
        int xLoc = 1;
        for (int i = 0; i < 7; i++) {
            xLoc += 1;
            testModel.moveRight();
            assertEquals(xLoc, testModel.getPlayerX());
        }

        for (int i = 0; i < 7; i++) {
            xLoc -= 1;
            testModel.moveLeft();
            assertEquals(xLoc, testModel.getPlayerX());
        }


    }

}
