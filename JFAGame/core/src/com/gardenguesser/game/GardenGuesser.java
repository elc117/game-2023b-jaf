package com.gardenguesser.game;

import com.badlogic.gdx.game;
import com.gardenguesser.game.screens.MainGameScreen;

public class GardenGuesser extends Game {
	
	@Override
	public void create () {
		setScreen(new MainGameScreen(this));
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
