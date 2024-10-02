package com.example.cs2340c_team22.models;

import com.example.cs2340c_team22.R;

public class ScorePotionDecorator extends PowerUpDecorator {
    private int strength;

    private final int potionSpriteId = R.drawable.flask_big_yellow;
    private GamePowerUp wrappee;
    public ScorePotionDecorator(GamePowerUp powerUp) {
        wrappee = powerUp;
    }

    public void setUp() {
        wrappee.setX(1);
        wrappee.setY(12);
        wrappee.setPowerUpSprite(potionSpriteId);
    }

    public int getStrength(int difficulty) {
        if (difficulty == 0) {
            strength = 12;
        } else if (difficulty == 1) {
            strength = 10;
        } else if (difficulty == 2) {
            strength = 8;
        }
        return strength;
    }
}
