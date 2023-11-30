package com.gardenguesser.game.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.gardenguesser.game.Vicente;
import com.gardenguesser.game.Professor;
public class MainGameScreen extends Product implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private SpriteBatch batch;
    private Texture background;
    private Game game;
    private Stage stage;
    private Professor prof;
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("gameplay_song_final.mp3"));
    private Sound rightAnswer = Gdx.audio.newSound(Gdx.files.internal("right_answer.mp3"));
    private Sound wrongAnswer = Gdx.audio.newSound(Gdx.files.internal("wrong_answer.mp3"));

    private Product product;

    private BitmapFont font;

    private float deltaTime;
    private float timer;
    private int acertos;
    private int erros;
    private Vicente vicente;
    private TextureAtlas atlas,atlasProf;

    private boolean areaClicked = false;

    private float elapsedTime;
    private float interval = 1f;

    public MainGameScreen(Game game){
        super();
        this.game = game;
        product = new Product();
    }

    @Override
    public void show() {
        atlasProf = new TextureAtlas("professor.pack");
        atlas = new TextureAtlas("Vicente_Movimentos.pack");
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = Assets.innerArea;
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        vicente = new Vicente(this);
        prof = new Professor(this);
        erros = 0;
        acertos = 0;
        deltaTime = 0;
        timer = 10;
        elapsedTime = 0f;
        prof.mexerOlhos();
        sound.loop(0.02f, 1.0f, 0.0f);



        font.setColor(Color.WHITE);
        font.getData().setScale(3.0f);

        product.image.setPosition(product.imageX - 100, product.imageY);

        stage.addActor(product.image);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        vicente.setStateTime(delta);
        prof.setStateTime(delta);
        deltaTime += delta;
        elapsedTime += delta;
        if(erros > 5) {
            sound.pause();
            game.setScreen(new GameOver(game));
        }
        if(acertos > 20) {
            sound.pause();
            game.setScreen(new GameWon (game));
        }
        if (deltaTime >= 1) {
            timer -= 1;
            deltaTime = 0;
            if (timer <= 0) {
                timer = 10;
                stage.getRoot().removeActor(product.image);
                super.gerarImagem();
                product = new Product();
                erros++;
                wrongAnswer.play();
                elapsedTime = 0;
            }
        }
        batch.begin();
        batch.draw(background, 0, 0);
        TextureRegion currentFrame = new TextureRegion(animacaoVicente());
        vicente.setRegion(currentFrame);
        vicente.draw(batch);
        TextureRegion currentFrameProf = new TextureRegion(prof.lerJornal.getKeyFrame(prof.getStateTime(), true));
        prof.setRegion(currentFrameProf);
        prof.draw(batch);
        if(Gdx.input.getX() >= 1120 && Gdx.input.getX() <= 1175 && Gdx.input.getY() >= 550 && Gdx.input.getY() <=  635 && Gdx.input.isTouched() && elapsedTime >= interval)
        {
            if(product.answer == 'F') {
                acertos++;
                rightAnswer.play();
            }
            else {
                erros++;
                wrongAnswer.play();
            }
            timer = 10;
            stage.getRoot().removeActor(product.image);
            super.gerarImagem();
            product = new Product();
            elapsedTime = 0f;
        }

        if(Gdx.input.getX() >= 1310 && Gdx.input.getX() <= 1365 && Gdx.input.getY() >= 550 && Gdx.input.getY() <=  635 && Gdx.input.isTouched() && elapsedTime >= interval)
        {
            if(product.answer == 'P') {
                acertos++;
                rightAnswer.play();
            }
            else {
                erros++;
                wrongAnswer.play();
            }
            timer = 10;
            stage.getRoot().removeActor(product.image);
            super.gerarImagem();
            product = new Product();
            elapsedTime = 0f;
        }

        if(Gdx.input.getX() >= 1120 && Gdx.input.getX() <= 1175 && Gdx.input.getY() >= 680 && Gdx.input.getY() <=  765 && Gdx.input.isTouched() && elapsedTime >= interval)
        {
            if(product.answer == 'V') {
                acertos++;
                rightAnswer.play();
            }
            else {
                erros++;
                wrongAnswer.play();
            }
            timer = 10;
            stage.getRoot().removeActor(product.image);
            super.gerarImagem();
            product = new Product();
            elapsedTime = 0f;
        }

        if(Gdx.input.getX() >= 1310 && Gdx.input.getX() <= 1365 && Gdx.input.getY() >= 680 && Gdx.input.getY() <=  765 && Gdx.input.isTouched() && elapsedTime >= interval)
        {
            if(product.answer == 'L') {
                acertos++;
                rightAnswer.play();
            }
            else {
                erros++;
                wrongAnswer.play();
            }
            timer = 10;
            stage.getRoot().removeActor(product.image);
            super.gerarImagem();
            product = new Product();
            elapsedTime = 0f;
        }

        if(product.nomeProduto == null) {
            super.gerarImagem();
            product = new Product();
        }

        if (product.nomeProduto != null) {
            font.draw(batch, product.nomeProduto, product.imageX - 100, product.imageY + 350);
        }
        font.draw(batch, "Tempo: " + (int) timer + "s", product.imageX - 100, product.imageY + 450);
        font.draw(batch, "Acertos: " + acertos, 50, product.imageY + 50);
        font.draw(batch, "Erros: " + erros, 50, product.imageY - 50);

        batch.end();

        product.image.setPosition(product.imageX - 100, product.imageY);

        stage.addActor(product.image);

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
        font.dispose();
    }
    public TextureAtlas getAtlas() {
        return atlas;
    }
    public TextureAtlas getAtlasProf() {
        return atlasProf;
    }
    private TextureRegion animacaoVicente() {
        if(vicente.getPosY() <= 510.0f){
            vicente.andarParaCima();
            return vicente.andarCima.getKeyFrame(vicente.getStateTime(), true);
        }
        else if (vicente.getPosY() >510.0f) {
            if(vicente.getPosX()<630.0f)
            {
                return vicente.getVicenteCostas();
            }
            else {
                vicente.andarParaEsquerda();
                return vicente.andarEsquerda.getKeyFrame(vicente.getStateTime(),true);
            }

        }
        else
            return vicente.getVicenteCostas();
    }
}
