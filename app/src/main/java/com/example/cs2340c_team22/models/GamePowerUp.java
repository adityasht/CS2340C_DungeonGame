package com.example.cs2340c_team22.models;

public class GamePowerUp implements PowerUp {

    private int x;
    private int y;
    private int powerUpSprite;
    private boolean hasBeenUsed;

    public GamePowerUp() {
        x = 0;
        y = 0;
        powerUpSprite = 0;
        hasBeenUsed = false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPowerUpSprite(int sprite) {
        powerUpSprite = sprite;
    }

    public void setHasBeenUsed(boolean used) {
        hasBeenUsed = used;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getHasBeenUsed() {
        return hasBeenUsed;
    }

    public int getPowerUpSprite() {
        return powerUpSprite;
    }





}
