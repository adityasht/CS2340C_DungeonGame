package com.example.cs2340c_team22.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.viewmodels.ConfigViewModel;

public class ConfigScreenActivity extends AppCompatActivity {

    private ConfigViewModel configViewModel;
    private EditText playerNameInput;
    private ImageView chosenSpriteDisplay;
    private TextView errorMessageDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        configViewModel = new ViewModelProvider(this).get(ConfigViewModel.class);

        Button beginButton = findViewById(R.id.beginButton);
        ImageButton selectNextSpriteButton = findViewById(R.id.spriteChooseNext);
        ImageButton selectPrevSpriteButton = findViewById(R.id.spriteChoosePrev);
        RadioGroup difficultyRadioGroup = findViewById(R.id.difficultySelector);

        beginButton.setOnClickListener(onStartButtonPressed());
        difficultyRadioGroup.setOnCheckedChangeListener(onDifficultySelected());

        playerNameInput = findViewById(R.id.playerNameInput);
        chosenSpriteDisplay = findViewById(R.id.chosenSpriteDisplay);
        errorMessageDisplay = findViewById(R.id.configErrorMessageDisplay);

        selectNextSpriteButton.setOnClickListener(onNewSpriteSelected(false));
        selectPrevSpriteButton.setOnClickListener(onNewSpriteSelected(true));

        configViewModel.getPlayerSprite().observe(this, newSprite -> {
            int sprite = newSprite != null ? newSprite : R.drawable.dwarf_m_idle_anim_f0;
            chosenSpriteDisplay.setImageResource(sprite);
        });
    }

    private View.OnClickListener onStartButtonPressed() {
        return view -> {
            // Player name cannot be null, empty, or whitespace
            if (!configViewModel.changePlayerName(playerNameInput.getText())) {
                errorMessageDisplay.setText(R.string.invalid_name_error_message);
                return;
            }
            String playerName = configViewModel.getPlayerName();

            // Update GameActivity.class
            Intent game = new Intent(ConfigScreenActivity.this, GameScreenActivity.class);
            game.putExtra("difficulty", configViewModel.getDifficulty().getValue().getValue());
            game.putExtra("playerName", playerName);
            game.putExtra("playerSprite", configViewModel.getPlayerSprite().getValue());
            startActivity(game);

            finish();
        };
    }

    private View.OnClickListener onNewSpriteSelected(boolean selectPrevious) {
        return view -> configViewModel.selectNewSprite(selectPrevious);
    }

    private RadioGroup.OnCheckedChangeListener onDifficultySelected() {
        return (radioGroup, checkedId) -> configViewModel.setDifficulty(checkedId);
    }
}