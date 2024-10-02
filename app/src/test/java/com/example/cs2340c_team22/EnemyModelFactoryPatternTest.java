package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.enemy.DemonEnemy;
import com.example.cs2340c_team22.models.enemy.Enemy;
import com.example.cs2340c_team22.models.enemy.EnemyCreator;
import com.example.cs2340c_team22.models.enemy.OrcEnemy;
import com.example.cs2340c_team22.models.enemy.PumpkinEnemy;
import com.example.cs2340c_team22.models.enemy.ZombieEnemy;

public class EnemyModelFactoryPatternTest {

    @Test
    public void testDemonCreation() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy demon = enemyCreator.createEnemy("demon");
        assertTrue(demon instanceof DemonEnemy);
    }

    @Test
    public void testOrcCreation() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy orc = enemyCreator.createEnemy("orc");
        assertTrue(orc instanceof OrcEnemy);
    }

    @Test
    public void testPumpkinCreation() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy pumpkin = enemyCreator.createEnemy("pumpkin");
        assertTrue(pumpkin instanceof PumpkinEnemy);
    }

    @Test
    public void testZombieCreation() {
        EnemyCreator enemyCreator = new EnemyCreator();
        Enemy zombie = enemyCreator.createEnemy("zombie");
        assertTrue(zombie instanceof ZombieEnemy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownEnemyCreation() {
        EnemyCreator enemyCreator = new EnemyCreator();
        enemyCreator.createEnemy("skeleton");
    }
}
