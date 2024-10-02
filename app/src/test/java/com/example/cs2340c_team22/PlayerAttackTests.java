package com.example.cs2340c_team22;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.PlayerAttackPublisher;
import com.example.cs2340c_team22.models.enemy.DemonEnemy;

public class PlayerAttackTests {
    private static DemonEnemy enemy;
    private static PlayerAttackPublisher attackPublisher;

    @BeforeClass
    public static void setup() {
        enemy = new DemonEnemy();
        attackPublisher = new PlayerAttackPublisher();

        attackPublisher.subscribe(enemy);
    }

    @Before
    public void reset() {
        enemy.setPlayerX(2);
        enemy.setPlayerY(2);
        attackPublisher.setAttacking(true);
        attackPublisher.notifySubscribers();
    }

    @Test
    public void notAttacking() {
        attackPublisher.setAttacking(false);
        attackPublisher.notifySubscribers();

        enemy.setCurrX(1);
        enemy.setCurrY(2);
        assertFalse(enemy.checkAttacked());

        enemy.setCurrX(2);
        enemy.setCurrY(1);
        assertFalse(enemy.checkAttacked());
    }

    @Test
    public void enemyNotInRangeX() {
        enemy.setCurrX(4);
        enemy.setCurrY(2);
        assertFalse(enemy.checkAttacked());

        enemy.setPlayerX(7);
        assertFalse(enemy.checkAttacked());
    }

    @Test
    public void enemyNotInRangeY() {
        enemy.setCurrX(2);
        enemy.setCurrY(4);
        assertFalse(enemy.checkAttacked());

        enemy.setPlayerY(7);
        assertFalse(enemy.checkAttacked());
    }

    @Test
    public void enemyInRangeX() {
        enemy.setCurrX(1);
        enemy.setCurrY(2);
        assertTrue(enemy.checkAttacked());

        enemy.setPlayerX(0);
        assertTrue(enemy.checkAttacked());
    }

    @Test
    public void enemyInRangeY() {
        enemy.setCurrX(2);
        enemy.setCurrY(1);
        assertTrue(enemy.checkAttacked());

        enemy.setPlayerY(0);
        assertTrue(enemy.checkAttacked());
    }
}
