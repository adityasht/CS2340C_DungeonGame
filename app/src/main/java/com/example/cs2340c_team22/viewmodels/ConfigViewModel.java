package com.example.cs2340c_team22.viewmodels;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.models.ConfigModel;

public class ConfigViewModel extends ViewModel {

    private final ConfigModel configModel;

    private final MutableLiveData<Integer> playerSprite;
    private final MutableLiveData<Difficulty> difficulty;

    public ConfigViewModel() {
        this.configModel = ConfigModel.getInstance();
        this.playerSprite = new MutableLiveData<>(configModel.getCurrentSprite());
        this.difficulty = new MutableLiveData<>(configModel.getDifficulty());
    }

    public LiveData<Integer> getPlayerSprite() {
        return playerSprite;
    }
    public LiveData<Difficulty> getDifficulty() {
        return difficulty;
    }

    public void selectNewSprite(boolean selectPrevious) {
        configModel.selectNewSprite(selectPrevious);
        playerSprite.setValue(configModel.getCurrentSprite());
    }

    public void setDifficulty(int selectedButton) {
        if (selectedButton == R.id.hardButton) {
            this.configModel.setDifficulty(Difficulty.HARD);
        } else if (selectedButton == R.id.mediumButton) {
            this.configModel.setDifficulty(Difficulty.MEDIUM);
        } else {
            this.configModel.setDifficulty(Difficulty.EASY);
        }

        this.difficulty.setValue(this.configModel.getDifficulty());
    }

    public String getPlayerName() {
        return this.configModel.getPlayerName();
    }

    public boolean changePlayerName(Editable name) {
        if (name == null) {
            return false;
        }

        return this.configModel.setPlayerName(name.toString());
    }

    public enum Difficulty {
        EASY(1.0),
        MEDIUM(0.75),
        HARD(0.5);

        private final double value;
        public double getValue() {
            return this.value;
        }
        Difficulty(double value) {
            this.value = value;
        }
    }
}
