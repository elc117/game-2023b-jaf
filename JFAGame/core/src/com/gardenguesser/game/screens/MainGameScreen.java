package com.gardenguesser.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gardenguesser.game.Assets;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.Actor;
public class MainGameScreen implements Screen {

    public static float windowWidth;
    public static float windowHeight;

    private Texture background;
    private SpriteBatch batch;
    private Game game;

    public MainGameScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = new Texture("innerArea.png");
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }



    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        Assets.background.dispose();
        //AssetUtils.avatar.dispose();
        //AssetUtils.enemyDino.dispose();
        batch.dispose();
    }

}
