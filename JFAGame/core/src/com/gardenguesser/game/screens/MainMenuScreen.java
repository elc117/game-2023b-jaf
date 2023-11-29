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

// Classe que cria a tela de menu principal
// Com um botão de play e O titulo do jogo no centro da tela
// Ao clicar no botão de play, a tela de seleção de mapa é chamada
// Os elementos presentes na tela são carregados em AssetUtils.java
public class MainMenuScreen implements Screen {
    // Variáveis estáticas para armazenar a largura e altura da janela
    //public static float windowWidth;
    //public static float windowHeight;

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    // Variáveis privadas para armazenar o batch, background, stage e game
    private SpriteBatch batch;
    private Texture background;
    private Game game;
    private Stage stage;

    private TextureRegionDrawable playButtonVariable;
    private TextureRegionDrawable playButtonHighlightedVariable;
    private ImageButton playButton;

    private TextureRegionDrawable exitButtonVariable;
    private TextureRegionDrawable exitButtonHighlightedVariable;
    private ImageButton exitButton;

    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("menu_song.mp3"));
    private Sound soundButton = Gdx.audio.newSound(Gdx.files.internal("button_sound.mp3"));


    // Construtor da classe
    public MainMenuScreen(Game game) {
        this.game = game;
    }

    // Método que inicializa os elementos da tela
    @Override
    public void show() {
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = Assets.menu;
        stage = new Stage(new ScreenViewport());

        sound.loop(0.1f, 1.0f, 0.0f);

        float playButtonX = windowWidth / 2 - Assets.playButton.getWidth() / 2 - 75;
        float playButtonY = windowHeight / 2 - Assets.playButton.getHeight() / 2;
        float exitButtonX = windowWidth / 2 - Assets.exitButton.getWidth() / 2;
        float exitButtonY = windowHeight / 2 - Assets.exitButton.getHeight() / 2 - 150;

        playButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.playButton));
        playButton = new ImageButton(playButtonVariable);

        exitButtonVariable = new TextureRegionDrawable(new TextureRegion(Assets.exitButton));
        exitButton = new ImageButton(exitButtonVariable);

        playButton.setPosition(playButtonX, playButtonY);
        exitButton.setPosition(exitButtonX, exitButtonY);

        stage.addActor(playButton);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);
    }

    // Método que renderiza os elementos da tela
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Limpa a tela
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Limpa o buffer de cores

        batch.begin();

        batch.draw(background, 0, 0);
        if(Gdx.input.getX() >= 840 && Gdx.input.getX() <= 1070 && Gdx.input.getY() >= 440 && Gdx.input.getY() <= 540) {
            playButtonVariable.setRegion(new TextureRegion(Assets.playButtonHighlighted));
            if (Gdx.input.isTouched())
            {
                soundButton.play();
                this.dispose();
                sound.pause();

                // AO INVES DE CHAMAR O WALKINTOGAME COLOCAR PRA CHAMAR UMA CLASSE FADEOUT
                // AI A CLASSE FADEOUT REALIZA O FADEOUT E DEPOIS DE FAZER ELA CHAMA O WALKINTOGAME
                // NA VDD ELA VAI CHAMAR A CUT SCENE MAS AI É COM VCS
                // DPS DA CUT SCENE CHAMA O FADEOUT DNV E ELE CHAMA A SEGUNDA CUT SCENE
                // E DEPOIS CHAMA DNV O FADEOUT
                // E DAI O FADOUT CHAMA O INNERAREA
                // AI TEM O DIALOGO COM O PROFESSOR
                // AI SE O JOGADOR ACEITAR JOGAR TEM O FADEOUT DNV
                // E DAI OU CHAMA UMA CLASSE NOVA QUE VAI SER DO JOGO OU MANTEM NA INNER AREA MESMO QUE  É ONDE JOGA

                game.setScreen(new ContextScreen(game));
            }
        }
        else
            playButtonVariable.setRegion(new TextureRegion(Assets.playButton));

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
        Assets.menu.dispose();
        Assets.playButton.dispose();
        Assets.playButtonHighlighted.dispose();
        Assets.exitButton.dispose();
        Assets.exitButtonHighlighted.dispose();
        batch.dispose();
    }
}