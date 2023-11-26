package com.gardenguesser.game;

import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture innerArea;
    public static Texture mainCharacter;
    public static Texture botanicalGardenStreet;

    private Assets {
    }

    private static void loadAssets(){
        innerArea = new Texture("innerArea.png");
        mainCharacter = new Texture("mainCharacter.png");
        botanicalGardenStreet = new Texture("botanicalGardenStreet.png");
    }

}