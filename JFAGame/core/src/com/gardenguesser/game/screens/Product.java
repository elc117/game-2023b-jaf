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

    protected String nomeProduto;

    protected TextureRegionDrawable imageVariable;
    protected ImageButton image;

    protected char answer;

    protected float imageX;
    protected float imageY;

    public static float windowWidth = Gdx.graphics.getWidth();
    public static float windowHeight = Gdx.graphics.getHeight();

    public Product() {
        gerarImagem();
    }

    protected void gerarImagem(){
        Random random = new Random();
        int num = random.nextInt(26) + 1;


        switch (num) {
            case 1:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAbacaxi));
                nomeProduto = "Abacaxi";
                answer = 'F';
                break;

            case 2:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaAmeixa));
                nomeProduto = "Ameixa";
                answer = 'F';
                break;

            case 3:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaBerinjela));
                nomeProduto = "Berinjela";
                answer = 'F';
                break;

            case 4:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaCereja));
                nomeProduto = "Cereja";
                answer = 'F';
                break;

            case 5:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaCoco));
                nomeProduto = "Coco";
                answer = 'F';
                break;

            case 6:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaMirtilo));
                nomeProduto = "Mirtilo";
                answer = 'F';
                break;

            case 7:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaMorango));
                nomeProduto = "Morango";
                answer = 'F';
                break;

            case 8:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaPimenta));
                nomeProduto = "Pimenta";
                answer = 'F';
                break;

            case 9:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.frutaTomate));
                nomeProduto = "Tomate";
                answer = 'F';
                break;

            case 10:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeAbobora));
                nomeProduto = "Abóbora";
                answer = 'L';
                break;

            case 11:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeAcelga));
                nomeProduto = "Acelga";
                answer = 'L';
                break;

            case 12:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeAlho));
                nomeProduto = "Alho";
                answer = 'L';
                break;

            case 13:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeBatata));
                nomeProduto = "Batata";
                answer = 'L';
                break;

            case 14:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeBeterraba));
                nomeProduto = "Beterraba";
                answer = 'L';
                break;

            case 15:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeCenoura));
                nomeProduto = "Cenoura";
                answer = 'L';
                break;

            case 16:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeInhame));
                nomeProduto = "Inhame";
                answer = 'L';
                break;

            case 17:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.legumeMilho));
                nomeProduto = "Milho";
                answer = 'L';
                break;

            case 18:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.plantaCogumeloVermelho));
                nomeProduto = "Cogumelo Vermelho";
                answer = 'P';
                break;

            case 19:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.plantaDenteDeLeao));
                nomeProduto = "Dente-de-Leão";
                answer = 'P';
                break;

            case 20:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.plantaGirassol));
                nomeProduto = "Girassol";
                answer = 'P';
                break;

            case 21:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.plantaNarciso));
                nomeProduto = "Narciso";
                answer = 'P';
                break;

            case 22:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.plantaTulipa));
                nomeProduto = "Tulipa";
                answer = 'P';
                break;

            case 23:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.verduraAlhoPoro));
                nomeProduto = "Alho Poró";
                answer = 'V';
                break;

            case 25:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.verduraBrocolis));
                nomeProduto = "Brócolos";
                answer = 'V';
                break;

            case 26:
                imageVariable = new TextureRegionDrawable(new TextureRegion(Assets.verduraCouveFlor));
                nomeProduto = "Couve-Flor";
                answer = 'V';
                break;
        }

        image = new ImageButton(imageVariable);

        imageX = windowWidth/2 + 700;
        imageY = windowHeight/2 ;

        //image.setSize(200, 200);

    }

}
