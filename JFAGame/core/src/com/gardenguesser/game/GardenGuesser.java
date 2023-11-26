package com.gardenguesser.game;

import com.badlogic.gdx.Game;
import com.gardenguesser.game.screens.MainMenuScreen;

public class GardenGuesser extends Game {
	
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
	}

	/*@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}*/
}
