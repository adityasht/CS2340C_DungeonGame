package com.example.cs2340c_team22.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.viewmodels.EndScreenViewModel;


public class EndScreenActivity extends AppCompatActivity {


    private ListView leaderboard;
    private TextView finalScoreView;
    private TextView winOrLoseView;


    private EndScreenViewModel updateEndScreenViewModel;
    private String[] leaderboardArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        updateEndScreenViewModel =  new ViewModelProvider(this).get(EndScreenViewModel.class);

        finalScoreView = findViewById(R.id.finalScore);
        winOrLoseView = findViewById(R.id.end_screen_header);
        leaderboard = findViewById(R.id.leaderboard);

        Intent intent = getIntent();
        int oldScore = intent.getIntExtra("finalScore", 0);
        String name = intent.getStringExtra("playerName");
        int endHealth = intent.getIntExtra("winOrLose", 0);
        Button restartButton = findViewById(R.id.restart_game_button);
        restartButton.setOnClickListener(onRestartButtonClicked());
        if (endHealth > 0) {
            //update leaderboard
            updateEndScreenViewModel.updateLeaderboard(name, oldScore);
        } else {
            winOrLoseView.setText("GAME OVER! You Lose!");
            oldScore = 0;
        }
        //create array for leaderboard
        leaderboardArr = updateEndScreenViewModel.createLeaderboardArr();
        // create final score
        String scoreConcat = "Final Score: " + oldScore;
        finalScoreView.setText(scoreConcat);
        // create leaderboard listview
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                leaderboardArr);
        leaderboard.setAdapter(arr);
    }

    private View.OnClickListener onRestartButtonClicked() {
        return view -> {
            Intent startScreenIntent = new Intent(this, StartScreenActivity.class);
            startActivity(startScreenIntent);
        };
    }
}