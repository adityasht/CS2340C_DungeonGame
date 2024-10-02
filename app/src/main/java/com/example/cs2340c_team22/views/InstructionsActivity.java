package com.example.cs2340c_team22.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs2340c_team22.R;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_screen);
        Button nextButton = findViewById(R.id.next_game_button);
        nextButton.setOnClickListener(onNextButtonClicked());
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finishAffinity();
        }
    }

    private View.OnClickListener onNextButtonClicked() {
        return view -> {
            Intent configScreenIntent = new Intent(this, ConfigScreenActivity.class);
            startActivity(configScreenIntent);
        };
    }
}


