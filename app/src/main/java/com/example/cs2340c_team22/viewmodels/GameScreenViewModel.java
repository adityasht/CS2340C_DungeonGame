package com.example.cs2340c_team22.viewmodels;

import static com.example.cs2340c_team22.models.drawable.RoomLayouts.NUM_ROOMS;
import static com.example.cs2340c_team22.models.drawable.RoomLayouts.ROOM_COL_TILES;
import static com.example.cs2340c_team22.models.drawable.RoomLayouts.ROOM_ROW_TILES;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.models.FreezeEnemiesPotionDecorator;
import com.example.cs2340c_team22.models.GamePowerUp;
import com.example.cs2340c_team22.models.HealthPotionDecorator;
import com.example.cs2340c_team22.models.PlayerAttackPublisher;
import com.example.cs2340c_team22.models.PlayerAttackSubscriber;
import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerCollisionSubscriber;
import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.ScorePotionDecorator;
import com.example.cs2340c_team22.models.TileLayoutPublisher;
import com.example.cs2340c_team22.models.drawable.Room;
import com.example.cs2340c_team22.models.drawable.RoomLayouts;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.models.drawable.TileType;
import com.example.cs2340c_team22.models.enemy.Enemy;
import com.example.cs2340c_team22.models.enemy.EnemyCreator;

public class GameScreenViewModel extends ViewModel {

    private final PlayerModel gameScreenCurr;
    private final Room[] rooms;
    private int currentRoomNum;
    private boolean finished = false;

    private final MutableLiveData<String> playerName = new MutableLiveData<>();
    private final MutableLiveData<ConfigViewModel.Difficulty> playerDifficulty =
        new MutableLiveData<>();
    private final MutableLiveData<Integer> playerHealth = new MutableLiveData<>();
    private final MutableLiveData<Integer> playerScore = new MutableLiveData<>();
    private int playerX;
    private int playerY;

    private final TileLayoutPublisher publisher;
    private final PlayerCollisionPublisher collisionPublisher;
    private final PlayerAttackPublisher attackPublisher;

    private EnemyCreator enemyCreator = new EnemyCreator();

    private Enemy[] enemies;
    private GamePowerUp[] powerUps;


    // constructor initializes model object
    public GameScreenViewModel() {
        gameScreenCurr = PlayerModel.getPlayer();
        this.currentRoomNum = 0;
        this.rooms = new Room[NUM_ROOMS];
        publisher = new TileLayoutPublisher();
        publisher.subscribe(gameScreenCurr);
        this.enemies = new Enemy[2];
        collisionPublisher = new PlayerCollisionPublisher();
        this.attackPublisher = new PlayerAttackPublisher();
      
        this.powerUps = new GamePowerUp[3];
    }

    // method to initialize data in model
    public void init(String playerName, ConfigViewModel.Difficulty difficulty,
                     Bitmap playerSpriteBitmap, int playerScore, int x, int y) {
        gameScreenCurr.setPlayerName(playerName);
        gameScreenCurr.setPlayerDifficulty(difficulty);
        gameScreenCurr.setPlayerSprite(playerSpriteBitmap);
        gameScreenCurr.setPlayerHealth((int) (10 * difficulty.getValue()));
        setPlayerHealth((int) (10 * difficulty.getValue()));
        gameScreenCurr.setPlayerScore(playerScore);
        gameScreenCurr.setPlayerX(x);
        gameScreenCurr.setPlayerY(y);
        MovementStrategy keyMovement = new KeyMovementStrategy(gameScreenCurr);
        gameScreenCurr.setPlayerMoveStrategy(keyMovement);
        collisionPublisher.setPlayerX(x);
        collisionPublisher.setPlayerY(y);
        gameScreenCurr.setCollisionPublisher(collisionPublisher);
        gameScreenCurr.setAttackPublisher(attackPublisher);

        this.playerName.setValue(gameScreenCurr.getPlayerName());
        this.playerDifficulty.setValue(gameScreenCurr.getPlayerDifficulty());
        this.playerHealth.setValue(gameScreenCurr.getPlayerHealth());
        this.playerScore.setValue(gameScreenCurr.getPlayerScore());
        this.playerX = gameScreenCurr.getPlayerX();
        this.playerY = gameScreenCurr.getPlayerY();
        this.finished = gameScreenCurr.getFinished();

        // initialize enemies for enemy list
        enemies[0] = enemyCreator.createEnemy("orc");
        enemies[1] = enemyCreator.createEnemy("zombie");
        collisionPublisher.subscribe((PlayerCollisionSubscriber) enemies[0]);
        collisionPublisher.subscribe((PlayerCollisionSubscriber) enemies[1]);
        attackPublisher.subscribe((PlayerAttackSubscriber) enemies[0]);
        attackPublisher.subscribe((PlayerAttackSubscriber) enemies[1]);
        initEnemies();
        initPowerUps();
    }

    public void initializeMap(Resources resources) {

        int roomNum = 0;
        for (TileType[][] roomLayout : RoomLayouts.ROOM_LAYOUTS) {
            Tile[][] parsedRoomLayout = new Tile[ROOM_ROW_TILES][ROOM_COL_TILES];

            for (int iRow = 0; iRow < ROOM_ROW_TILES; iRow++) {
                for (int iCol = 0; iCol < ROOM_COL_TILES; iCol++) {

                    int tileId;
                    switch (roomLayout[iRow][iCol]) {
                    case FLOOR_TILE_2:
                        tileId = R.drawable.floor_2;
                        break;
                    case FLOOR_TILE_3:
                        tileId = R.drawable.floor_3;
                        break;
                    case EXIT_TILE:
                        tileId = R.drawable.floor_ladder;
                        break;
                    case FINAL_EXIT_TILE:
                        tileId = R.drawable.floor_stairs;
                        break;
                    case WALL_TILE:
                        tileId = R.drawable.wall_mid;
                        break;
                    default:
                        tileId = R.drawable.floor_1;
                        break;
                    }

                    Bitmap bitmap = BitmapFactory.decodeResource(
                        resources,
                        tileId
                    );
                    parsedRoomLayout[iRow][iCol] = new Tile(bitmap, false);
                    if (roomLayout[iRow][iCol] == TileType.WALL_TILE) {
                        parsedRoomLayout[iRow][iCol].setCollideable(true);
                    }
                    if (roomLayout[iRow][iCol] == TileType.EXIT_TILE) {
                        parsedRoomLayout[iRow][iCol].setExit(true);
                        parsedRoomLayout[iRow][iCol].setCollideable(false);
                    }
                    if (roomLayout[iRow][iCol] == TileType.FINAL_EXIT_TILE) {
                        parsedRoomLayout[iRow][iCol].setFinalExit(true);
                        parsedRoomLayout[iRow][iCol].setCollideable(false);
                    }
                }
            }

            this.rooms[roomNum] = new Room(parsedRoomLayout);
            roomNum++;
        }
    }

    public Room getCurrentRoom() {
        return this.rooms[currentRoomNum];
    }

    public void advanceRoom() {
        if (currentRoomNum >= NUM_ROOMS - 1) {
            currentRoomNum = NUM_ROOMS - 1;
        } else {
            currentRoomNum++;
        }
        // update list of enemies
        // unsubscribe old enemies
        collisionPublisher.unsubscribe((PlayerCollisionSubscriber) enemies[0]);
        collisionPublisher.unsubscribe((PlayerCollisionSubscriber) enemies[1]);
        attackPublisher.unsubscribe((PlayerAttackSubscriber) enemies[0]);
        attackPublisher.unsubscribe((PlayerAttackSubscriber) enemies[1]);
        if (currentRoomNum == 1) {
            enemies[0] = enemyCreator.createEnemy("demon");
            enemies[1] = enemyCreator.createEnemy("pumpkin");
            initEnemies();
            initPowerUps();
        }
        if (currentRoomNum == 2) {
            enemies[0] = enemyCreator.createEnemy("orc");
            enemies[1] = enemyCreator.createEnemy("demon");
            initEnemies();
            initPowerUps();
        }
        collisionPublisher.subscribe((PlayerCollisionSubscriber) enemies[0]);
        collisionPublisher.subscribe((PlayerCollisionSubscriber) enemies[1]);
        attackPublisher.subscribe((PlayerAttackSubscriber) enemies[0]);
        attackPublisher.subscribe((PlayerAttackSubscriber) enemies[1]);

    }

    public void setPlayerLayout(Tile[][] layout) {
        publisher.setCurrLayout(layout);
        publisher.notifySubscribers();
    }

    // Decrement score by 1 every second
    public void startScoreDecrement() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                int playerScore = getPlayerScore().getValue();
                playerScore--;
                setPlayerScore(playerScore);

                if (playerScore > 0) {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    // set starting x and y for enemies
    private void initEnemies() {
        enemies[0].setCurrX(1);
        enemies[0].setCurrY(7);
        enemies[1].setCurrX(4);
        enemies[1].setCurrY(1);
    }

    // reset powerups for when rooms change
    private void initPowerUps() {
        // powerUp[0] will be health power up
        GamePowerUp healthPowerUp = new GamePowerUp();
        HealthPotionDecorator healthPotion = new HealthPotionDecorator(healthPowerUp);
        healthPotion.setUp();
        powerUps[0] = healthPowerUp;

        // powerUp[1] will be score power up
        GamePowerUp scorePowerUp = new GamePowerUp();
        ScorePotionDecorator scoreDecorator = new ScorePotionDecorator(scorePowerUp);
        scoreDecorator.setUp();
        powerUps[1] = scorePowerUp;

        // powerUp[2] will be freeze enemies
        GamePowerUp freezePowerUp = new GamePowerUp();
        FreezeEnemiesPotionDecorator freeze = new FreezeEnemiesPotionDecorator(freezePowerUp);
        freeze.setUp();
        powerUps[2] = freezePowerUp;
    }

    public void checkEnemyCollisions() {
        for (Enemy e: enemies) {
            if (e.getEnemyHealth() > 0 && e.checkCollision()) {
                gameScreenCurr.collision();
                setPlayerY(1);
                setPlayerX(1);
                // reduce health
                double diff = gameScreenCurr.getPlayerDifficulty().getValue();
                int healthReduction = (int) (1 / diff);
                if (diff != 1) {
                    healthReduction += 1;
                }
                setPlayerHealth(gameScreenCurr.getPlayerHealth() - healthReduction);
            }
        }
    }

    // check collisions for powerUps
    public void checkPowerUps() {
        // check for powerup 1 (health)
        if (!powerUps[0].getHasBeenUsed() && gameScreenCurr.getPlayerX() == 5
                && gameScreenCurr.getPlayerY() == 8) {
            setPlayerHealth(gameScreenCurr.getPlayerHealth() + 5);
            setPlayerScore(getPlayerScore().getValue() - 5);
            powerUps[0].setHasBeenUsed(true);
        }
        // check for power up 2 (score)
        if (!powerUps[1].getHasBeenUsed() && gameScreenCurr.getPlayerX() == 1
                && gameScreenCurr.getPlayerY() == 12) {
            setPlayerScore(getPlayerScore().getValue() + 10);
            powerUps[1].setHasBeenUsed(true);
        }

        if (!powerUps[2].getHasBeenUsed() && gameScreenCurr.getPlayerX() == 10
                && gameScreenCurr.getPlayerY() == 1) {
            // freeze enemies
            for (Enemy e: enemies) {
                e.setIsFrozen(true);
                powerUps[2].setHasBeenUsed(true);
            }
        }
    }

    public void checkEnemyAttacked() {
        for (Enemy e : this.enemies) {
            if (e.getEnemyHealth() > 0 && e.checkAttacked()) {
                e.setEnemyHealth(0);
                // Increase player points?
            }
        }
    }

    public void update() {

    }

    // return player name
    public MutableLiveData<String> getPlayerName() {
        return playerName;
    }

    // setter method for player name
    public void setPlayerName(String name) {
        gameScreenCurr.setPlayerName(name);
        playerName.setValue(gameScreenCurr.getPlayerName());
    }

    // return player difficulty
    public MutableLiveData<ConfigViewModel.Difficulty> getPlayerDifficulty() {
        return playerDifficulty;
    }

    // setter method for difficulty, automatically updates health
    public void setPlayerDifficulty(ConfigViewModel.Difficulty difficulty) {
        gameScreenCurr.setPlayerDifficulty(difficulty);
        playerDifficulty.setValue(gameScreenCurr.getPlayerDifficulty());
        int newHealth = (int) (10 * playerDifficulty.getValue().getValue());
        gameScreenCurr.setPlayerHealth(newHealth);
        setPlayerHealth(newHealth);
        playerHealth.setValue(gameScreenCurr.getPlayerHealth());
    }

    // return player health
    public MutableLiveData<Integer> getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int health) {
        gameScreenCurr.setPlayerHealth(health);
        playerHealth.postValue(health);
    }

    // return player sprite ID selection
    public Bitmap getPlayerSpriteBitmap() {
        return gameScreenCurr.getPlayerSprite().getBitmap();
    }

    // getter and setter for player Score
    public MutableLiveData<Integer> getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int score) {
        gameScreenCurr.setPlayerScore(score);
        playerScore.setValue(gameScreenCurr.getPlayerScore());
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int y) {
        gameScreenCurr.setPlayerY(y);
        // check enemy collisions
        checkEnemyCollisions();
        checkPowerUps();
        if (gameScreenCurr.getRoomChanged()) {
            advanceRoom();
            gameScreenCurr.setRoomChanged(false);
        }
        playerY = gameScreenCurr.getPlayerY();
        playerX = gameScreenCurr.getPlayerX();
        finished = gameScreenCurr.getFinished();
    }

    public void setPlayerX(int x) {
        gameScreenCurr.setPlayerX(x);
        checkEnemyCollisions();
        checkPowerUps();
        if (gameScreenCurr.getRoomChanged()) {
            advanceRoom();
            gameScreenCurr.setRoomChanged(false);
        }
        playerX = gameScreenCurr.getPlayerX();
        playerY = gameScreenCurr.getPlayerY();
        finished = gameScreenCurr.getFinished();
    }

    public float getPlayerX() {
        return playerX;
    }
    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean bool) {
        finished = bool;
        gameScreenCurr.setFinished(bool);
    }

    public void moveDown() {
        gameScreenCurr.moveDown();
        setPlayerY(gameScreenCurr.getPlayerY());
    }

    public void moveUp() {
        gameScreenCurr.moveUp();
        setPlayerY(gameScreenCurr.getPlayerY());
    }

    public void moveLeft() {
        gameScreenCurr.moveLeft();
        setPlayerX(gameScreenCurr.getPlayerX());
    }

    public void moveRight() {
        gameScreenCurr.moveRight();
        setPlayerX(gameScreenCurr.getPlayerX());
    }

    public boolean isPlayerAttacking() {
        return this.gameScreenCurr.isAttacking();
    }

    public void toggleAttacking() {
        this.gameScreenCurr.setAttacking(!this.gameScreenCurr.isAttacking());
    }

    public Enemy[] getEnemies() {
        return enemies;
    }
    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public void updateEnemyPositions() {
        for (Enemy enemy : enemies) {
            if (enemy.getEnemyHealth() <= 0) {
                continue;
            }

            enemy.move();
        }
    }

    public GamePowerUp[] getPowerUps() {
        return powerUps;
    }
}
