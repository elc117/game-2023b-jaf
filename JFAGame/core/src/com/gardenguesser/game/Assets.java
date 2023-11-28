package com.gardenguesser.game;

import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture innerArea;
    public static Texture mainCharacter;
    public static Texture botanicalGardenStreet;

    public static Texture mainCharacterFront;

    public static Texture menu;
    public static Texture playButton;
    public static Texture exitButton;
    public static Texture playButtonHighlighted;
    public static Texture exitButtonHighlighted;

    //public static Texture blackFadeOut;

    public static Texture frutaAbacaxi;
    public static Texture frutaAmeixa;
    public static Texture frutaBerinjela;
    public static Texture frutaCereja;
    public static Texture frutaCoco;
    public static Texture frutaMirtilo;
    public static Texture frutaMorango;
    public static Texture frutaPimenta;
    public static Texture frutaTomate;
    public static Texture legumeAbobora;
    public static Texture legumeAcelga;
    public static Texture legumeAlho;
    public static Texture legumeBatata;
    public static Texture legumeBeterraba;
    public static Texture legumeCenoura;
    public static Texture legumeInhame;
    public static Texture legumeMilho;
    public static Texture plantaCogumeloVermelho;
    public static Texture plantaDenteDeLeao;
    public static Texture plantaGirassol;
    public static Texture plantaNarciso;
    public static Texture plantaTulipa;
    public static Texture verduraAlhoPoro;
    public static Texture verduraBrocolis;
    public static Texture verduraCouveFlor;
    public static Texture verduraRepolhoRoxo;

    private Assets () {
    }

    public static void loadAssets(){
        innerArea = new Texture("innerArea.png");
        mainCharacter = new Texture("mainCharacter.png");
        mainCharacterFront = new Texture("vicent_de_frente.png");
        botanicalGardenStreet = new Texture("botanicalGardenStreet.png");
        menu = new Texture("menu.png");
        playButton = new Texture("play_button.png");
        exitButton = new Texture("exit_button.png");
        playButtonHighlighted = new Texture("play_button_highlighted.png");
        exitButtonHighlighted = new Texture("exit_button_highlighted.png");
        frutaAbacaxi = new Texture ("fruta_abacaxi.png");
        frutaAmeixa = new Texture ("fruta_ameixa.png");
        frutaBerinjela = new Texture ("fruta_berinjela.png");
        frutaCereja = new Texture ("fruta_cereja.png");
        frutaCoco = new Texture ("fruta_coco.png");
        frutaMirtilo = new Texture ("fruta_mirtilo.png");
        frutaMorango = new Texture ("fruta_morango.png");
        frutaPimenta = new Texture ("fruta_pimenta.png");
        frutaTomate = new Texture ("fruta_tomate.png");
        legumeAbobora = new Texture ("legume_abobora.png");
        legumeAcelga = new Texture ("legume_acelga.png");
        legumeAlho = new Texture ("legume_alho.png");
        legumeBatata = new Texture ("legume_batata.png");
        legumeBeterraba = new Texture ("legume_beterraba.png");
        legumeCenoura = new Texture ("legume_cenoura.png");
        legumeInhame = new Texture ("legume_inhame.png");
        legumeMilho = new Texture ("legume_milho.png");
        plantaCogumeloVermelho = new Texture ("planta_cogumelo_vermelho.png");
        plantaDenteDeLeao = new Texture ("planta_dente_de_leao.png");
        plantaGirassol = new Texture ("planta_girassol.png");
        plantaNarciso = new Texture ("planta_narciso.png");
        plantaTulipa = new Texture ("planta_tulipa.png");
        verduraAlhoPoro = new Texture ("verdura_alho_poro.png");
        verduraBrocolis = new Texture ("verdura_brocolis.png");
        verduraCouveFlor = new Texture ("verdura_couve_flor.png");
        verduraRepolhoRoxo = new Texture ("verdura_repolho_roxo.png");
    }

}