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

public class GameOver implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private Texture background;
    private SpriteBatch batch;
    private Game game;
    private Stage stage;


    private String text = "Infelizmente você acabou não passando no teste,\ne não conseguirá a vaga como bolsista dessa vez :(";
    private AnimatedText animatedText;

    private TextureRegionDrawable playAgainButtonVariable;
    private TextureRegionDrawable playAgainHighlightedVariable;
    private ImageButton playAgainButton;

    private TextureRegionDrawable exitButtonVariable;
    private TextureRegionDrawable exitButtonHighlightedVariable;
    private ImageButton exitButton;

    //private Sound sound = Gdx.audio.newSound(Gdx.files.internal("footsteps.wav"));
    private Sound soundButton = Gdx.audio.newSound(Gdx.files.internal("button_sound.mp3"));

    public GameOver(Game game){
        this.game = game;
    }


    @Override
    public void show() {
        Assets.loadAssets();

        batch = new SpriteBatch();

        background = Assets.gameOver;

        stage = new Stage(new ScreenViewport());

        animatedText = new AnimatedText(text, 300, Gdx.graphics.getHeight()/2);
        animatedText.setGradientColors(Color.WHITE, Color.WHITE); // Defina as cores do gradiente
        animatedText.setSpeed(0.05f); // Defina a velocidade da animação

        float playAgainButtonX = windowWidth / 2 - Assets.playButton.getWidth() / 2 - 75;
        float playAgainButtonY = windowHeight / 2 - Assets.playButton.getHeight() / 2;
        float exitButtonX = windowWidth / 2 - Assets.exitButton.getWidth() / 2;
        float exitButtonY = windowHeight / 2 - Assets.exitButton.getHeight() / 2 - 150;

        playAgainButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.playAgainButton));
        playAgainButton = new ImageButton(playAgainButtonVariable);

        exitButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.exitButton));
        exitButton = new ImageButton(exitButtonVariable);

        playAgainButton.setPosition(playAgainButtonX, playAgainButtonY);
        exitButton.setPosition(exitButtonX, exitButtonY);

        stage.addActor(animatedText);
        stage.addActor(playAgainButton);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(background, -600, 0);
        if(Gdx.input.getX() >= 840 && Gdx.input.getX() <= 1070 && Gdx.input.getY() >= 440 && Gdx.input.getY() <= 540) {
            playAgainButtonVariable.setRegion(new TextureRegion(Assets.playAgainButtonHighlighted));
            if (Gdx.input.isTouched())
            {
                soundButton.play();
                this.dispose();
                //sound.pause();

                game.setScreen(new MainGameScreen(game));
            }
        }
        else
            playAgainButtonVariable.setRegion(new TextureRegion(Assets.playAgainButton));

        if(Gdx.input.getX() >= 840 && Gdx.input.getX() <= 1070 && Gdx.input.getY() >= 590 && Gdx.input.getY() <= 690) {
            exitButtonVariable.setRegion(new TextureRegion(Assets.exitButtonHighlighted));
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        else
            exitButtonVariable.setRegion(new TextureRegion(Assets.exitButton));

        batch.end();


        stage.act(delta);
        stage.draw();


        //sound.pause();
        //game.setScreen(new MainGameScreen(game));
    }

    private static class AnimatedText extends Actor {
        private CharSequence text;
        private BitmapFont font;
        private float elapsedTime = 0;
        private float speed = 0.1f;

        // Gradiente
        private Color startColor;
        private Color endColor;

        public AnimatedText(CharSequence text, float x, float y) {
            this.text = text;
            font = new BitmapFont();
            setPosition(x, y);
        }

        public void setGradientColors(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            if (font != null) {
                elapsedTime += Gdx.graphics.getDeltaTime();
                font.getData().setScale(4.0f); // Ajuste o tamanho da fonte conforme necessário

                int numCharsToDraw = (int) (elapsedTime / speed);

                numCharsToDraw = Math.min(numCharsToDraw, text.length());

                // Interpolação para obter a cor gradiente
                Color color = new Color(
                        Interpolation.linear.apply(startColor.r, endColor.r, elapsedTime * speed),
                        Interpolation.linear.apply(startColor.g, endColor.g, elapsedTime * speed),
                        Interpolation.linear.apply(startColor.b, endColor.b, elapsedTime * speed),
                        Interpolation.linear.apply(startColor.a, endColor.a, elapsedTime * speed)
                );

                font.setColor(color);
                font.draw(batch, text.subSequence(0, numCharsToDraw), getX(), getY());
            }
        }
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
        Assets.gameOver.dispose();
        batch.dispose();
    }
}