package com.gardenguesser.game.screens;

//import com.badlogic.gdx.ApplicationListener;
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

// Classe que cria a tela de menu principal
// Com um botão de play e O titulo do jogo no centro da tela
// Ao clicar no botão de play, a tela de seleção de mapa é chamada
// Os elementos presentes na tela são carregados em AssetUtils.java
public class ContextScreen implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private SpriteBatch batch;
    private Texture background;
    private Game game;
    private Stage stage;

    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("context_game_song.mp3"));

    private float deltaTime = 0;
    private float timer = 60;

    private AnimatedText animatedText;

    private String texto = "O Jardim Botânico lançou um edital no site da UFSM, \n\nconvocando candidatos para a " +
            "posição de bolsista encarregado de documentar e categorizar essas novas espécies. \n\nNeste cenário, " +
            "você foi escolhido antecipadamente para ocupar essa posição crucial. \n\nEm breve, você será conduzido " +
            "ao Jardim Botânico, \n\nonde suas habilidades e conhecimentos serão testados de maneira prática e " +
            "decisiva, \n\ndesempenhando um papel fundamental na ampliação do entendimento científico sobre a " +
            "biodiversidade botânica.\n\n\n\n\n" +
            "Boa sorte !!!";


    // Construtor da classe
    public ContextScreen(Game game) {
        this.game = game;
    }

    // Método que inicializa os elementos da tela
    @Override
    public void show() {
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = Assets.darkScreen;
        stage = new Stage(new ScreenViewport());

        sound.loop(0.5f, 1.0f, 0.0f);

        animatedText = new AnimatedText(texto, 100, Gdx.graphics.getHeight() - 100);
        animatedText.setGradientColors(Color.WHITE, Color.WHITE); // Defina as cores do gradiente
        animatedText.setSpeed(0.05f); // Defina a velocidade da animação
        stage.addActor(animatedText);
    }

    // Método que renderiza os elementos da tela
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Limpa a tela
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Limpa o buffer de cores

        deltaTime += delta;

        if (deltaTime >= 1) {
            timer -= 1;
            deltaTime = 0;
        }


        if (timer <= 0) {
            this.dispose();
            sound.pause();
            game.setScreen(new WalkIntoGame(game));
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
            elapsedTime += Gdx.graphics.getDeltaTime();
            font.getData().setScale(2.2f); // Ajuste o tamanho da fonte conforme necessário

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

    // Método que redimensiona a tela
    @Override
    public void resize(int width, int height) {
    }

    // Método que pausa a tela
    @Override
    public void pause() {
    }

    // Método que retoma a tela
    @Override
    public void resume() {
    }

    // Método que esconde a tela
    @Override
    public void hide() {
    }

    // Método que descarta os elementos da tela
    @Override
    public void dispose() {
        Assets.darkScreen.dispose();
        batch.dispose();
    }
}