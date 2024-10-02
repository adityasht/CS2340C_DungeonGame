package com.example.cs2340c_team22;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340c_team22.models.ConfigModel;
public class NameUnitTests {
    @Test
    public void nameIsNull() {
        ConfigModel test = ConfigModel.getInstance();
        boolean returnedName = test.setPlayerName(null);
        assertEquals(false, returnedName);
    }
    @Test
    public void nameIsWhiteSpace() {
        ConfigModel test = ConfigModel.getInstance();
        boolean returnedName = test.setPlayerName("");
        assertEquals(false, returnedName);
    }
}