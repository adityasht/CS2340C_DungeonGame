package com.example.cs2340c_team22.viewmodels;

import com.example.cs2340c_team22.models.PlayerModel;

public class KeyMovementStrategy implements MovementStrategy {

    private final PlayerModel player;

    public KeyMovementStrategy(PlayerModel player) {
        this.player = player;
    }

    @Override
    public void moveDown() {
        player.setPlayerY(player.getPlayerY() + 1);
    }

    @Override
    public void moveLeft() {
        player.setPlayerX(player.getPlayerX() - 1);
    }

    @Override
    public void moveRight() {
        player.setPlayerX(player.getPlayerX() + 1);
    }

    @Override
    public void moveUp() {
        player.setPlayerY(player.getPlayerY() - 1);
    }
}
