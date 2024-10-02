package com.example.cs2340c_team22.models.enemy;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.models.PlayerAttackPublisher;
import com.example.cs2340c_team22.models.PlayerAttackSubscriber;
import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerCollisionSubscriber;

public class PumpkinEnemy implements Enemy, PlayerCollisionSubscriber, PlayerAttackSubscriber {
    // enemy attributes
    private final int enemySprite = R.drawable.pumpkin_dude_idle_anim_f0;
    // can edit these to match
    private int enemyHealth = 3;
    private final int enemySpeed = 5;
    private int currX;
    private int currY;
    private int playerX;
    private int playerY;
    private boolean playerAttacking;
    private int count = 0;
    private int directionVectorX = 1;
    private int directionMagnitudeX = 1;
    private int directionVectorY = -1;
    private int directionMagnitudeY = 0;
    private boolean isFrozen = false;

    @Override
    public void move() {
        if (!isFrozen) {
            currX += directionVectorX * directionMagnitudeX;
            currY += directionVectorY * directionMagnitudeY;
            count++;
            if (count == 4) {
                if (directionMagnitudeX == 0) {
                    directionMagnitudeX = 1;
                    directionMagnitudeY = 0;
                    directionVectorX *= -1;
                } else if (directionMagnitudeY == 0) {
                    directionMagnitudeY = 1;
                    directionMagnitudeX = 0;
                    directionVectorY *= -1;
                }
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

    public boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(boolean frozen) {
        isFrozen = frozen;
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

    public int getDirectionVectorX() {
        return this.directionVectorX;
    }

    public int getDirectionVectorY() {
        return this.directionVectorY;
    }

    public int getDirectionMagnitudeX() {
        return this.directionMagnitudeX;
    }

    public int getDirectionMagnitudeY() {
        return this.directionMagnitudeY;
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
