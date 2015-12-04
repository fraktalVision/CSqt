package com.csqt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {

    private CSqt game;
    private Stage stage;
    private TextButton startButton, exitButton;
    private Image title;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextButton.TextButtonStyle buttonStyle;
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    public MainMenuScreen(CSqt game) {
        this.game = game;

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(0.2f);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = Artist.getCamera();
        viewport = Artist.getViewport();
        viewport.update((int) w, (int) h, true);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        stage = new Stage(viewport, batch);

        buttonSkin = new Skin();
        buttonSkin.add("button", Artist.buttonSpriteFactory());

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = buttonStyle.down = buttonStyle.checked = buttonSkin.newDrawable("button");

        Texture tex = new Texture("CSqt.png");
        TextureRegion img = new TextureRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());

        startButton = new TextButton("START", buttonStyle);
        startButton.setSize(Artist.getButtonWidth(), Artist.getButtonHeight());
        exitButton = new TextButton("EXI T", buttonStyle);
        exitButton.setSize(Artist.getButtonWidth(), Artist.getButtonHeight());
        title = new Image(img);
        title.setSize(24, 18);

        w = viewport.getWorldWidth();
        h = viewport.getWorldHeight();

        startButton.setPosition(
                (w / 2) - (Artist.getButtonWidth() / 2),
                (h / 2) - Artist.getButtonHeight()
        );
        exitButton.setPosition(
                (w / 2) - (Artist.getButtonWidth() / 2),
                (h / 2) - (Artist.getButtonHeight() * 2) - 2
        );
        title.setPosition(
                (w / 2) - (title.getWidth() / 2),
                (h / 2) + (title.getHeight() / 2) - 6
        );

        Gdx.input.setInputProcessor(stage);

        final CSqt csqt = game;

        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                csqt.setScreen(csqt.getGameScreen());
                dispose();
            }
        });

        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(title);
        stage.addActor(startButton);
        stage.addActor(exitButton);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getBatch().setProjectionMatrix(camera.combined);
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
        Artist.renderBackground();
        camera.update();

        stage.act(delta);

        batch.begin();

        batch.end();

        stage.draw();
    }

    public void dispose() {
        buttonSkin.dispose();
        stage.dispose();
    }
}
