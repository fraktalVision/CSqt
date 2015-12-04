package com.csqt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public final class Artist {

    private static Texture T_CIRCLE; // texture for circle units
    private static Texture T_CIRCLE_D; // texture for destroyed circle units
    private static Texture T_SQUARE; // texture for square units
    private static Texture T_SQUARE_D; // texture for destroyed square units
    private static Texture T_TRIANGLE; // texture for triangle units
    private static Texture T_TRIANGLE_D; // texture for destroyed triangle units
    private static Texture DEFAULT; // default badlogic texture
    private static Texture ATTACK; // texture for the units attack
    private static Texture BUTTON; // texture for the main menu buttons
    private static Vector3 COLOR; // vec3 for holding the RGB values of the background
    private static Camera CAMERA; // global camera for the game
    private static Viewport VIEWPORT; // global Viewport for the game

    private static final float UNIT_DIM = 7f; // dimensions of all unit sprites in game
    private static final float ATTK_DIM = 2f; // dimensions of all attack sprites
    private static final float BTN_H = 5f; // button sprite height
    private static final float BTN_W = 12f; // button sprite width;

    public static void init() {
        T_CIRCLE = new Texture("Circle.png"); // texture for circle units
        T_CIRCLE_D = new Texture("CircleDestroyed.png"); // texture for circle units
        T_SQUARE = new Texture("Square.png"); // texture for square units
        T_SQUARE_D = new Texture("SquareDestroyed.png"); // texture for square units
        T_TRIANGLE = new Texture("Triangle.png"); // texture for triangle units
        T_TRIANGLE_D = new Texture("TriangleDestroyed.png"); // texture for triangle units
        DEFAULT = new Texture("badlogic.jpg"); // default badlogic texture
        ATTACK = new Texture("attack.png"); // texture for the units attack
        BUTTON = new Texture("button.png"); // texture for the buttons
        COLOR = new Vector3(0, 0, 0);
        CAMERA = new OrthographicCamera(80, 45);
        VIEWPORT = new StretchViewport(80, 45, CAMERA);
        VIEWPORT.update(80, 45, true);
    }

    public static Sprite unitSpriteFactory(Unit.Type type) {
        Sprite sprite;

        switch (type) {
            case CIRCLE:
                sprite = new Sprite(T_CIRCLE, 256, 256);
                break;
            case SQUARE:
                sprite = new Sprite(T_SQUARE, 256, 256);
                break;
            case TRIANGLE:
                sprite = new Sprite(T_TRIANGLE, 256, 256);
                break;
            default:
                sprite = new Sprite(DEFAULT, 256, 256);
                break;
        }

        return sprite;
    }

    public static Sprite unitDestroyedSpriteFactory(Unit.Type type) {
        Sprite sprite;

        switch (type) {
            case CIRCLE:
                sprite = new Sprite(T_CIRCLE_D, 256, 256);
                break;
            case SQUARE:
                sprite = new Sprite(T_SQUARE_D, 256, 256);
                break;
            case TRIANGLE:
                sprite = new Sprite(T_TRIANGLE_D, 256, 256);
                break;
            default:
                sprite = new Sprite(DEFAULT, 256, 256);
                break;
        }

        return sprite;
    }

    public static Sprite attackSpriteFactory(float x, float y) {
        Sprite attack = new Sprite(ATTACK, 50, 50);
        attack.setBounds(x, y, ATTK_DIM, ATTK_DIM);
        return attack;
    }

    public static Sprite attackSpriteFactory() {
        return attackSpriteFactory(0, 0);
    }

    public static Sprite buttonSpriteFactory() {
        Sprite sprite = new Sprite(BUTTON, 480, 220);
        sprite.setSize(BTN_W, BTN_H);
        return sprite;
    }

    public static void setUnitLocation(Sprite sprite, Vector2 position) {
        sprite.setBounds(position.x, position.y, UNIT_DIM, UNIT_DIM);
    }

    public static void setAttackLocation(Sprite sprite, Vector2 position) {
        sprite.setBounds(position.x, position.y, ATTK_DIM, ATTK_DIM);
    }

    public static void setButtonLocation(Sprite sprite, Vector2 position) {
        sprite.setBounds(position.x, position.y, BTN_W, BTN_H);
    }

    public static void renderBackground() {
        // sets background color for the app
        Gdx.gl.glClearColor(1, 1, 1, 1);
        // glClear call
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void renderBackground(Texture texture) {
        renderBackground();

    }

    public static void renderBackground(float delta) {
        // extracts current color from vec3
        float r = COLOR.x;
        float g = COLOR.y;
        float b = COLOR.z;

        float x = delta * 5;

        // changes RGB values based on delta
        float d = (float) Math.floor((x * 200000) % 3);
        if (d == 0) {
            if (Math.round(r) == 0)
                r += ((float) Math.random() * x) % 1f;
            else if (Math.round(r) == 1)
                r -= ((float) Math.random() * x) % 1f;
        }
        if (d == 1) {
            if (Math.round(g) == 0)
                g += ((float) Math.random() * x) % 1f;
            else if (Math.round(g) == 1)
                g -= ((float) Math.random() * x) % 1f;
        }
        if (d == 2) {
            if (Math.round(b) == 0)
                b += ((float) Math.random() * x) % 1f;
            else if (Math.round(b) == 1)
                b -= ((float) Math.random() * x) % 1f;
        }

        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        COLOR.x = r;
        COLOR.y = g;
        COLOR.z = b;
    }

    public static void dispose() {
        T_CIRCLE.dispose();
        T_SQUARE.dispose();
        T_TRIANGLE.dispose();
        DEFAULT.dispose();
        ATTACK.dispose();
    }

    public static float getUnitDim() {
        return UNIT_DIM;
    }

    public static float getButtonWidth() { return BTN_W; }

    public static float getButtonHeight() { return BTN_H; }

    public static Camera getCamera() { return CAMERA; }

    public static Viewport getViewport() { return VIEWPORT; }

}
