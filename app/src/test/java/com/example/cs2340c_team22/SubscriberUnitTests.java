package com.example.cs2340c_team22;

import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.TileLayoutPublisher;
import com.example.cs2340c_team22.models.drawable.Tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubscriberUnitTests {
    @Test
    public void subscribeAndUpdate() {
        PlayerModel testPlayerModel = PlayerModel.getPlayer();
        TileLayoutPublisher testPublisher = new TileLayoutPublisher();
        PlayerCollisionPublisher collisionPublisher = new PlayerCollisionPublisher();
        testPlayerModel.setCollisionPublisher(collisionPublisher);

        Tile[][] testLayout = new Tile[1][2];

        testPublisher.subscribe(testPlayerModel);
        testPublisher.setCurrLayout(testLayout);

        //assertNull(testPlayerModel.getPlayerLayout());

        testPublisher.notifySubscribers();

        assertNotNull(testPlayerModel.getPlayerLayout());
        assertArrayEquals(testPlayerModel.getPlayerLayout(), testLayout);
        assertEquals(testPlayerModel.getPlayerLayout().length, 1);
        assertEquals(testPlayerModel.getPlayerLayout()[0].length, 2);
    }

    @Test
    public void unsubscribe() {
        PlayerModel testPlayerModel = PlayerModel.getPlayer();
        TileLayoutPublisher testPublisher = new TileLayoutPublisher();

        Tile[][] testLayout1 = new Tile[1][2];
        Tile[][] testLayout2 = new Tile[2][1];

        testPublisher.subscribe(testPlayerModel);
        testPublisher.setCurrLayout(testLayout1);
        testPublisher.notifySubscribers();

        testPublisher.setCurrLayout(testLayout2);
        testPublisher.unsubscribe(testPlayerModel);
        testPublisher.notifySubscribers();

        assertNotNull(testPlayerModel.getPlayerLayout());
        assertArrayEquals(testPlayerModel.getPlayerLayout(), testLayout1);
        assertEquals(testPlayerModel.getPlayerLayout().length, 1);
        assertEquals(testPlayerModel.getPlayerLayout()[0].length, 2);
    }
}
