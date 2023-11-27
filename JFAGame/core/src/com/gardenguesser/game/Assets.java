package com.gardenguesser.game;

import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture innerArea;
    public static Texture mainCharacter;
    public static Texture botanicalGardenStreet;

    public static Texture menu;
    public static Texture playButton;
    public static Texture exitButton;
    public static Texture playButtonHighlighted;
    public static Texture exitButtonHighlighted;

    public static Texture blackFadeOut;

    private Assets () {
    }

    public static void loadAssets(){
        innerArea = new Texture("innerArea.png");
        mainCharacter = new Texture("mainCharacter.png");
        botanicalGardenStreet = new Texture("botanicalGardenStreet.png");
        menu = new Texture("menu.png");
        playButton = new Texture("play_button.png");
        exitButton = new Texture("exit_button.png");
        playButtonHighlighted = new Texture("play_button_highlighted.png");
        exitButtonHighlighted = new Texture("exit_button_highlighted.png");
        blackFadeOut = new Texture("dark_screen.png");
    }

}