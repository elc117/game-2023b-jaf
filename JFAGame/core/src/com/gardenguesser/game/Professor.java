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
    public Professor(MainGameScreen screen) {

    }

}
