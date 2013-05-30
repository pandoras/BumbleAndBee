package screens;

import java.util.Random;
import java.lang.*;

import modele.Pszczola;
import inne.InterfejsHP;
import inne.PostepPoziomu;
import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;
import inne.WyswietlaniePrzeciwnikow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.majapiotr.bumbleandbee.BumbleAndBee;
import com.majapiotr.bumbleandbee.IPrzycisk;


public class Level extends SkalowalnyEkran  {	

	class ImageGroup extends Group {
		public void draw (SpriteBatch batch, float parentAlpha) {
			batch.setColor(getColor());
			batch.draw(miodek, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
			super.draw(batch, parentAlpha);
		}
	}
	
	protected static final int WIDTH_MULTIPLIER = 2;
	protected static final int LEVEL_WIDTH = BASE_WIDTH*WIDTH_MULTIPLIER;
	
	WyswietlaniePrzeciwnikow wyswietlaniePrzeciwnikow = new WyswietlaniePrzeciwnikow();
	IPrzycisk przyciskStrzalu;

	private Stage przesuwaneT³o;
	private Stage statystyki;
	
	TextureRegion miodek;
	// kamera jest w pozycji 0 na poczatku poziomu a w pozycji 1 na koncu
	private float pozycjaKameryProc;
	
	// private Camera camera;
	Pszczola pszczola = new Pszczola();
	PostepPoziomu postepPoziomu;
	InterfejsHP interfejsHP = new InterfejsHP();
	long czas = System.nanoTime();
	
	public Level (BumbleAndBee gra,IPrzycisk przycisk)
	{
		super(gra);
		przyciskStrzalu = przycisk;
		przesuwaneT³o = new Stage(LEVEL_WIDTH, BASE_HEIGHT, true);
		statystyki = new Stage(BASE_WIDTH, BASE_HEIGHT,true);
		postepPoziomu = new PostepPoziomu();
		statystyki.addActor(postepPoziomu);
		
		//---------------------------WYŒWIETLENIE INFORMACJI O LVL -------------------------------------
		Skin skin = new Skin();
		skin.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		// bez podania nazwy stylu uzyje stylu 'default'
		Label nazwaPoziomu = new Label("Poziom: 1",skin);
		nazwaPoziomu.setX(BASE_WIDTH-nazwaPoziomu.getWidth()-20);
		nazwaPoziomu.setY(BASE_HEIGHT-nazwaPoziomu.getHeight());
		statystyki.addActor(nazwaPoziomu);	
		
		skin.add("mniejszy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI14, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		// uzyj stylu 'mniejszy'
		Label trudnosc = new Label("Poziom trudnoœci: £atwy",skin,"mniejszy");
		trudnosc.setX(BASE_WIDTH-trudnosc.getWidth()-10);
		trudnosc.setY(BASE_HEIGHT-trudnosc.getHeight()-nazwaPoziomu.getHeight());		
		statystyki.addActor(trudnosc);
		//---------------------------koniec WYŒWIETLENIE INFORMACJI O LVL -------------------------------------		
		
		// nasza estrada przetwarza wydarzenia wejœcia i rozdziela na aktorów
		Gdx.input.setInputProcessor(przesuwaneT³o);		
        //camera = new OrthographicCamera(BASE_WIDTH, BASE_HEIGHT);
		Random rand = new Random();
		miodek = new TextureRegion(new Texture(Gdx.files.internal("data/miod.png")));
		
		int x = 200;
		int y;
		while ( x<LEVEL_WIDTH)
		{
			Group miodek1 = new ImageGroup();
			miodek1.setRotation(30);
			miodek1.setTransform(true);
			
			y = rand.nextInt(BASE_HEIGHT - 200) + 100;			
			miodek1.setBounds(x, y, 50, 50);
			przesuwaneT³o.addActor(miodek1);
			x+=200;
		}
		
		pozycjaKameryProc = 0;
    }
	
	public String czas() {
		// DZIELE PRZEZ 10^-9 BO NANOTIME
		long sekundy = (System.nanoTime() - czas) / 1000000000;
		int minuty = (int) sekundy / 60;
		
		// WYSWIETLENIE 0 PRZED SEKUNDAMI (GDY < 10)
		if(sekundy % 60 < 10) return minuty + ":0" + sekundy % 60;
		else return minuty + ":" + sekundy % 60;
	}
	
	@Override
	public void render(float delta) {
	

		super.render(delta);
		
		float pozycjaKamery = rozdzielczosc.x/2 +  pozycjaKameryProc * (WIDTH_MULTIPLIER-1)*rozdzielczosc.x;
		// przesun kamere
		pozycjaKamery += Gdx.graphics.getDeltaTime()*50;
		
		// odswiez procent
		pozycjaKameryProc = (pozycjaKamery - rozdzielczosc.x/2) / ((WIDTH_MULTIPLIER-1)*rozdzielczosc.x);
		if (pozycjaKameryProc>1)
		{	
			gra.pokazMenu();
			return;
		}	
				
		przesuwaneT³o.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		postepPoziomu.ustawProcent(pozycjaKameryProc);
		
		przesuwaneT³o.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		przesuwaneT³o.draw();
		
		statystyki.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		statystyki.draw();
		

		// to wszystko trzeba dodac do statystyki zeby sie samo rysowalo
		
        // DRAW EVERYTHING		
		// RYSOWANIE TEKSTU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": 100", 110, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": 3", 260, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
		// WYSWIETLENIE PSZCZOLY
		this.pszczola.NarysujMnie();
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE MONETY, SERC, MIODKU
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMoneta, 80, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePosiadaneHP, 10, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteStraconePosiadaneHP, 45, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMiod, 220, Gdx.graphics.getHeight() - 70);
			
		// RYSOWANIE PASKA HP
		interfejsHP.wyswietlHP();
		
		// PRZECIWNICY
		wyswietlaniePrzeciwnikow.obslugaPrzeciwnikow();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		przesuwaneT³o.setViewport(rozdzielczosc.x*2, rozdzielczosc.y, true);
		przesuwaneT³o.getCamera().viewportWidth = rozdzielczosc.x;
		przesuwaneT³o.getCamera().viewportHeight = rozdzielczosc.y;
		
		float pozycjaKamery = rozdzielczosc.x/2 +  pozycjaKameryProc * (WIDTH_MULTIPLIER-1)*rozdzielczosc.x;
		przesuwaneT³o.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		
		for (Actor a : przesuwaneT³o.getActors()) {
			a.setScale(skala);
		}	
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
