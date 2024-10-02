package com.example.cs2340c_team22.models;

import com.example.cs2340c_team22.R;

public class FreezeEnemiesPotionDecorator extends PowerUpDecorator {

    private final int potionSpriteId = R.drawable.flask_big_blue;
    private GamePowerUp wrappee;
    public FreezeEnemiesPotionDecorator(GamePowerUp powerUp) {
        wrappee = powerUp;
    }

    public void setUp() {
        wrappee.setX(10);
        wrappee.setY(1);
        wrappee.setPowerUpSprite(potionSpriteId);
    }
}
