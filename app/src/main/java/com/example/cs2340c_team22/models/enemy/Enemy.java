package com.example.cs2340c_team22.models.enemy;


public interface Enemy {
    // enemies should be able to attack and move
    void move();
    void setCurrX(int x);
    int getCurrX();
    void setCurrY(int y);
    int getCurrY();
    int getEnemySprite();
    int getEnemyHealth();
    void setEnemyHealth(int newHealth);
    boolean checkCollision();
    boolean checkAttacked();
    void setIsFrozen(boolean frozen);
    boolean getIsFrozen();
}
