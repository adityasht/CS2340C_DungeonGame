package com.example.cs2340c_team22.models;

import com.example.cs2340c_team22.R;

public class HealthPotionDecorator extends PowerUpDecorator {

    private final int potionSpriteId = R.drawable.flask_big_red;

    private int strength;

    private GamePowerUp wrappee;
    public HealthPotionDecorator(GamePowerUp powerUp) {
        wrappee = powerUp;
    }
    public void setUp() {
        wrappee.setX(5);
        wrappee.setY(8);
        wrappee.setPowerUpSprite(potionSpriteId);
    }

    public int getStrength(int difficulty) {
        if (difficulty == 0) {
            strength = 15;
        } else if (difficulty == 1) {
            strength = 10;
        } else if (difficulty == 2) {
            strength = 5;
        }
        return strength;
    }


}
