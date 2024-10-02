package com.example.cs2340c_team22.models.enemy;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.models.PlayerAttackPublisher;
import com.example.cs2340c_team22.models.PlayerAttackSubscriber;
import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerCollisionSubscriber;

public class OrcEnemy implements Enemy, PlayerCollisionSubscriber, PlayerAttackSubscriber {
    // enemy attributes
    private final int enemySprite = R.drawable.masked_orc_idle_anim_f0;
    // can edit these to match
    private int enemyHealth = 6;
    private final int enemySpeed = 1;
    private int currX;
    private int currY;

    private int playerX;
    private int playerY;
    private boolean playerAttacking;
    private int count = 0;
    private int directionVector = 1;

    private boolean isFrozen = false;

    @Override
    public void move() {
        if (!isFrozen) {
            currY += directionVector;
            count++;
            if (count == 6) {
                directionVector *= -1;
                count = 0;
            }
        }

    }

    // getter method for health
    public int getEnemyHealth() {
        return enemyHealth;
    }

    // setter method for health
    public void setEnemyHealth(int newHealth) {
        enemyHealth = newHealth;
    }
    public int getCurrX() {
        return currX;
    }

    public void setCurrX(int x) {
        currX = x;
    }

    public int getCurrY() {
        return currY;
    }

    public void setCurrY(int y) {
        currY = y;
    }
    public int getEnemySprite() {
        return enemySprite;
    }

    public boolean checkCollision() {
        return (playerX == currX) && (playerY == currY);
    }

    @Override
    public boolean checkAttacked() {
        return
            playerAttacking && (
                (
                    (playerX == currX)
                        && ((currY == playerY + 1) || (currY == playerY) || (currY == playerY - 1))
                )
                    || (
                    (playerY == currY)
                        && ((currX == playerX + 1) || (currX == playerX - 1))
                )
            );
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int x) {
        playerX = x;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int y) {
        playerY = y;
    }

    public int getDirectionVector() {
        return this.directionVector;
    }

    public boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    @Override
    public void update(PlayerCollisionPublisher subject) {
        playerX = subject.getPlayerX();
        playerY = subject.getPlayerY();
    }

    @Override
    public void update(PlayerAttackPublisher subject) {
        this.playerAttacking = subject.isAttacking();
    }
}
