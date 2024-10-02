package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;


import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.viewmodels.KeyMovementStrategy;
import com.example.cs2340c_team22.viewmodels.MovementStrategy;

public class PlayerCollisionTest {

    @Test
    public void collideWithBorder() {

        PlayerModel testModel = PlayerModel.getPlayer();
        Tile[][] testLayout = new Tile[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                testLayout[i][j] = new Tile(null, false);
                if ((i == 0) || (i == 6) || (j == 0) || (j == 6)) {
                    testLayout[i][j].setCollideable(true);
                }
            }
        }

        testModel.setPlayerLayout(testLayout);
        testModel.setPlayerX(1);
        testModel.setPlayerY(1);
        MovementStrategy keyMovement = new KeyMovementStrategy(testModel);
        testModel.setPlayerMoveStrategy(keyMovement);

        testModel.moveUp();
        testModel.moveLeft();
        assertEquals(1, testModel.getPlayerY());
        assertEquals(1, testModel.getPlayerX());

        for (int i = 0; i < 7; i++) {
            testModel.moveRight();
            testModel.moveDown();
        }
        assertEquals(5, testModel.getPlayerX());
        assertEquals(5, testModel.getPlayerY());
    }

    @Test
    public void collideWithWall() {
        PlayerModel testModel = PlayerModel.getPlayer();
        PlayerCollisionPublisher collisionPublisher = new PlayerCollisionPublisher();
        testModel.setCollisionPublisher(collisionPublisher);
        Tile[][] testLayout = new Tile[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                testLayout[i][j] = new Tile(null, false);
                if ((i == 0) || (i == 6) || (j == 0) || (j == 6)) {
                    testLayout[i][j].setCollideable(true);
                }
            }
        }

        testLayout[2][2].setCollideable(true);
        testLayout[2][3].setCollideable(true);

        testModel.setPlayerLayout(testLayout);
        testModel.setPlayerX(1);
        testModel.setPlayerY(1);
        MovementStrategy keyMovement = new KeyMovementStrategy(testModel);
        testModel.setPlayerMoveStrategy(keyMovement);

        testModel.moveRight();
        testModel.moveDown();
        assertEquals(2, testModel.getPlayerX());
        assertEquals(1, testModel.getPlayerY());

        testModel.moveRight();
        testModel.moveDown();
        testModel.moveDown();
        testModel.moveLeft();
        assertEquals(2, testModel.getPlayerX());
        //assertEquals(3, testModel.getPlayerY());
    }
}
