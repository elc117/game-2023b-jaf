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
import com.badlogic.gdx.graphics.g2d.Batch;

public class MainGameScreen extends Product implements Screen {

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private SpriteBatch batch;
    private Texture background;
    private TextureAtlas atlasProf;
    private Game game;
    private Stage stage;
    private TextureRegion currentFrame;
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("gameplay_song_final.mp3"));
    private Sound rightAnswer = Gdx.audio.newSound(Gdx.files.internal("right_answer.mp3"));
    private Sound wrongAnswer = Gdx.audio.newSound(Gdx.files.internal("wrong_answer.mp3"));
    private Sound soundWalking = Gdx.audio.newSound(Gdx.files.internal("footsteps.wav"));
    private Sound soundTalking = Gdx.audio.newSound(Gdx.files.internal("professor.mp3"));

    private Product product;

    private BitmapFont font;

    private float deltaTime;
    private float timer;
    private int acertos;
    private int erros;
    private Vicente vicente;
    private Professor prof;
    private TextureAtlas atlas;
    private boolean areaClicked = false;
    private boolean primeiraAnimacaoConcluida;
    private float elapsedTime;
    private float interval = 1f;

    private String text = "Opa, estava distraído!\nAcabei nem te vendo chegar\nMas enfim bom dia, Mário.\nVamos realizar com você um teste para\ntestar seu conhecimento botânico, ok?";
    private AnimatedText animatedText;

    private TextureRegionDrawable professorDialogueVariable;
    private ImageButton professorDialogue;

    private float professorDialogueX = 0;
    private float professorDialogueY = 0;

    private boolean startGame = false;
    private boolean podeAndar = true;
    private boolean click = false;
    private boolean dialogueOne = false;
    private boolean secondDialogue = false;

    private boolean playMusic = false;
    private boolean controle = false;

    private boolean controle2 = false;

    public MainGameScreen(Game game){
        super();
        this.game = game;
        product = new Product();

    }

    @Override
    public void show() {
        atlas = new TextureAtlas("Vicente_Movimentos.pack");
        atlasProf = new TextureAtlas("professor.pack");
        Assets.loadAssets();
        batch = new SpriteBatch();
        background = Assets.innerArea;
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        primeiraAnimacaoConcluida = false;
        vicente = new Vicente(this);
        prof = new Professor(this);
        prof.mexerOlhos();
        erros = 0;
        acertos = 0;
        deltaTime = 0;
        timer = 10;
        elapsedTime = 0f;

        font.setColor(Color.WHITE);
        font.getData().setScale(3.0f);

        professorDialogueVariable = new TextureRegionDrawable(new TextureRegion(Assets.professorDialogue));
        professorDialogue = new ImageButton(professorDialogueVariable);

        sound.pause();

        soundWalking.loop(1.0f, 0.8f, 1.0f);

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

        if (deltaTime > 5 && secondDialogue == false)
            soundTalking.pause();

        if (podeAndar == false && dialogueOne == false) {
            long soundId = soundTalking.play();
            soundTalking.setPitch(soundId, 1.3f);
            soundWalking.pause();
            dialogueOne = true;
            professorDialogue.setPosition(professorDialogueX, professorDialogueY);
            stage.addActor(professorDialogue);
            animatedText = new AnimatedText(text, 275, Gdx.graphics.getHeight() / 2);
            animatedText.setGradientColors(Color.BLACK, Color.BLACK);
            animatedText.setSpeed(0.03f);
            stage.addActor(professorDialogue);
            stage.addActor(animatedText);
            text = "Você terá que classificar diferentes produtos\ndo acervo do Jardim Botânico em \nquatro diferentes categorias:\n 'F' represeta Frutas, 'P' representa plantas, \n'V' representa verduras e  'L' representa legumes. \nEntendido? Vamos começar?";
            deltaTime = 0;
        }

        if (dialogueOne == true && Gdx.input.isTouched() && secondDialogue == false && deltaTime >= 6) {
            stage.getRoot().removeActor(animatedText);
            long soundId = soundTalking.play();
            soundTalking.setPitch(soundId, 1.1f);
            animatedText = new AnimatedText(text, 275, Gdx.graphics.getHeight() / 2);
            animatedText.setGradientColors(Color.BLACK, Color.BLACK);
            animatedText.setSpeed(0.03f);
            stage.addActor(animatedText);
            secondDialogue = true;
            deltaTime = 0;
        }

        if (secondDialogue == true && Gdx.input.isTouched() && deltaTime >= 9) {
            stage.getRoot().removeActor(animatedText);
            stage.getRoot().removeActor(professorDialogue);
            podeAndar = true;
        }

        if(playMusic == true && controle == false){
            controle = true;
            soundWalking.loop(1.0f, 0.8f, 1.0f);
        }

        if(startGame == true) {
            if(playMusic == true && controle == true && controle2 == false){
                controle2 = true;
                soundWalking.pause();
                sound.loop(0.2f, 1.0f, 1.0f);
            }

            if (erros > 5) {
                sound.pause();
                game.setScreen(new GameOver(game));
            }
            if (acertos > 20) {
                sound.pause();
                game.setScreen(new GameWon(game));
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
        }

        batch.begin();
        batch.draw(background, 0, 0);
        if(!primeiraAnimacaoConcluida)
             currentFrame = new TextureRegion(animacaoVicente());
        if(primeiraAnimacaoConcluida) {
             currentFrame = new TextureRegion(animacaoVicente2());
        }
        vicente.setRegion(currentFrame);
        vicente.draw(batch);
        TextureRegion currentFrameProf = new TextureRegion(prof.lerJornal.getKeyFrame(prof.getStateTime(), true));
        prof.setRegion(currentFrameProf);
        prof.draw(batch);

        if(startGame == true) {

            if (Gdx.input.getX() >= 1120 && Gdx.input.getX() <= 1175 && Gdx.input.getY() >= 550 && Gdx.input.getY() <= 635 && Gdx.input.isTouched() && elapsedTime >= interval) {
                if (product.answer == 'F') {
                    acertos++;
                    rightAnswer.play();
                } else {
                    erros++;
                    wrongAnswer.play();
                }
                timer = 10;
                stage.getRoot().removeActor(product.image);
                super.gerarImagem();
                product = new Product();
                elapsedTime = 0f;
            }

            if (Gdx.input.getX() >= 1310 && Gdx.input.getX() <= 1365 && Gdx.input.getY() >= 550 && Gdx.input.getY() <= 635 && Gdx.input.isTouched() && elapsedTime >= interval) {
                if (product.answer == 'P') {
                    acertos++;
                    rightAnswer.play();
                } else {
                    erros++;
                    wrongAnswer.play();
                }
                timer = 10;
                stage.getRoot().removeActor(product.image);
                super.gerarImagem();
                product = new Product();
                elapsedTime = 0f;
            }

            if (Gdx.input.getX() >= 1120 && Gdx.input.getX() <= 1175 && Gdx.input.getY() >= 680 && Gdx.input.getY() <= 765 && Gdx.input.isTouched() && elapsedTime >= interval) {
                if (product.answer == 'V') {
                    acertos++;
                    rightAnswer.play();
                } else {
                    erros++;
                    wrongAnswer.play();
                }
                timer = 10;
                stage.getRoot().removeActor(product.image);
                super.gerarImagem();
                product = new Product();
                elapsedTime = 0f;
            }

            if (Gdx.input.getX() >= 1310 && Gdx.input.getX() <= 1365 && Gdx.input.getY() >= 680 && Gdx.input.getY() <= 765 && Gdx.input.isTouched() && elapsedTime >= interval) {
                if (product.answer == 'L') {
                    acertos++;
                    rightAnswer.play();
                } else {
                    erros++;
                    wrongAnswer.play();
                }
                timer = 10;
                stage.getRoot().removeActor(product.image);
                super.gerarImagem();
                product = new Product();
                elapsedTime = 0f;
            }

            if (product.nomeProduto == null) {
                super.gerarImagem();
                product = new Product();
            }

            if (product.nomeProduto != null) {
                font.draw(batch, product.nomeProduto, product.imageX - 100, product.imageY + 350);
            }

            font.draw(batch, "Tempo: " + (int) timer + "s", product.imageX - 100, product.imageY + 450);
            font.draw(batch, "Acertos: " + acertos, 50, product.imageY + 50);
            font.draw(batch, "Erros: " + erros, 50, product.imageY - 50);
        }

        batch.end();

        if(startGame == true) {
            product.image.setPosition(product.imageX - 100, product.imageY);

            stage.addActor(product.image);
        }

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

            if (font != null) {
                elapsedTime += Gdx.graphics.getDeltaTime();
                font.getData().setScale(3.0f);

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
        batch.dispose();
        font.dispose();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    private TextureRegion animacaoVicente() {
        if(vicente.getPosY() <= 510.0f){
            vicente.andarParaCima();
            return vicente.andarCima.getKeyFrame(vicente.getStateTime(), true);
        }
        else if (vicente.getPosY() >510.0f) {
            if(vicente.getPosX()<630.0f)
            {
                podeAndar = false;
                primeiraAnimacaoConcluida = true;
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

    private TextureRegion animacaoVicente2() {
        if(vicente.getPosX() <= 870.0f && podeAndar && primeiraAnimacaoConcluida){
            if (playMusic == false && controle == false) {
                playMusic = true;
            }
            vicente.andarParaDireita();
            return vicente.andarDireita.getKeyFrame(vicente.getStateTime(), true);
        }
        else if (vicente.getPosX() > 870.0f && podeAndar && primeiraAnimacaoConcluida) {
            if(vicente.getPosY()> 360.0f && podeAndar && primeiraAnimacaoConcluida) {
                vicente.andarParaBaixo();
                return vicente.andarBaixo.getKeyFrame(vicente.getStateTime(), true);
            }
            else if(vicente.getPosY() <= 360.0f && podeAndar && primeiraAnimacaoConcluida)
            {
                if(vicente.getPosX()<= 1220.0f && podeAndar && primeiraAnimacaoConcluida)
                {
                    vicente.andarParaDireita();
                    return vicente.andarDireita.getKeyFrame(vicente.getStateTime(), true);
                }
                else {
                    startGame = true;
                    return vicente.getVicenteFrente();
                }
            }
            else
                return vicente.getVicenteFrente();
        }
        else
            return vicente.getVicenteFrente();
    }

    public TextureAtlas getAtlasProf() {
        return atlasProf;
    }
}
