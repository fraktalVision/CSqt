package com.csqt.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Actor {

    public enum Type { CIRCLE, SQUARE, TRIANGLE } // enum defining possible unit types

    private final float MAX_HEALTH = 100f; // maximum amount of health for every unit
    private final float DAMAGE = 10f; // base damage of every unit

    private Type mType; // which type the unit is
    private Sprite mSprite; // texture for the unit
    private float mCurrentHealth; // current health of the unit
    private Vector2 mPosition; // position of the unit
    private boolean isDestroyed; // bool for if the unit is destroyed
    private List<Unit> targets; // List of Units that the unit is targetting. The first on the list is the current target.
    private Attack attack; // Current attack by unit (Only one attack at a time)

    public static Unit init(Type unitType) {
        Unit unit = new Unit();

        unit.mType = unitType;
        unit.mSprite = Artist.unitSpriteFactory(unit.mType);
        unit.mPosition = new Vector2(unit.mSprite.getX(), unit.mSprite.getY());
        unit.mCurrentHealth = unit.MAX_HEALTH;
        unit.isDestroyed = false;
        unit.targets = new ArrayList<Unit>(3);

        return unit;
    }

    public void draw(SpriteBatch batch, float delta) {
        mSprite.draw(batch);

        while (attack != null && !(targets.isEmpty()) && attack.isActive()) {
            attack.animate(delta, targets.get(0).getPosition());
            attack.draw(batch);
        }
    }

    /**
     * attack method for units
     * @param enemy The enemy unit being attacked
     * @return returns ammount of damage done
     */
    public void attack(Unit enemy) {

        targets.add(enemy);

        attack = new Attack();

        attack.setLocation(mPosition);

        if (strongAgainst(mType, enemy.getType())) {
            // do 1.33 times damage
            enemy.takeDamage(DAMAGE * 1.33f);
        }
        else if (strongAgainst(enemy.getType(), mType)) {
            // do 0.5 times damage
            enemy.takeDamage(DAMAGE * 0.5f);
        }
        else
            enemy.takeDamage(DAMAGE);
    }

    private void takeDamage(float damage) {
        mCurrentHealth -= damage;

        if (mCurrentHealth <= 0)
            destroy();
    }

    private void destroy() {
        isDestroyed = true;
        mSprite = Artist.unitDestroyedSpriteFactory(mType);
    }

    private boolean strongAgainst(Type firstType, Type secondType) {

        if (firstType == Type.CIRCLE && secondType == Type.SQUARE) // circle beats square
            return true;
        else if (firstType == Type.SQUARE && secondType == Type.TRIANGLE) // square beats triangle
            return true;
        else if (firstType == Type.TRIANGLE && secondType == Type.CIRCLE) // triangle beats circle
            return true;
        else
            return false;
    }

    public Type getType() { return mType; }

    public Vector2 getPosition() { return mPosition; }

    public void setPosition(Vector2 newPosition) {
        mPosition = newPosition;
        Artist.setUnitLocation(mSprite, mPosition);
    }

    public Sprite getSprite() { return mSprite; }

    public boolean isDestroyed() { return isDestroyed; }

}
