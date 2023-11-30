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

public class WalkIntoGame implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private Texture background;
    private SpriteBatch batch;
    private Game game;
    private Stage stage;

    private TextureAtlas atlas;
    private Vicente vicente;
    private boolean podeTrocar, telaTrocada;

    private boolean podeAndar = false;
    private boolean secondDialogue = false;

    private TextureRegionDrawable marioDialogueVariable;
    private ImageButton marioDialogue;

    private float marioDialogueX = 0;
    private float marioDialogueY = 0;

    private String text = "Nossa, estou muito empolgado, mal\nposso esperar para começar\na atuar no Jardim Botânico !!!";
    private AnimatedText animatedText;

    private Sound soundWalking = Gdx.audio.newSound(Gdx.files.internal("footsteps.wav"));
    private Sound talking = Gdx.audio.newSound(Gdx.files.internal("mario.mp3"));

    private long soundId;

    private float deltaTime = 0;
    private float timer = 5;

    public WalkIntoGame(Game game){
        this.game = game;
    }


    @Override
    public void show() {
        atlas = new TextureAtlas("Vicente_Movimentos.pack");
        Assets.loadAssets();
        telaTrocada = false;
        batch = new SpriteBatch();
        background = Assets.botanicalGardenStreet;

        vicente = new Vicente(this);

        long soundId = talking.play();
        talking.setPitch(soundId, 1.5f);

        stage = new Stage(new ScreenViewport());

        podeTrocar = false;

        animatedText = new AnimatedText(text, 275, Gdx.graphics.getHeight()/2);
        animatedText.setGradientColors(Color.BLACK, Color.BLACK);
        animatedText.setSpeed(0.05f);

        marioDialogueVariable = new TextureRegionDrawable(new TextureRegion(Assets.marioDialogue));
        marioDialogue = new ImageButton(marioDialogueVariable);

        marioDialogue.setPosition(marioDialogueX, marioDialogueY);

        stage.addActor(marioDialogue);
        stage.addActor(animatedText);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        vicente.setStateTime(delta);

        deltaTime += delta;

        if (deltaTime >= 1) {
            timer -= 1;
            deltaTime = 0;
        }


        if(podeAndar == false && Gdx.input.isTouched() && timer <= 0) {
            talking.pause();
            podeAndar = true;
            stage.getRoot().removeActor(animatedText);
            stage.getRoot().removeActor(marioDialogue);
            soundId = soundWalking.loop(1.0f, 0.8f, 0.0f);
            timer = 16;
        }

        if(vicente.getPosX() >= 1110.0f && secondDialogue == false)
        {
            long soundId = talking.play();
            talking.setPitch(soundId, 1.5f);
            secondDialogue = true;
            podeAndar = false;
            soundWalking.pause();
            text = "Preciso me apressar! \nNão posso chegar atrasado,\nainda mais no meu primeiro dia!";
            animatedText = new AnimatedText(text, 275, Gdx.graphics.getHeight()/2);
            animatedText.setGradientColors(Color.BLACK, Color.BLACK); // Defina as cores do gradiente
            animatedText.setSpeed(0.05f);
            stage.addActor(marioDialogue);
            stage.addActor(animatedText);
        }

        if(secondDialogue == true && Gdx.input.isTouched() && timer <= 0)
        {
            talking.pause();
            podeAndar = true;
            stage.getRoot().removeActor(animatedText);
            stage.getRoot().removeActor(marioDialogue);
            soundWalking.setPitch(soundId, 1.2f);
        }

        batch.begin();
        batch.draw(background, 0, 0);

        TextureRegion currentFrame = new TextureRegion(animacaoVicente());

        vicente.setRegion(currentFrame);
        vicente.draw(batch);

        batch.end();
        if(podeTrocar && !telaTrocada)
        {
            telaTrocada = true;
            transicaoTela();
        }
        stage.act(delta);
        stage.draw();
    }
    private TextureRegion animacaoVicente() {
        if (vicente.getPosX() <1110.0f && podeAndar == true){
            vicente.andarParaDireita();
            return vicente.andarDireita.getKeyFrame(vicente.getStateTime(), true);
        }
        else if (vicente.getPosY() >= 710.0f && podeAndar == true){
            podeTrocar = true;
            soundWalking.pause();
            return vicente.getVicenteCostas();
        }
        else if(vicente.getPosX() >= 1110.0f && podeAndar == true) {
            vicente.andarParaCima();
            return vicente.andarCima.getKeyFrame(vicente.getStateTime(), true);
        }
        else
            return vicente.getVicenteFrente();
    }
    private void transicaoTela() {
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 2.0f);
        FadeScreen fadeScreen = new FadeScreen(game, fadeOut, this, new MainGameScreen(game));
        game.setScreen(fadeScreen);
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
                font.getData().setScale(4.0f);

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
        Assets.botanicalGardenStreet.dispose();
        batch.dispose();
        stage.dispose();

        Assets.marioDialogue.dispose();
        soundWalking.dispose();
        background.dispose();
    }
    public TextureAtlas getAtlas() {
        return atlas;
    }

}