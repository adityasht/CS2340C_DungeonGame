package com.example.cs2340c_team22;

import com.example.cs2340c_team22.models.ConfigModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigModelSingletonTest {
    @Test
    public void modelIsSingleton() {
        ConfigModel configModel1 = ConfigModel.getInstance();
        ConfigModel configModel2 = ConfigModel.getInstance();

        assertEquals(configModel1, configModel2);

        configModel1.setPlayerName("Valid name");
        configModel2.selectNewSprite(false);

        assertEquals(configModel1.getPlayerName(), configModel2.getPlayerName());
        assertEquals(configModel1.getCurrentSprite(), configModel2.getCurrentSprite());
    }
}
