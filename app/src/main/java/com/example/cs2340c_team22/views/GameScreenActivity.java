package com.example.cs2340c_team22.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.viewmodels.ConfigViewModel;
import com.example.cs2340c_team22.viewmodels.GameScreenViewModel;

public class GameScreenActivity extends AppCompatActivity {

    private GameScreenViewModel updateGameViewModel;
    private TilemapGameView tilemapGameView;
    private TextView playerDifficultyTextView;
    private TextView playerHealthTextView;

    private TextView playerScoreView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use data binding for the layout
        setContentView(R.layout.activity_tilemap_game);

        // initialize textViews and ImageViews
        playerDifficultyTextView = findViewById(R.id.playerDifficultyDisplay);
        playerHealthTextView = findViewById(R.id.playerHealthDisplay);
        playerScoreView = findViewById(R.id.playerScoreDisplay);

        // obtain reference to view model to update livedata
        updateGameViewModel =  new ViewModelProvider(this).get(GameScreenViewModel.class);

        // create intent
        Intent pullFromConfig = getIntent();

        // pull data from ConfigScreen
        String playerNameInput = pullFromConfig.getStringExtra("playerName");
        double difficultyValInput = pullFromConfig.getDoubleExtra("difficulty", 1.0);

        // calculate difficulty enum
        ConfigViewModel.Difficulty playerDifficulty;
        if (difficultyValInput == 1.0) {
            playerDifficulty = ConfigViewModel.Difficulty.EASY;
        } else if (difficultyValInput == 0.75) {
            playerDifficulty = ConfigViewModel.Difficulty.MEDIUM;
        } else {
            playerDifficulty = ConfigViewModel.Difficulty.HARD;
        }

        // pre set data based on config screen data
        String difficultyConcat = "Difficulty: " + playerDifficulty.name();
        playerDifficultyTextView.setText(difficultyConcat);
        int initialScore = 100;
        String scoreConcat = "Score: " + initialScore;
        playerScoreView.setText(scoreConcat);

        Bitmap playerSpriteBitmap =
            BitmapFactory.decodeResource(
                this.getResources(),
                pullFromConfig.getIntExtra("playerSprite", R.drawable.dwarf_m_idle_anim_f0)
            );

        // initialize viewModel
        updateGameViewModel.init(playerNameInput, playerDifficulty,
            playerSpriteBitmap, initialScore, 1, 1);
        updateGameViewModel.initializeMap(this.getResources());

        updateGameViewModel.getPlayerDifficulty().observe(this,
                difficulty -> {
                    String difficultyInput = "Difficulty: " + difficulty.name();
                    playerDifficultyTextView.setText(difficultyInput);
                });

        updateGameViewModel.getPlayerHealth().observe(this, playerHealth -> {
            String healthConcat = "Health: " + playerHealth;
            playerHealthTextView.setText(healthConcat);
        });

        // observe score changes
        updateGameViewModel.startScoreDecrement();
        updateGameViewModel.getPlayerScore().observe(this, playerScore -> {
            String userScoreConcat = "Score: " + playerScore;
            playerScoreView.setText(userScoreConcat);
        });

        TextView playerNameDisplay = findViewById(R.id.playerNameDisplay);
        updateGameViewModel.getPlayerName().observe(this, name -> {
            String nameConcat = "Player name: " + name;
            playerNameDisplay.setText(nameConcat);
        });

        FrameLayout gameMap = findViewById(R.id.mapDisplay);
        tilemapGameView = new TilemapGameView(this, updateGameViewModel);
        gameMap.addView(tilemapGameView);


        // Move down button
        Button moveDownButton = findViewById(R.id.downMovementButton);
        moveDownButton.setOnClickListener(v -> {
            updateGameViewModel.moveDown();
            if (updateGameViewModel.getFinished()
                                        || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
        });

        // Move up button
        Button moveUpButton = findViewById(R.id.upMovementButton);
        moveUpButton.setOnClickListener(v -> {
            updateGameViewModel.moveUp();
            if (updateGameViewModel.getFinished()
                                        || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
        });
        // Move left button
        Button moveLeftButton = findViewById(R.id.leftMovementButton);
        moveLeftButton.setOnClickListener(v -> {
            updateGameViewModel.moveLeft();
            if (updateGameViewModel.getFinished()
                                        || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
        });
        // Move right button
        Button moveRightButton = findViewById(R.id.rightMovementButton);
        moveRightButton.setOnClickListener(v -> {
            updateGameViewModel.moveRight();
            if (updateGameViewModel.getFinished()
                                        || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
        });

        Button attackButton = findViewById(R.id.attackButton);
        attackButton.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                updateGameViewModel.toggleAttacking();
                return true;
            default:
                return false;
            }
        });
    }

    // Movement keyboard input
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_W:
            updateGameViewModel.moveUp();
            if (updateGameViewModel.getFinished()
                    || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
            return true;
        case KeyEvent.KEYCODE_A:
            updateGameViewModel.moveLeft();
            if (updateGameViewModel.getFinished()
                    || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
            return true;
        case KeyEvent.KEYCODE_S:
            updateGameViewModel.moveDown();
            if (updateGameViewModel.getFinished()
                    || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
            return true;
        case KeyEvent.KEYCODE_D:
            updateGameViewModel.moveRight();
            if (updateGameViewModel.getFinished()
                    || updateGameViewModel.getPlayerHealth().getValue() <= 0) {
                updateGameViewModel.setFinished(false);
                transition();
            }
            return true;
        default:
            return super.onKeyUp(keyCode, event);
        }
    }

    private void transition() {
        tilemapGameView.stop();
        Intent switchScreenToEnd = new Intent(GameScreenActivity.this, EndScreenActivity.class);
        switchScreenToEnd.putExtra(
                "finalScore",
                updateGameViewModel.getPlayerScore().getValue()
        );
        switchScreenToEnd.putExtra(
                "playerName",
                updateGameViewModel.getPlayerName().getValue()
        );
        switchScreenToEnd.putExtra(
                "winOrLose",
                updateGameViewModel.getPlayerHealth().getValue()
        );
        startActivity(switchScreenToEnd);
    }
}