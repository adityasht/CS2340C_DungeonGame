package com.example.cs2340c_team22;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.enemy.DemonEnemy;
import com.example.cs2340c_team22.models.enemy.PumpkinEnemy;

public class CollisionPublisherTest {


    @Test
    public void testPublisher() {
        PlayerCollisionPublisher collisionPublisher = new PlayerCollisionPublisher();
        PumpkinEnemy pumpkin = new PumpkinEnemy();
        DemonEnemy demon = new DemonEnemy();
        // subscribe to publisher
        collisionPublisher.subscribe(pumpkin);
        collisionPublisher.subscribe(demon);
        collisionPublisher.setPlayerX(5);
        collisionPublisher.setPlayerY(5);
        collisionPublisher.notifySubscribers();

        // check the getPlayerx and playerY
        assertEquals(5, pumpkin.getPlayerX());
        assertEquals(5, pumpkin.getPlayerY());
        assertEquals(5, demon.getPlayerX());
        assertEquals(5, demon.getPlayerY());
    }

}
