package com.csqt.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Attack extends Actor {

    private final float SPEED = 10f;

    private Vector2 mPosition;
    private Sprite mSprite;
    private boolean isActive;

    public Attack() {
        mSprite = Artist.attackSpriteFactory();
        mPosition = new Vector2(mSprite.getX(), mSprite.getY());
        isActive = true;
    }

    public void setLocation(Vector2 location) {
        mPosition.x = location.x;
        mPosition.y = location.y;

        Artist.setAttackLocation(mSprite, mPosition);
    }

    public void draw(SpriteBatch batch) {
        mSprite.draw(batch);
    }

    public void animate(float delta, Vector2 enemyPosition) {
        float movement = delta * SPEED;

        mPosition.x += movement;
        mPosition.y += movement;

        Vector2 collision = new Vector2(
                enemyPosition.x + (Artist.getUnitDim() / 2),
                enemyPosition.y + (Artist.getUnitDim() / 2)
        );

        if (mPosition == collision) {
            isActive = false;
        }

        Artist.setAttackLocation(mSprite, mPosition);
    }

    public Vector2 getPosition() { return mPosition; }

    public void setPosition(Vector2 newPosition) {
        mPosition = newPosition;
        Artist.setAttackLocation(mSprite, mPosition);
    }

    public float getSpeed() { return SPEED; }

    public boolean isActive() { return isActive; }

    public Sprite getSprite() { return mSprite; }

}
