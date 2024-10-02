package com.example.cs2340c_team22;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.lang.reflect.Method;

import com.example.cs2340c_team22.models.PlayerModel;
import com.example.cs2340c_team22.viewmodels.KeyMovementStrategy;
import com.example.cs2340c_team22.viewmodels.MovementStrategy;

import org.junit.Test;

public class StrategyPatternTest {


    @Test
    public void testStrategyPatternContainsMoveLeft() {
        Class<?> strategyClass = KeyMovementStrategy.class;
        try {
            // Get the moveLeft method
            Method moveLeftMethod = strategyClass.getDeclaredMethod("moveLeft");
            // Check if the return type of the method is void
            assertEquals(void.class, moveLeftMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            fail("moveLeft method does not exist in KeyMovementStrategy");
        }
    }

    @Test
    public void testStrategyPatternContainsMoveRight() {
        Class<?> strategyClass = KeyMovementStrategy.class;
        try {
            // Get the moveRight method
            Method moveRightMethod = strategyClass.getDeclaredMethod("moveRight");
            // Check if the return type of the method is void
            assertEquals(void.class, moveRightMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            fail("moveRight method does not exist in KeyMovementStrategy");
        }
    }
    @Test
    public void testStrategyPatternContainsMoveUp() {
        Class<?> strategyClass = KeyMovementStrategy.class;
        try {
            // Get the moveUp method
            Method moveUpMethod = strategyClass.getDeclaredMethod("moveUp");
            // Check if the return type of the method is void
            assertEquals(void.class, moveUpMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            fail("moveUp method does not exist in KeyMovementStrategy");
        }
    }
    @Test
    public void testStrategyPatternContainsMoveDown() {
        Class<?> strategyClass = KeyMovementStrategy.class;
        try {
            // Get the moveRight method
            Method moveDownMethod = strategyClass.getDeclaredMethod("moveDown");
            // Check if the return type of the method is void
            assertEquals(void.class, moveDownMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            fail("moveDown method does not exist in KeyMovementStrategy");
        }
    }

    @Test
    public void testStrategyPatternMethod() {
        PlayerModel player = PlayerModel.getPlayer();
        player.setPlayerName("Name");
        KeyMovementStrategy strategyModel = new KeyMovementStrategy(player);
        assertTrue(strategyModel instanceof MovementStrategy);
    }
}
