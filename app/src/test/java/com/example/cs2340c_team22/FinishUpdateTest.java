package com.example.cs2340c_team22;

import static org.junit.Assert.assertEquals;

import com.example.cs2340c_team22.models.PlayerCollisionPublisher;
import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.TileLayoutPublisher;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.models.drawable.TileType;
import com.example.cs2340c_team22.viewmodels.KeyMovementStrategy;
import com.example.cs2340c_team22.viewmodels.MovementStrategy;

import org.junit.Test;


public class FinishUpdateTest {
    private final PlayerModel gameScreenCurr = PlayerModel.getPlayer();

    private PlayerCollisionPublisher collisionPublisher = new PlayerCollisionPublisher();
    private int playerY = 1;
    private int playerX = 1;
    private boolean finshed = false;
    private final TileLayoutPublisher publisher = new TileLayoutPublisher();
    private final TileType[][] roomDesign = new TileType[5][5];

    @Test
    public void roomUpdate() {
        gameScreenCurr.setCollisionPublisher(collisionPublisher);
        publisher.subscribe(gameScreenCurr);
        MovementStrategy keyMovement = new KeyMovementStrategy(gameScreenCurr);
        gameScreenCurr.setPlayerMoveStrategy(keyMovement);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 4) {
                    roomDesign[4][4] = TileType.FINAL_EXIT_TILE;
                } else {
                    roomDesign[i][j] = TileType.FLOOR_TILE;
                }

            }
        }

        Tile[][] parsedRoomLayout = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                /*
                    Bitmap bitmap = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.floor_1
                );*/
                parsedRoomLayout[i][j] = new Tile(null, false);
                if (roomDesign[i][j] == TileType.FINAL_EXIT_TILE) {
                    parsedRoomLayout[i][j].setFinalExit(true);
                }
                if (roomDesign[i][j] == TileType.EXIT_TILE) {
                    parsedRoomLayout[i][j].setExit(true);
                }
            }
        }
        gameScreenCurr.setPlayerLayout(parsedRoomLayout);

        assertEquals(false, finshed);
        setPlayerX(4);
        setPlayerY(4);
        assertEquals(true, finshed);

    }
    private void setPlayerLayout(Tile[][] layout) {
        publisher.setCurrLayout(layout);
        publisher.notifySubscribers();
    }

    private void setPlayerY(int y) {
        gameScreenCurr.setPlayerY(y);
        if (gameScreenCurr.getFinished()) {
            finshed = true;
        }
        playerY = gameScreenCurr.getPlayerY();
        playerX = gameScreenCurr.getPlayerX();
    }

    private void setPlayerX(int x) {
        gameScreenCurr.setPlayerX(x);
        if (gameScreenCurr.getFinished()) {
            finshed = true;
        }
        playerX = gameScreenCurr.getPlayerX();
        playerY = gameScreenCurr.getPlayerY();
    }
}

