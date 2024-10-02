package com.example.cs2340c_team22.models.drawable;

import android.graphics.Bitmap;

public class Tile {
    public static final float TILE_SCALE = 4.95f;
    public static final int TILE_WIDTH_PX = 16;

    private final Sprite sprite;

    private boolean isCollideable;
    private boolean isExit;
    private boolean isFinalExit;

    public Tile(Bitmap spriteBitmap, boolean isCollideable) {
        this(spriteBitmap, isCollideable, false, false);
    }

    public Tile(Bitmap spriteBitmap, boolean isCollideable, boolean isExit, boolean isFinalExit) {
        this.sprite = new Sprite(spriteBitmap);
        this.isCollideable = isCollideable;
        this.isExit = isExit;
        this.isFinalExit = isFinalExit;
    }

    public boolean getCollideable() {
        return isCollideable;
    }

    public void setCollideable(boolean isCollideable) {
        this.isCollideable = isCollideable;
    }

    public boolean getExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getFinalExit() {
        return isFinalExit;
    }

    public void setFinalExit(boolean isFinalExit) {
        this.isFinalExit = isFinalExit;
    }

    public Sprite getSprite() {
        return this.sprite;
    }
}
