package com.example.cs2340c_team22.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs2340c_team22.R;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Button startButton = findViewById(R.id.start_game_button);
        Button quitButton = findViewById(R.id.quit_game_button);
        startButton.setOnClickListener(onStartButtonClicked());
        quitButton.setOnClickListener(onQuitButtonClicked());
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finishAffinity();
        }
    }

    private View.OnClickListener onStartButtonClicked() {
        return view -> {
            Intent instructionScreenIntent = new Intent(this, InstructionsActivity.class);
            startActivity(instructionScreenIntent);
        };
    }

    private View.OnClickListener onQuitButtonClicked() {
        return view -> {
            Intent intitalSceenIntent = new Intent(this, StartScreenActivity.class);
            intitalSceenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intitalSceenIntent.putExtra("EXIT", true);
            startActivity(intitalSceenIntent);
        };
    }
}

