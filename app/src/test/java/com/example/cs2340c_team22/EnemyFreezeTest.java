package com.example.cs2340c_team22;
import com.example.cs2340c_team22.models.enemy.Enemy;
import com.example.cs2340c_team22.models.enemy.EnemyCreator;

import static org.junit.Assert.*;
import org.junit.Test;

public class EnemyFreezeTest {

    @Test
    public void testPumpkinEnemyFrozen() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy enemy = enemyCreator.createEnemy("pumpkin");
        enemy.setCurrX(1);
        enemy.setCurrY(1);
        assertEquals(enemy.getCurrX(), 1);
        assertEquals(enemy.getCurrY(), 1);
        enemy.move();
        assertEquals(enemy.getCurrX(), 2);
        assertEquals(enemy.getCurrY(), 1);

        // When isFrozen is set to true and move is called
        // the enemy will not move
        enemy.setIsFrozen(true);
        enemy.move();
        assertEquals(enemy.getCurrX(), 2);
        assertEquals(enemy.getCurrY(), 1);
        assertFalse(enemy.getCurrX() == 3);
        assertTrue(enemy.getIsFrozen());
    }

    @Test
    public void testDemonEnemyFrozen() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy enemy = enemyCreator.createEnemy("demon");
        enemy.setCurrX(1);
        enemy.setCurrY(1);
        assertEquals(enemy.getCurrX(), 1);
        assertEquals(enemy.getCurrY(), 1);
        enemy.move();
        assertEquals(enemy.getCurrX(), 2);
        assertEquals(enemy.getCurrY(), 2);

        // When isFrozen is set to true and move is called
        // the enemy will not move
        enemy.setIsFrozen(true);
        enemy.move();
        assertEquals(enemy.getCurrX(), 2);
        assertFalse(enemy.getCurrX() == 3);
        assertEquals(enemy.getCurrY(), 2);
        assertFalse(enemy.getCurrY() == 3);
        assertTrue(enemy.getIsFrozen());
    }

    @Test
    public void testOrcEnemyFrozen() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy enemy = enemyCreator.createEnemy("orc");
        enemy.setCurrX(1);
        enemy.setCurrY(1);
        assertEquals(enemy.getCurrX(), 1);
        assertEquals(enemy.getCurrY(), 1);
        enemy.move();
        assertEquals(enemy.getCurrX(), 1);
        assertEquals(enemy.getCurrY(), 2);

        // When isFrozen is set to true and move is called
        // the enemy will not move
        enemy.setIsFrozen(true);
        enemy.move();
        assertEquals(enemy.getCurrX(), 1);
        assertEquals(enemy.getCurrY(), 2);
        assertFalse(enemy.getCurrY() == 3);
        assertTrue(enemy.getIsFrozen());
    }

    @Test
    public void testZombieEnemyFrozen() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy enemy = enemyCreator.createEnemy("zombie");
        enemy.setCurrX(1);
        enemy.setCurrY(1);
        assertEquals(enemy.getCurrX(), 1);
        enemy.move();
        assertEquals(enemy.getCurrX(), 2);

        // When isFrozen is set to true and move is called
        // the enemy will not move
        enemy.setIsFrozen(true);
        enemy.move();
        assertEquals(enemy.getCurrX(), 2);
        assertFalse(enemy.getCurrX() == 3);
        assertTrue(enemy.getIsFrozen());
    }
}
