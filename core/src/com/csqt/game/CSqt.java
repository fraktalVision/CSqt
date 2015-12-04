package com.csqt.game;

import com.badlogic.gdx.Game;

public class CSqt extends Game {

	private GameScreen game;
	private MainMenuScreen mainMenu;

	@Override
	public void create () {
		Artist.init();
		game = new GameScreen(this);
		mainMenu = new MainMenuScreen(this);
		setScreen(mainMenu);
	}

	@Override
	public void dispose() {
		game.dispose();
		mainMenu.dispose();
		Artist.dispose();
	}

	public GameScreen getGameScreen() { return game; }

	public MainMenuScreen getMainMenu() { return mainMenu; }
} // end CircleSquareTriangle class
