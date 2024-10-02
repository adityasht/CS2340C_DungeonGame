package com.example.cs2340c_team22;

import static org.junit.Assert.assertTrue;
import com.example.cs2340c_team22.models.enemy.DemonEnemy;
import com.example.cs2340c_team22.models.enemy.OrcEnemy;
import com.example.cs2340c_team22.models.enemy.PumpkinEnemy;
import com.example.cs2340c_team22.models.enemy.ZombieEnemy;

import org.junit.Test;

public class MovementTestsEnemy {

    @Test
    public void demonMove() {
        DemonEnemy demon = new DemonEnemy();
        demon.setCurrX(0);
        demon.setCurrY(0);
        demon.move();
        assertTrue(demon.getCurrX() == 1);
        assertTrue(demon.getCurrY() == 1);
    }

    @Test
    public void zombieMove() {
        ZombieEnemy zombie = new ZombieEnemy();
        zombie.setCurrX(0);
        zombie.setCurrY(5);
        zombie.move();
        zombie.move();
        assertTrue(zombie.getCurrX() == 2);
        assertTrue(zombie.getCurrY() == 5);
    }

    @Test
    public void orcMove() {
        OrcEnemy orc = new OrcEnemy();
        orc.setCurrX(0);
        orc.setCurrY(0);
        orc.move();
        assertTrue(orc.getCurrX() == 0);
        assertTrue(orc.getCurrY() == 1);
    }

    @Test
    public void pumpkinMove() {
        PumpkinEnemy pumpkin = new PumpkinEnemy();
        pumpkin.setCurrX(0);
        pumpkin.setCurrY(0);
        pumpkin.move();
        assertTrue(pumpkin.getCurrX() == 1);
        assertTrue(pumpkin.getCurrY() == 0);
    }

}
