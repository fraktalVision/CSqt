package com.csqt.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class User {

    protected final int MAX_UNITS = 3; // max amount of units of each type that a player can have
    protected int circleCount; // tracks how many circle units the user has left
    protected int triangleCount; // tracks how many triangle units the user has left
    protected int squareCount; // tracks how many square units the user has left
    protected Unit[] unitOrder; // keeps the order of unit placement on board for easy reference
    protected Vector2[] unitPositions; // keeps the order of unit positions. in line with unitOrder array

    public User() {
        circleCount = MAX_UNITS;
        triangleCount = MAX_UNITS;
        squareCount = MAX_UNITS;
    }

    public abstract void placeUnit(Unit.Type unitType, int arrayPosition, float screenPosX, float screenPosY);

    public Unit getUnitAt(int index) {

        if (index >= 0 && index < unitOrder.length)
            return unitOrder[index];

        return null;
    }

    public Unit.Type getUnitTypeAt(int index) {

        Unit unit = getUnitAt(index);

        if (unit != null)
            return unit.getType();

        return null;

    }

    public Sprite getUnitSpriteAt(int index) {

        Unit unit = getUnitAt(index);

        if (unit != null)
            return unit.getSprite();

        return null;
    }

    public Vector2 getUnitPositionAt(int index) {

        if (index >= 0 && index < unitPositions.length)
            return unitPositions[index];

        return null;
    }
}
