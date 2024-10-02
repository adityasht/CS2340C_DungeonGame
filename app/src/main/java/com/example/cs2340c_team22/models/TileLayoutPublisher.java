package com.example.cs2340c_team22.models;

import com.example.cs2340c_team22.models.drawable.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileLayoutPublisher {
    private Tile[][] currLayout;
    private final List<Subscriber> subscribers = new ArrayList<>();

    public TileLayoutPublisher() {

    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }

    public Tile[][] getCurrLayout() {
        return currLayout;
    }

    public void setCurrLayout(Tile[][] layout) {
        currLayout = layout;
    }


}
