package com.example.cs2340c_team22.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.cs2340c_team22.models.LeaderboardModel;
public class EndScreenViewModel extends ViewModel {

    private final LeaderboardModel leaderboardModel;

    public EndScreenViewModel() {
        leaderboardModel = LeaderboardModel.createLeaderboard();
    }

    public void updateLeaderboard(String playerName, int score) {
        leaderboardModel.setFinalscore(score, leaderboardModel.getSize());
        leaderboardModel.setDate(leaderboardModel.getSize());
        leaderboardModel.setPlayerName(playerName, leaderboardModel.getSize());
        leaderboardModel.setSize(leaderboardModel.getSize() + 1);
        leaderboardModel.sort();
    }

    public String[] createLeaderboardArr() {
        int siz;
        if (leaderboardModel.getSize() >= 11) {
            siz = 10;
        } else {
            siz = leaderboardModel.getSize();
        }
        String[] out = new String[leaderboardModel.getSize()];
        for (int i = 0; i < siz; i++) {
            String allConcat = leaderboardModel.getPlayerName(i) + " scored "
                    + leaderboardModel.getPlayerScore(i) + " at "
                    + leaderboardModel.getDate(i);
            out[i] = allConcat;
        }
        return out;
    }

}
