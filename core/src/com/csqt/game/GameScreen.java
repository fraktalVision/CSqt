package com.csqt.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class GameScreen implements Screen {

    private SpriteBatch mBatch;
    private OrthographicCamera mCamera;
    private Unit[] playerUnits;
    private Unit[] enemyUnits;

    private final Map<Integer, Vector2> mPlayerUnitPositions = new HashMap<Integer, Vector2>();
    private final Map<Integer, Vector2> mEnemyUnitPositions = new HashMap<Integer, Vector2>();

    private CSqt mGame;

    public GameScreen(CSqt game) {
        this.mGame = game;
        mBatch = new SpriteBatch();
        mCamera = new OrthographicCamera(80, 45);
        mBatch.setProjectionMatrix(mCamera.combined);
        playerUnits = new Unit[5];
        enemyUnits = new Unit[5];

        for (int i = 0; i < 5; ++i) {
            float y = (i * 9) - 21;
            if (i % 2 == 0) {
                mPlayerUnitPositions.put(i, new Vector2(-35, y));
                mEnemyUnitPositions.put(i, new Vector2(25, y));
            } else {
                mPlayerUnitPositions.put(i, new Vector2(-25, y));
                mEnemyUnitPositions.put(i, new Vector2(15, y));
            }
        }

        for (int i = 0; i < 5; ++i) {
            playerUnits[i] = Unit.init(Unit.Type.CIRCLE);
        }

        for (int i = 0; i < enemyUnits.length; ++i) {
            enemyUnits[i] = Unit.init(Unit.Type.TRIANGLE);
        }

        for (int i = 0; i < 5; ++i) {
            playerUnits[i].setPosition(mPlayerUnitPositions.get(i));
            enemyUnits[i].setPosition(mEnemyUnitPositions.get(i));
        }

        //playerUnits[2].attack(enemyUnits[1]);
        //playerUnits[4].attack(enemyUnits[3]);
    }

    public void resize(int width, int height) {
        mCamera = new OrthographicCamera(80, 45); // sets the screen up with easier to work with coordinates
        mBatch.setProjectionMatrix(mCamera.combined); // sets screen to recognize the new mCamera
    }

    public void resume() {

    }

    public void show() {

    }

    public void pause() {

    }

    public void hide() {

    }

    public void render(float delta) {
        mBatch.begin();

        Artist.renderBackground(delta);

        for (Unit unit : playerUnits)
            unit.draw(mBatch, delta);

        for (Unit unit : enemyUnits)
            unit.draw(mBatch, delta);

        mBatch.end();
    }

    private void attack(Unit attacker, Unit defender) {
        Attack attack = new Attack();
        attack.setLocation(attacker.getPosition());
    }

    public void dispose() {

    }
}
