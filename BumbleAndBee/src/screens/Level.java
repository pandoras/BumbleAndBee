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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

	private Stage estrada;
	TextureRegion miodek;
	// kamera jest w pozycji 0 na poczatku poziomu a w pozycji 1 na koncu
	private float pozycjaKameryProc;
	
	// private Camera camera;
	Pszczola pszczola = new Pszczola();
	PostepPoziomu postepPoziomu = new PostepPoziomu();
	InterfejsHP interfejsHP = new InterfejsHP();
	long czas = System.nanoTime();
	
	public Level (BumbleAndBee gra,IPrzycisk przycisk)
	{
		super(gra);
		przyciskStrzalu = przycisk;
		estrada = new Stage(LEVEL_WIDTH, BASE_HEIGHT, true);
		// nasza estrada przetwarza wydarzenia wejœcia i rozdziela na aktorów
		Gdx.input.setInputProcessor(estrada);		
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
			estrada.addActor(miodek1);
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

				
		estrada.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		
		
		estrada.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		estrada.draw();
		

		// to wszystko trzeba dodac do estrady zeby sie samo rysowalo
		
        // DRAW EVERYTHING		
		// RYSOWANIE TEKSTU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": 100", 110, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": 3", 260, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
		// WYSWIETLENIE PSZCZOLY
		//rysujBitmape.wyswietlBitmape(spritePszczola, 90, Gdx.graphics.getHeight() - 200);
		this.pszczola.NarysujMnie();
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE MONETY, SERC, MIODKU
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMoneta, 80, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePosiadaneHP, 10, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteStraconePosiadaneHP, 45, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMiod, 220, Gdx.graphics.getHeight() - 70);
		
		// WYŒWIETLENIE INFORMACJI O LVL
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI_Light32, "Poziom: 1", Gdx.graphics.getWidth() - 144, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI14, "Poziom trudnoœci: £atwy", Gdx.graphics.getWidth() - 160, Gdx.graphics.getHeight() - 40, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE PASKA HP
		interfejsHP.wyswietlHP();
		
		// WYSWIETLA DOLNY PASEK POSTEPU LEVELU
		postepPoziomu.wyswietlPasekPostepu((System.nanoTime() - czas) / 1000000000);
		
		// PRZECIWNICY
		wyswietlaniePrzeciwnikow.obslugaPrzeciwnikow();
	}

	/*	
	@Override
	public void render(float arg0) {
	    //----Aspect Ratio maintenance

	    // update camera
	    camera.update();
	    camera.apply(Gdx.gl10);

	    // set viewport
	    Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
	                      (int) viewport.width, (int) viewport.height);

	    // clear previous frame
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	    // DRAW EVERYTHING
	//--maintenance end--


	    splashScreenSprite.begin();
	    splashScreenSprite.disableBlending();
	    splashScreenSprite.draw(splashScreenRegion, 0, 0);
	    splashScreenSprite.end();
	}

	@Override
	public void resize(int width, int height) {
	    //--Aspect Ratio Maintenance--
	    // calculate new viewport
	    float aspectRatio = (float)width/(float)height;
	    float scale = 1f;
	    Vector2 crop = new Vector2(0f, 0f);

	    if(aspectRatio > ASPECT_RATIO)
	    {
	        scale = (float)height/(float)VIRTUAL_HEIGHT;
	        crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
	    }
	    else if(aspectRatio < ASPECT_RATIO)
	    {
	        scale = (float)width/(float)VIRTUAL_WIDTH;
	        crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
	    }
	    else
	    {
	        scale = (float)width/(float)VIRTUAL_WIDTH;
	    }

	    float w = (float)VIRTUAL_WIDTH*scale;
	    float h = (float)VIRTUAL_HEIGHT*scale;
	    viewport = new Rectangle(crop.x, crop.y, w, h);
	//Maintenance ends here--
	}


	@Override
	public void show() {
	    camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT); //Aspect Ratio Maintenance

	    splashScreen = new Texture(Gdx.files.internal("images/splashScreen.png"));
	    splashScreenRegion = new TextureRegion(splashScreen, 0, 0, 640, 480);
	    splashScreenSprite = new SpriteBatch();

	    if(Assets.load()) {
	        this.dispose();
	        TempMainGame.setScreen(TempMainGame.mainmenu);
	    }   
	}	*/

	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		estrada.setViewport(rozdzielczosc.x*2, rozdzielczosc.y, true);
		estrada.getCamera().viewportWidth = rozdzielczosc.x;
		estrada.getCamera().viewportHeight = rozdzielczosc.y;
		
		float pozycjaKamery = rozdzielczosc.x/2 +  pozycjaKameryProc * (WIDTH_MULTIPLIER-1)*rozdzielczosc.x;
		estrada.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		
		for (Actor a : estrada.getActors()) {
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
