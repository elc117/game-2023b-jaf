package com.gardenguesser.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.gardenguesser.game.GardenGuesser;
import com.gardenguesser.game.screens.MainGameScreen;
import com.gardenguesser.game.screens.WalkIntoGame;

public class Vicente extends Sprite {
    private TextureAtlas atlas;
    private TextureRegion vicenteFrente;
    private TextureRegion vicenteCostas;
    public Animation<TextureRegion> andarDireita;
    public Animation<TextureRegion> andarEsquerda;
    public Animation<TextureRegion> andarCima;
    public Animation<TextureRegion> andarBaixo;
    private Array<TextureRegion> frames;
    private float stateTime, posX,posY;
    public Vicente (WalkIntoGame screen) {
        super(screen.getAtlas().findRegion("vicente"));
        posX = 50;
        posY = 500;
        stateTime = 0;
        vicenteFrente = new TextureRegion(getTexture(), 8, 15, 112, 176);
        vicenteCostas = new TextureRegion(getTexture(), 511, 262, 120, 184);
        setBounds(posX, posY, 112 / 2, 176 / 2);
        setRegion(vicenteFrente);
    }
    public Vicente (MainGameScreen screen) {
        super(screen.getAtlas().findRegion("vicente"));
        posX = 870;
        posY = 50;
        stateTime = 0;
        vicenteFrente = new TextureRegion(getTexture(), 8, 15, 112, 176);
        vicenteCostas = new TextureRegion(getTexture(), 511, 262, 120, 184);
        setBounds(posX, posY, 112 / 2, 176 / 2);
        setRegion(vicenteCostas);
    }
        //
    public void andarParaDireita() {
        setPosition(posX+=1.5f, posY);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<4; i++) {
            if(i ==0)
                frames.add(new TextureRegion(getTexture(),7,262, 120, 184));
            else
                frames.add(new TextureRegion(getTexture(),(120+8)*i,262,120,184));
        }
            andarDireita = new Animation<>(0.4f,frames,Animation.PlayMode.LOOP);
    }
    public void andarParaEsquerda() {
        setPosition(posX-=1.0f, posY);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<4; i++) {
            if(i ==0)
                frames.add(new TextureRegion(getTexture(),511,6, 120, 184));
            else
                frames.add(new TextureRegion(getTexture(),511+((120+8)*i),6,120,184));
        }
        andarEsquerda = new Animation<>(0.4f,frames,Animation.PlayMode.LOOP);
    }
    public void andarParaCima() {
        setPosition(posX, posY+=2.0f);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<4; i++) {
            if(i ==0)
                frames.add(new TextureRegion(getTexture(),511,262, 120, 184));
            else
                frames.add(new TextureRegion(getTexture(),511+((120+8)*i),262,120,184));
        }
        andarCima = new Animation<>(0.4f,frames,Animation.PlayMode.LOOP);
    }
    public void andarParaBaixo() {
        setPosition(posX, posY-=1.0f);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<4; i++) {
            if(i ==0)
                frames.add(new TextureRegion(getTexture(),7,16, 112, 176));
            else
                frames.add(new TextureRegion(getTexture(),7+((120+8)*i),16,112,176));
        }
        andarBaixo = new Animation<>(0.4f,frames,Animation.PlayMode.LOOP);
    }
    public void setStateTime(float delta) {
        this.stateTime = this.stateTime + delta;
    }
    public float getStateTime() {
        return this.stateTime;
    }
    public float getPosX() {
        return this.posX;
    }
    public float getPosY() {
        return this.posY;
    }
    public TextureRegion getVicenteFrente() {
        return this.vicenteFrente;
    }
    public TextureRegion getVicenteCostas() {
        return this.vicenteCostas;
    }
}
