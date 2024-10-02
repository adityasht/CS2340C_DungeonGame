package com.example.cs2340c_team22.models.enemy;

public class EnemyCreator {
    public Enemy createEnemy(String enemyType) {
        if ("demon".equals(enemyType)) {
            return new DemonEnemy();
        } else if ("orc".equals(enemyType)) {
            return new OrcEnemy();
        } else if ("pumpkin".equals(enemyType)) {
            return new PumpkinEnemy();
        } else if ("zombie".equals(enemyType)) {
            return new ZombieEnemy();
        } else {
            throw new IllegalArgumentException("Invalid enemy type");
        }
    }
}
