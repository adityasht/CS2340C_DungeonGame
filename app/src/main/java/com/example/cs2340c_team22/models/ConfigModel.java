package com.example.cs2340c_team22.models;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.viewmodels.ConfigViewModel;

public class ConfigModel {
    private static ConfigModel instance;

    private static final int[] SPRITE_LIST = new int[] {
        R.drawable.dwarf_m_idle_anim_f0,
        R.drawable.knight_f_idle_anim_f0,
        R.drawable.wizzard_f_idle_anim_f0
    };
    private int currentSpriteIndex;
    private ConfigViewModel.Difficulty difficulty;
    private String playerName;

    private ConfigModel() {
        this.currentSpriteIndex = 0;
        this.difficulty = ConfigViewModel.Difficulty.EASY;
        this.playerName = "Default";
    }

    public static ConfigModel getInstance() {
        if (instance == null) {
            synchronized (ConfigModel.class) {
                if (instance == null) {
                    instance = new ConfigModel();
                }
            }
        }

        return instance;
    }

    public int getCurrentSprite() {
        return SPRITE_LIST[this.currentSpriteIndex];
    }

    public void selectNewSprite(boolean selectPrevious) {
        currentSpriteIndex =
            // % spriteList.length ensures that the index does not go out of bounds
            (
                selectPrevious
                    // If going backwards and chosenSprite == 0, add (spriteList.length - 1)
                    // to go to the last sprite in spriteList
                    ? currentSpriteIndex + (currentSpriteIndex == 0 ? (SPRITE_LIST.length - 1) : -1)
                    : currentSpriteIndex + 1
            ) % SPRITE_LIST.length;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean setPlayerName(String name) {
        boolean isValid = (name != null) && (!name.trim().equals(""));
        if (isValid) {
            this.playerName = name.trim();
        }

        return isValid;
    }

    public ConfigViewModel.Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(ConfigViewModel.Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
    }
}
