package inne;

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
	public static Sprite spriteTlo;
	public static Sprite spriteStartGry;
	public static Sprite spriteOpcje;
	public static Sprite spriteExit;
	public static Sprite spriteZadlo;
	
	public static void load() {
		
		spriteBatch = new SpriteBatch();
		spriteTlo = NarzedziaBitmapy.wczytajBitmape("menu/MainMenu.jpg");
		spriteStartGry = NarzedziaBitmapy.wczytajBitmape("menu/start_gry.png");
		spriteOpcje = NarzedziaBitmapy.wczytajBitmape("menu/opcje.png");
		spriteExit = NarzedziaBitmapy.wczytajBitmape("menu/exit.png");
		
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