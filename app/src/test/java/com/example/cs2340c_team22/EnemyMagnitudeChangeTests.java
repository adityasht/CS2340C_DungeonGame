package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.enemy.DemonEnemy;
import com.example.cs2340c_team22.models.enemy.OrcEnemy;
import com.example.cs2340c_team22.models.enemy.PumpkinEnemy;
import com.example.cs2340c_team22.models.enemy.ZombieEnemy;

public class EnemyMagnitudeChangeTests {
    @Test
    public void demonMagnitude() {
        DemonEnemy enemy = new DemonEnemy();
        enemy.setCurrX(0);
        enemy.setCurrY(0);
        assertEquals(enemy.getDirectionVectorX(), 1);
        assertEquals(enemy.getDirectionVectorY(), 1);

        for (int i = 0; i < 5; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVectorX(), -1);
        assertEquals(enemy.getDirectionVectorY(), -1);

        for (int i = 0; i < 5; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVectorX(), 1);
        assertEquals(enemy.getDirectionVectorY(), 1);
    }

    @Test
    public void orcMagnitude() {
        OrcEnemy enemy = new OrcEnemy();
        assertEquals(enemy.getDirectionVector(), 1);

        for (int i = 0; i < 6; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVector(), -1);

        for (int i = 0; i < 6; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVector(), 1);
    }

    @Test
    public void pumpkinMagnitude() {
        PumpkinEnemy enemy = new PumpkinEnemy();
        assertEquals(enemy.getDirectionVectorX(), 1);
        assertEquals(enemy.getDirectionVectorY(), -1);
        assertEquals(enemy.getDirectionMagnitudeX(), 1);
        assertEquals(enemy.getDirectionMagnitudeY(), 0);

        for (int i = 0; i < 4; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVectorX(), 1);
        assertEquals(enemy.getDirectionVectorY(), 1);
        assertEquals(enemy.getDirectionMagnitudeX(), 0);
        assertEquals(enemy.getDirectionMagnitudeY(), 1);

        for (int i = 0; i < 4; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVectorX(), -1);
        assertEquals(enemy.getDirectionVectorY(), 1);
        assertEquals(enemy.getDirectionMagnitudeX(), 1);
        assertEquals(enemy.getDirectionMagnitudeY(), 0);

        for (int i = 0; i < 4; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVectorX(), -1);
        assertEquals(enemy.getDirectionVectorY(), -1);
        assertEquals(enemy.getDirectionMagnitudeX(), 0);
        assertEquals(enemy.getDirectionMagnitudeY(), 1);

        for (int i = 0; i < 4; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVectorX(), 1);
        assertEquals(enemy.getDirectionVectorY(), -1);
        assertEquals(enemy.getDirectionMagnitudeX(), 1);
        assertEquals(enemy.getDirectionMagnitudeY(), 0);
    }

    @Test
    public void zombieMagnitude() {
        ZombieEnemy enemy = new ZombieEnemy();
        assertEquals(enemy.getDirectionVector(), 1);

        for (int i = 0; i < 5; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVector(), -1);

        for (int i = 0; i < 5; i++) {
            enemy.move();
        }
        assertEquals(enemy.getDirectionVector(), 1);
    }
}
