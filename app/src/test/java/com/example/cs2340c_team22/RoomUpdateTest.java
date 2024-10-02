package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.models.TileLayoutPublisher;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.viewmodels.KeyMovementStrategy;
import com.example.cs2340c_team22.models.drawable.TileType;
import com.example.cs2340c_team22.viewmodels.MovementStrategy;


public class RoomUpdateTest {
    private final PlayerModel gameScreenCurr = PlayerModel.getPlayer();
    private int playerY = 1;
    private int playerX = 1;
    private boolean roomUpdate = false;
    private final TileLayoutPublisher publisher = new TileLayoutPublisher();
    private final TileType[][] roomDesign = new TileType[5][5];

    @Test
    public void roomUpdate() {
        publisher.subscribe(gameScreenCurr);
        MovementStrategy keyMovement = new KeyMovementStrategy(gameScreenCurr);
        gameScreenCurr.setPlayerMoveStrategy(keyMovement);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 4) {
                    roomDesign[4][4] = TileType.EXIT_TILE;
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

        assertEquals(false, roomUpdate);
        setPlayerX(4);
        setPlayerY(4);
        assertEquals(true, roomUpdate);

    }
    private void setPlayerLayout(Tile[][] layout) {
        publisher.setCurrLayout(layout);
        publisher.notifySubscribers();
    }

    private void setPlayerY(int y) {
        gameScreenCurr.setPlayerY(y);
        if (gameScreenCurr.getRoomChanged()) {
            roomUpdate = true;
            gameScreenCurr.setRoomChanged(false);
        }
        playerY = gameScreenCurr.getPlayerY();
        playerX = gameScreenCurr.getPlayerX();
    }

    private void setPlayerX(int x) {
        gameScreenCurr.setPlayerX(x);
        if (gameScreenCurr.getRoomChanged()) {
            roomUpdate = true;
            gameScreenCurr.setRoomChanged(false);
        }
        playerX = gameScreenCurr.getPlayerX();
        playerY = gameScreenCurr.getPlayerY();
    }
}

