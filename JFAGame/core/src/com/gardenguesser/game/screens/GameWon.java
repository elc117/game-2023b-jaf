package com.gardenguesser.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.gardenguesser.game.Vicente;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GameWon implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private Texture background;
    private SpriteBatch batch;
    private Game game;
    private Stage stage;

    private String text = "Parabéns, você teve um desempenho excelente!!\nVocê se saiu muito bem na prova!!\nSendo assim, a vaga como bolsista no Jardim Botânico é toda sua!!! ";
    private BitmapFont font;

    private TextureRegionDrawable playAgainButtonVariable;
    private TextureRegionDrawable playAgainHighlightedVariable;
    private ImageButton playAgainButton;

    private TextureRegionDrawable exitButtonVariable;
    private TextureRegionDrawable exitButtonHighlightedVariable;
    private ImageButton exitButton;

    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("game_win_sfx.mp3"));
    private Sound soundButton = Gdx.audio.newSound(Gdx.files.internal("button_sound.mp3"));

    public GameWon(Game game){
        this.game = game;
    }


    @Override
    public void show() {
        Assets.loadAssets();

        batch = new SpriteBatch();

        sound.play();

        background = Assets.gameWon;

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2.5f);

        stage = new Stage(new ScreenViewport());

        float playAgainButtonX = -200;
        float playAgainButtonY = -300;
        float exitButtonX = 300;
        float exitButtonY = -310;

        playAgainButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.playAgainButton));
        playAgainButton = new ImageButton(playAgainButtonVariable);

        exitButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.exitButton));
        exitButton = new ImageButton(exitButtonVariable);

        playAgainButton.setPosition(playAgainButtonX, playAgainButtonY);
        exitButton.setPosition(exitButtonX, exitButtonY);

        stage.addActor(playAgainButton);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        if(Gdx.input.getX() >= 490 && Gdx.input.getX() <= 990 && Gdx.input.getY() >= 760 && Gdx.input.getY() <= 865) {
            playAgainButtonVariable.setRegion(new TextureRegion(Assets.playAgainButtonHighlighted));
            if (Gdx.input.isTouched())
            {
                soundButton.play();
                //this.dispose();
                sound.pause();
                transicaoTela();
                //game.setScreen(new MainGameScreen(game));
            }
        }
        else
            playAgainButtonVariable.setRegion(new TextureRegion(Assets.playAgainButton));

        if(Gdx.input.getX() >= 1135 && Gdx.input.getX() <= 1365 && Gdx.input.getY() >= 760 && Gdx.input.getY() <= 860) {
            exitButtonVariable.setRegion(new TextureRegion(Assets.exitButtonHighlighted));
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        else
            exitButtonVariable.setRegion(new TextureRegion(Assets.exitButton));

        font.draw(batch, text, 500, windowHeight/2 + 25);

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
        Assets.gameWon.dispose();
        batch.dispose();
        font.dispose();
    }
    private void transicaoTela() {
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 2.0f);
        FadeScreen fadeScreen = new FadeScreen(game, fadeOut, this, new MainGameScreen(game));
        game.setScreen(fadeScreen);
    }
}