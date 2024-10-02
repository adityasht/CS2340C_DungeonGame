package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.models.enemy.DemonEnemy;

public class PlayerEnemyCollisionTest {

    @Test
    public void playerEnemyCollisionTest() {
        PlayerModel player = PlayerModel.getPlayer();
        DemonEnemy demon = new DemonEnemy();

        Tile[][] testLayout = new Tile[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                testLayout[i][j] = new Tile(null, false);
                if ((i == 0) || (i == 6) || (j == 0) || (j == 6)) {
                    testLayout[i][j].setCollideable(true);
                }
            }
        }
        player.setPlayerLayout(testLayout);
        PlayerCollisionPublisher publisher = new PlayerCollisionPublisher();
        player.setCollisionPublisher(publisher);
        publisher.subscribe(demon);
        demon.setCurrX(3);
        demon.setCurrY(3);
        player.setPlayerX(3);
        player.setPlayerY(3);
        publisher.setPlayerY(player.getPlayerY());
        publisher.setPlayerX(player.getPlayerX());
        publisher.notifySubscribers();
        assertTrue(demon.checkCollision());

    }

}
