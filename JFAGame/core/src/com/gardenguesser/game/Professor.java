package com.gardenguesser.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.gardenguesser.game.GardenGuesser;
import com.gardenguesser.game.screens.MainGameScreen;
public class Professor extends Sprite{
    private TextureAtlas atlasProf;
    public Animation<TextureRegion> lerJornal;
    private Array<TextureRegion> frames;
    private float stateTime, posX,posY;
    public TextureRegion profEstatico;
    public Professor(MainGameScreen screen) {
        super(screen.getAtlasProf().findRegion("professor"));
        posX = 630.0f;
        posY = 690.0f;
        stateTime = 0;
        setBounds (posX,posY,64,100);
        // = new TextureRegion(getTexture(), 0, 0, 64, 64);
        //setRegion(profEstatico);

    }
    public void mexerOlhos(){
        frames = new Array<TextureRegion>();
        for(int i = 0; i <3; i++)
        {
            if (i ==0)
                frames.add(new TextureRegion(getTexture(),1,0, 63, 100));
            else
                frames.add(new TextureRegion(getTexture(),(64+4)*i,0, 64, 100));
        }
        lerJornal = new Animation<>(1.0f,frames,Animation.PlayMode.LOOP);
    }
    public void setStateTime(float delta) {
        this.stateTime = this.stateTime + delta;
    }
    public float getStateTime() {
        return this.stateTime;
    }

    public Animation<TextureRegion> getLerJornal() {
        return lerJornal;
    }

}
