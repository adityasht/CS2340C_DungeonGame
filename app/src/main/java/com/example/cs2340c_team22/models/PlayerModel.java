package com.example.cs2340c_team22.models;

import android.graphics.Bitmap;

import com.example.cs2340c_team22.models.drawable.Sprite;
import com.example.cs2340c_team22.viewmodels.ConfigViewModel;
import com.example.cs2340c_team22.viewmodels.MovementStrategy;
import com.example.cs2340c_team22.models.drawable.Tile;

public class PlayerModel implements Subscriber {

    private String playerName;
    private ConfigViewModel.Difficulty playerDifficulty;
    private int playerHealth;
    private Sprite playerSprite;
    private int playerScore;
    private int playerX = 1;
    private int playerY = 1;
    private boolean attacking;
    private boolean playerRoomChanged = false;
    private boolean finished = false;
    private boolean deathStatus = false;
    private MovementStrategy playerMoveStrategy;
    private Tile[][] currLayout;

    private static PlayerModel playerInstance;

    private PlayerCollisionPublisher collisionPublisher;
    private PlayerAttackPublisher attackPublisher;

    // default constructor
    private PlayerModel() {
    }

    public static PlayerModel getPlayer() {
        if (playerInstance == null) {
            synchronized (PlayerModel.class) {
                if (playerInstance == null) {
                    playerInstance = new PlayerModel();
                }
            }
        }
        return playerInstance;
    }

    // return player name
    public String getPlayerName() {
        return playerName;
    }

    // setter method for player name
    public void setPlayerName(String name) {
        playerName = name;
    }

    // return player difficulty
    public ConfigViewModel.Difficulty getPlayerDifficulty() {
        return playerDifficulty;
    }

    // setter method for player difficulty and health
    public void setPlayerDifficulty(ConfigViewModel.Difficulty difficulty) {
        playerDifficulty = difficulty;
    }

    // return player health
    public int getPlayerHealth() {
        return playerHealth;
    }

    // setter method for player health
    public void setPlayerHealth(int health) {
        if (health <= 0) {
            deathStatus = true;
        }
        playerHealth = health;
    }

    // return player sprite ID selection
    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    // setter method for sprite ID selection
    public void setPlayerSprite(Bitmap spriteBitmap) {
        this.playerSprite = new Sprite(spriteBitmap);
    }

    // return player Score
    public int getPlayerScore() {
        return playerScore;
    }

    // setter method for player Score
    public void setPlayerScore(int score) {
        if (score < 0) {
            playerScore = 0;
        } else {
            playerScore = score;
        }
    }
    public void setPlayerX(int x) {
        if (currLayout != null) {
            Tile currTile = currLayout[playerY][x];
            if (!currTile.getCollideable()) {
                playerX = x;
                collisionPublisher.setPlayerX(x);
                collisionPublisher.notifySubscribers();
            }
            if (currTile.getExit()) {
                playerY = 1;
                playerX = 1;
                playerRoomChanged = true;
                collisionPublisher.setPlayerX(1);
                collisionPublisher.notifySubscribers();
            }
            if (currTile.getFinalExit()) {
                finished = true;
            }
        }
    }

    // player collides with enemy method
    public void collision() {

        // 2. Move to start
        setPlayerX(1);
        setPlayerY(1);
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerY(int y) {
        if (currLayout != null) {
            Tile currTile = currLayout[y][playerX];
            if (!currTile.getCollideable()) {
                playerY = y;
                collisionPublisher.setPlayerY(y);
                collisionPublisher.notifySubscribers();
            }
            if (currTile.getExit()) {
                playerY = 1;
                playerX = 1;
                playerRoomChanged = true;
                collisionPublisher.setPlayerY(1);
                collisionPublisher.notifySubscribers();
            }
            if (currTile.getFinalExit()) {
                finished = true;
            }
        }
    }
    public int getPlayerY() {
        return playerY;
    }

    public boolean getDeathStatus() {
        return deathStatus;
    }

    public void setDeathStatus(boolean bool) {
        deathStatus = bool;
    }

    public void setPlayerMoveStrategy(MovementStrategy strategy) {
        playerMoveStrategy = strategy;
    }

    public boolean getRoomChanged() {
        return playerRoomChanged;
    }
    public boolean getFinished() {
        return finished;
    }

    public void moveUp() {
        playerMoveStrategy.moveUp();
    }

    public Tile[][] getPlayerLayout() {
        return this.currLayout;
    }
    public void setPlayerLayout(Tile[][] layout) {
        currLayout = layout;
    }

    public void moveDown() {
        playerMoveStrategy.moveDown();
    }
    public void moveLeft() {
        playerMoveStrategy.moveLeft();
    }

    public void moveRight() {
        playerMoveStrategy.moveRight();
    }
    public void setRoomChanged(boolean bool) {
        playerRoomChanged = bool;
    }
  
    public void setFinished(boolean bool) {
        finished = bool;
    }

    public boolean isAttacking() {
        return this.attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        this.attackPublisher.setAttacking(attacking);
        this.attackPublisher.notifySubscribers();
    }

    public void setCollisionPublisher(PlayerCollisionPublisher publisher) {
        collisionPublisher = publisher;
    }

    public void setAttackPublisher(PlayerAttackPublisher publisher) {
        this.attackPublisher = publisher;
    }

    @Override
    public void update(TileLayoutPublisher subject) {
        this.currLayout = subject.getCurrLayout();
    }
}
