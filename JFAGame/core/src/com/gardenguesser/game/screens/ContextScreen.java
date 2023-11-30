package com.gardenguesser.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ContextScreen implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private SpriteBatch batch;
    private Texture background;
    private Game game;
    private Stage stage;

    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("context_game_song.mp3"));
    private Sound soundButton = Gdx.audio.newSound(Gdx.files.internal("button_sound.mp3"));

    private float deltaTime = 0;
    private float timer = 26;
    private AnimatedText animatedText;

    private String texto = "O Jardim Botânico lançou um edital no site da UFSM, \n\nconvocando candidatos para a " +
            "posição de bolsista encarregado de documentar e categorizar essas novas espécies. \n\nNeste cenário, " +
            "você foi escolhido antecipadamente para ocupar essa posição crucial. \n\nEm breve, você será conduzido " +
            "ao Jardim Botânico, \n\nonde suas habilidades e conhecimentos serão testados de maneira prática e " +
            "decisiva, \n\ndesempenhando um papel fundamental na ampliação do entendimento científico sobre a " +
            "biodiversidade botânica.\n\n\n\n\n" +
            "Boa sorte !!!";

    private float continueX = 650;
    private float continueY = -450;

    private TextureRegionDrawable continueButtonVariable;
    private TextureRegionDrawable continueButtonHighlightedVariable;
    private ImageButton continueButton;

    public ContextScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = Assets.darkScreen;
        stage = new Stage(new ScreenViewport());

        sound.loop(0.2f, 1.0f, 0.0f);

        continueButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.continueButton));
        continueButton = new ImageButton(continueButtonVariable);

        continueButton.setPosition(continueX, continueY);

        animatedText = new AnimatedText(texto, 100, Gdx.graphics.getHeight() - 100);
        animatedText.setGradientColors(Color.WHITE, Color.WHITE);
        animatedText.setSpeed(0.05f);

        stage.addActor(animatedText);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaTime += delta;

        if (deltaTime >= 1) {
            timer -= 1;
            deltaTime = 0;
        }


        if (timer <= 0) {
            if(Gdx.input.getX() >= 1560 && Gdx.input.getX() <= 1790 && Gdx.input.getY() >= 880 && Gdx.input.getY() <= 980) {
                continueButtonVariable.setRegion(new TextureRegion(Assets.continueButtonHighlighted));
                if (Gdx.input.isTouched())
                {
                    soundButton.play();
                    //this.dispose();
                    sound.pause();
                    transicaoTela();
                    //game.setScreen(new WalkIntoGame(game));
                }
            }
            else
                continueButtonVariable.setRegion(new TextureRegion(Assets.continueButton));

            stage.addActor(continueButton);
        }

        batch.begin();

        batch.draw(background, 0, 0);

        batch.end();

        stage.act(delta);
        stage.draw();

    }

    private static class AnimatedText extends Actor {
        private CharSequence text;
        private BitmapFont font;
        private float elapsedTime = 0;
        private float speed = 0.1f;

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
                font.getData().setScale(2.2f);

                int numCharsToDraw = (int) (elapsedTime / speed);

                numCharsToDraw = Math.min(numCharsToDraw, text.length());

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
        Assets.darkScreen.dispose();
        stage.dispose();
        batch.dispose();
        Assets.continueButton.dispose();
        Assets.continueButtonHighlighted.dispose();
        sound.dispose();
        soundButton.dispose();
        background.dispose();
    }
    private void transicaoTela() {
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 2.0f);
        FadeScreen fadeScreen = new FadeScreen(game, fadeOut, this, new WalkIntoGame(game));
        game.setScreen(fadeScreen);
    }
}