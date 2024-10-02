package com.example.cs2340c_team22.models.drawable;

import android.graphics.Bitmap;

public class Sprite {
    private final Bitmap bitmap;

    public Sprite(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
