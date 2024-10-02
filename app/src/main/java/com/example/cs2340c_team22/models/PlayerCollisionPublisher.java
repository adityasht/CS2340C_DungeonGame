package com.example.cs2340c_team22.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerCollisionPublisher {
    private int playerX;
    private int playerY;
    private final List<PlayerCollisionSubscriber> subscribers = new ArrayList<>();

    public PlayerCollisionPublisher() {

    }

    public void setPlayerX(int x) {
        playerX = x;
    }

    public void setPlayerY(int y) {
        playerY = y;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void subscribe(PlayerCollisionSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(PlayerCollisionSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // Notify subscribers of new playerX and playerY
    public void notifySubscribers() {
        for (PlayerCollisionSubscriber subscriber: subscribers) {
            subscriber.update(this);
        }
    }




}
