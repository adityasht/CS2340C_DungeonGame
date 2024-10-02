package com.example.cs2340c_team22.views;

import static com.example.cs2340c_team22.models.drawable.RoomLayouts.ROOM_COL_TILES;
import static com.example.cs2340c_team22.models.drawable.RoomLayouts.ROOM_ROW_TILES;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.cs2340c_team22.R;
import com.example.cs2340c_team22.models.drawable.Tile;
import com.example.cs2340c_team22.models.enemy.Enemy;
import com.example.cs2340c_team22.viewmodels.GameScreenViewModel;

@SuppressLint("ViewConstructor")
public class TilemapGameView extends SurfaceView implements SurfaceHolder.Callback {
    private final GameScreenViewModel gameScreenViewModel;
    private final GameLoop gameLoop;

    public TilemapGameView(Context context, GameScreenViewModel gameScreenViewModel) {
        super(context);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        this.gameScreenViewModel = gameScreenViewModel;
        this.gameLoop = new GameLoop(holder, this);
    }

    protected void update() {
        this.gameScreenViewModel.updateEnemyPositions();
        this.gameScreenViewModel.update();
        this.gameScreenViewModel.checkEnemyCollisions();
        this.gameScreenViewModel.checkPowerUps();
        this.gameScreenViewModel.checkEnemyAttacked();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Tile[][] room = this.gameScreenViewModel.getCurrentRoom().getLayout();
        gameScreenViewModel.setPlayerLayout(room);
        for (int iRow = 0; iRow < ROOM_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < ROOM_COL_TILES; iCol++) {
                Matrix transform = new Matrix();
                transform.preTranslate(
                    iCol * Tile.TILE_WIDTH_PX * Tile.TILE_SCALE,
                    iRow * Tile.TILE_WIDTH_PX * Tile.TILE_SCALE
                );
                transform.preScale(Tile.TILE_SCALE, Tile.TILE_SCALE);
                canvas.drawBitmap(room[iRow][iCol].getSprite().getBitmap(), transform, null);
            }
        }
        Matrix transform = new Matrix();
        transform.postScale(2.25f, 2.25f);
        transform.postTranslate(
            gameScreenViewModel.getPlayerX() * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
            gameScreenViewModel.getPlayerY() * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
        );
        canvas.drawBitmap(
            this.gameScreenViewModel.getPlayerSpriteBitmap(), transform, null);
        if (this.gameScreenViewModel.isPlayerAttacking()) {
            Bitmap weaponBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.weapon_knight_sword
            );
            for (int i = 0; i < 4; i++) {
                Matrix weaponTransform = new Matrix();
                weaponTransform.postScale(2.25f, 2.25f);
                if (i == 0) {
                    weaponTransform.preRotate(90);
                    weaponTransform.postTranslate(
                        (gameScreenViewModel.getPlayerX() + 1.5f)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                        (gameScreenViewModel.getPlayerY() + 0.5f)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
                    );
                } else if (i == 1) {
                    weaponTransform.preRotate(270);
                    weaponTransform.postTranslate(
                        (gameScreenViewModel.getPlayerX() - 1)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                        (gameScreenViewModel.getPlayerY() + 0.75f)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
                    );
                } else if (i == 2) {
                    weaponTransform.preRotate(180);
                    weaponTransform.postTranslate(
                        (gameScreenViewModel.getPlayerX() + 0.5f)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                        (gameScreenViewModel.getPlayerY() + 2)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
                    );
                } else {
                    weaponTransform.postTranslate(
                        (gameScreenViewModel.getPlayerX() + 0.25f)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                        (gameScreenViewModel.getPlayerY() - 0.75f)
                            * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
                    );
                }
                canvas.drawBitmap(weaponBitmap, weaponTransform, null);
            }
        }
        Enemy enemy1 = gameScreenViewModel.getEnemies()[0];
        Enemy enemy2 = gameScreenViewModel.getEnemies()[1];
        if (enemy1.getEnemyHealth() > 0) {
            // Draw enemy1
            Matrix transformEnemy1 = new Matrix();
            transformEnemy1.postScale(2.25f, 2.25f);
            transformEnemy1.postTranslate(
                gameScreenViewModel.getEnemies()[0].getCurrX()
                    * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                gameScreenViewModel.getEnemies()[0].getCurrY()
                    * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
            );
            // Get enemy1 bitmap
            Bitmap b1 = BitmapFactory.decodeResource(this.getResources(),
                this.gameScreenViewModel.getEnemies()[0].getEnemySprite());
            canvas.drawBitmap(b1, transformEnemy1, null);
        }
        if (enemy2.getEnemyHealth() > 0) {
            // Draw enemy2
            Matrix transformEnemy2 = new Matrix();
            transformEnemy2.postScale(2.0f, 2.0f);
            transformEnemy2.postTranslate(
                gameScreenViewModel.getEnemies()[1].getCurrX()
                    * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                gameScreenViewModel.getEnemies()[1].getCurrY()
                    * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
            );
            // Get enemy2 bitmap
            Bitmap b2 = BitmapFactory.decodeResource(this.getResources(),
                this.gameScreenViewModel.getEnemies()[1].getEnemySprite());
            canvas.drawBitmap(b2, transformEnemy2, null);
        }
        // Create matrix for power up 1
        Matrix transformPowerUp1 = new Matrix();
        transformPowerUp1.postScale(2.0f, 2.0f);
        transformPowerUp1.postTranslate(
                this.gameScreenViewModel.getPowerUps()[0].getX()
                        * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                this.gameScreenViewModel.getPowerUps()[0].getY()
                        * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
        );
        // Draw power up 1 onto game
        Bitmap pu1 = BitmapFactory.decodeResource(this.getResources(),
                this.gameScreenViewModel.getPowerUps()[0].getPowerUpSprite());
        if (!this.gameScreenViewModel.getPowerUps()[0].getHasBeenUsed()) {
            canvas.drawBitmap(pu1, transformPowerUp1, null);
        }
        // Create matrix for power up 2
        Matrix transformPowerUp2 = new Matrix();
        transformPowerUp2.postScale(2.0f, 2.0f);
        transformPowerUp2.postTranslate(
                this.gameScreenViewModel.getPowerUps()[1].getX()
                        * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                this.gameScreenViewModel.getPowerUps()[1].getY()
                        * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
        );
        // Draw power up 2 onto game
        Bitmap pu2 = BitmapFactory.decodeResource(this.getResources(),
                this.gameScreenViewModel.getPowerUps()[1].getPowerUpSprite());

        if (!this.gameScreenViewModel.getPowerUps()[1].getHasBeenUsed()) {
            canvas.drawBitmap(pu2, transformPowerUp2, null);
        }
        // Create matrix for power up 3
        Matrix transformPowerUp3 = new Matrix();
        transformPowerUp3.postScale(2.0f, 2.0f);
        transformPowerUp3.postTranslate(
                this.gameScreenViewModel.getPowerUps()[2].getX()
                        * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE),
                this.gameScreenViewModel.getPowerUps()[2].getY()
                        * (Tile.TILE_WIDTH_PX * Tile.TILE_SCALE)
        );
        // Draw power up 3 onto game
        Bitmap pu3 = BitmapFactory.decodeResource(this.getResources(),
                this.gameScreenViewModel.getPowerUps()[2].getPowerUpSprite());
        if (!this.gameScreenViewModel.getPowerUps()[2].getHasBeenUsed()) {
            canvas.drawBitmap(pu3, transformPowerUp3, null);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("TILEMAP GAME VIEW", "SURFACE CREATED");
        this.gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("TILEMAP GAME VIEW", "SURFACE DESTROYED");
        this.gameLoop.stopLoop();
    }

    public void stop() {
        this.gameLoop.stopLoop();
    }

    // This has to be in a thread so that the loop
    // runs separately and doesn't
    private static class GameLoop extends Thread {
        private boolean isRunning;
        private final SurfaceHolder holder;
        private final TilemapGameView tilemapGameView;
        private int enemyMoveCounter = 0;

        public GameLoop(SurfaceHolder holder, TilemapGameView tilemapGameView) {
            this.holder = holder;
            this.tilemapGameView = tilemapGameView;
            this.isRunning = false;
        }

        public void startLoop() {
            this.isRunning = true;
            this.start();
        }

        public void stopLoop() {
            this.isRunning = false;

            try {
                this.join();
            } catch (InterruptedException ignored) {
            }
        }

        @Override
        public void run() {
            super.run();

            Canvas canvas = null;
            while (this.isRunning) {
                try {
                    canvas = this.holder.lockCanvas();

                    // Check counter variable
                    if (enemyMoveCounter >= 10) {
                        this.tilemapGameView.update();
                        enemyMoveCounter = 0;
                    }

                    // Increment counter variable
                    enemyMoveCounter++;

                    // Get updated values

                    // Draw
                    this.tilemapGameView.draw(canvas);

                    // Unlock canvas
                } catch (IllegalArgumentException ignored) {
                } finally {
                    if (canvas != null) {
                        try {
                            this.holder.unlockCanvasAndPost(canvas);
                        } catch (IllegalArgumentException ignored) {
                        }
                    }
                }
            }
        }
    }
}
