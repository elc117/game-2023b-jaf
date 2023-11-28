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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
public class MainGameScreen extends Product implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private SpriteBatch batch;
    private Texture background;
    private Game game;
    private Stage stage;

    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("gameplay_song_final.mp3"));
    private long id = sound.play();

    private Product product;


    public MainGameScreen(Game game){
        super();
        this.game = game;
        product = new Product();
    }

    @Override
    public void show() {
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = Assets.innerArea;
        stage = new Stage(new ScreenViewport());

        sound.setVolume(id, 1/10.0f);

        product.image.setSize(128, 128);

        product.image.setPosition(product.imageX, product.imageY);

        stage.addActor(product.image);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
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
        batch.dispose();
    }

}
