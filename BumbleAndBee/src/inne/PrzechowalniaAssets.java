package inne;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrzechowalniaAssets {


	public static SpriteBatch spriteBatch;
	
	public static BitmapFont fontSegoeUI14;
	public static BitmapFont fontSegoeUI15;
	public static BitmapFont fontSegoeUI18;
	public static BitmapFont fontSegoeUI72;
	public static BitmapFont fontSegoeUI_Light32;

	
	public static TextureRegion posiadaneZycie;
	public static TextureRegion straconeZycie;
	
	public static TextureRegion ul;
	public static TextureRegion zadlo;
	public static TextureRegion miod;
	public static TextureRegion moneta;
	
	// indeksy przyciskow
	public static final int przyciskStart = 0;
	public static final int przyciskOpcje = 1;
	public static final int przyciskWyjscie = 2;	
	// tabela tekstur przyciskow
	public static  ArrayList<Texture> teksturyPrzyciskow;
	public static Texture textureMenu;
	public static Texture textureSklep;
	public static TextureRegion textureSklepZamknij;
	public static TextureRegion textureSklepKup;
	
	public static TextureRegion textureStrzel;
	
	public static void load() {
		
		spriteBatch = new SpriteBatch();
		
		textureMenu = new Texture(Gdx.files.internal("menu/MainMenu.jpg"));
		
		textureSklep = new Texture(Gdx.files.internal("sklep/calosc.png"));
		textureSklepZamknij = new TextureRegion(new Texture(Gdx.files.internal("sklep/zamknij.png")));
		textureSklepKup = new TextureRegion(new Texture(Gdx.files.internal("sklep/kup.png")));
		
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

		textureStrzel = new TextureRegion(new Texture(Gdx.files.internal("data/atak.png")));
		
		posiadaneZycie = new TextureRegion(new Texture(Gdx.files.internal("data/posiadane_zycie.png")));
		straconeZycie = new TextureRegion(new Texture(Gdx.files.internal("data/stracone_zycie.png")));
		
		ul = new TextureRegion(new Texture(Gdx.files.internal("data/ul.png")));		
		
		zadlo = new TextureRegion(new Texture(Gdx.files.internal("data/zadlo.png")));	
		
		miod = new TextureRegion(new Texture(Gdx.files.internal("data/miod.png")));	
		
		moneta = new TextureRegion(new Texture(Gdx.files.internal("data/moneta.png")));
	
		Music music = Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3"));
		music.setLooping(true);
		music.setVolume(1.0f);
		music.play();
	}
}
