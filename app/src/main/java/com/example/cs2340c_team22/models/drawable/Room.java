package com.example.cs2340c_team22.models.drawable;

public class Room {
    private final Tile[][] layout;

    public Room(Tile[][] parsedRoomLayout) {
        this.layout = parsedRoomLayout;
    }

    public Tile[][] getLayout() {
        return this.layout;
    }
}
