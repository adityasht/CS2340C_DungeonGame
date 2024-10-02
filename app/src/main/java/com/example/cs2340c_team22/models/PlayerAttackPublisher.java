package com.example.cs2340c_team22.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerAttackPublisher {
    private final List<PlayerAttackSubscriber> subscribers;
    private boolean attacking;

    public PlayerAttackPublisher() {
        this.subscribers = new ArrayList<>();
        this.attacking = false;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void subscribe(PlayerAttackSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(PlayerAttackSubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (PlayerAttackSubscriber subscriber : this.subscribers) {
            subscriber.update(this);
        }
    }
}
