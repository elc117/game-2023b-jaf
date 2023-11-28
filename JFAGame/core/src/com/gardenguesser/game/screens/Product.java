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

import java.util.Random;
public class Product {

    protected String nomeAlimento;

    protected TextureRegionDrawable imageVariable;
    protected ImageButton image;

    protected float imageX;
    protected float imageY;

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    private float proporcao = 2.0f; // Fator de escala (por exemplo, 2.0f para dobrar o tamanho)
    private float novaLargura;
    private float novaAltura;

    public Product() {
        gerarImagem();
    }

    public void gerarImagem(){
        Random random = new Random();
        //int num = random.nextInt(26) + 1;

        int num = 1;

        switch (num) {
            case 1:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAbacaxi));
                nomeAlimento = "Abacaxi";
                break;

            case 2:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAmeixa));
                nomeAlimento = "Ameixa";
                break;

            case 3:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaBerinjela));
                break;

            case 4:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAbacaxi));
                break;

            case 5:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAbacaxi));
                break;

            case 6:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAbacaxi));
                break;

            case 7:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAbacaxi));
                break;

        }

        image = new ImageButton(imageVariable);

        //imageX = windowWidth - image.getWidth();
        //imageY = windowHeight + image.getHeight();

        imageX = windowWidth/2 + 100;
        imageY = windowHeight/2 + 100;

        //image.setSize(image.getWidth() * proporcao, image.getHeight() * proporcao);

    }


}
