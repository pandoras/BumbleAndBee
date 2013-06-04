package inne;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PrzechowalniaAssets {


	public static SpriteBatch spriteBatch;
	
	public static BitmapFont fontSegoeUI14;
	public static BitmapFont fontSegoeUI15;
	public static BitmapFont fontSegoeUI18;
	public static BitmapFont fontSegoeUI72;
	public static BitmapFont fontSegoeUI_Light32;
	
	public static Sprite spritePrzeciwnik;
	public static Sprite spritePszczola;
	public static Sprite spriteMoneta;
	public static Sprite spritePosiadaneHP;
	public static Sprite spriteStraconePosiadaneHP;
	public static Sprite spriteMiod;
	public static Sprite spriteZadlo;
	
	// indeksy przyciskow
	public static final int przyciskStart = 0;
	public static final int przyciskOpcje = 1;
	public static final int przyciskWyjscie = 2;	
	// tabela tekstur przyciskow
	public static  ArrayList<Texture> teksturyPrzyciskow;
	public static Texture textureMenu;
	
	public static void load() {
		
		spriteBatch = new SpriteBatch();
		
		textureMenu = new Texture(Gdx.files.internal("menu/MainMenu.jpg"));
		
		// wazne: kolejnosc jak w stalych przyciskow
		teksturyPrzyciskow = new ArrayList<Texture>(3);
		teksturyPrzyciskow.add(new Texture(Gdx.files.internal("menu/start_gry.png")));
		teksturyPrzyciskow.add(new Texture(Gdx.files.internal("menu/opcje.png")));
		teksturyPrzyciskow.add(new Texture(Gdx.files.internal("menu/exit.png")));
		
		fontSegoeUI15 = NarzedziaBitmapy.wczytajFont(15, "SegoeUI");	
		fontSegoeUI14 = NarzedziaBitmapy.wczytajFont(14, "SegoeUI");
		fontSegoeUI18 = NarzedziaBitmapy.wczytajFont(18, "SegoeUI");
		fontSegoeUI72 = NarzedziaBitmapy.wczytajFont(72, "SegoeUI");	
		fontSegoeUI_Light32 = NarzedziaBitmapy.wczytajFont(32, "SegoeUI_Light");
		
		spritePszczola = NarzedziaBitmapy.wczytajBitmape("data/pszczola.png");
		spriteMoneta = NarzedziaBitmapy.wczytajBitmape("data/moneta.png");
		spritePosiadaneHP = NarzedziaBitmapy.wczytajBitmape("data/posiadane_zycie.png");
		spriteStraconePosiadaneHP = NarzedziaBitmapy.wczytajBitmape("data/stracone_zycie.png");
		spriteMiod = NarzedziaBitmapy.wczytajBitmape("data/miod.png");
		spritePrzeciwnik = NarzedziaBitmapy.wczytajBitmape("data/trzmiel.png");
		spriteZadlo = NarzedziaBitmapy.wczytajBitmape("data/zadlo.png");
		

	
	}
}