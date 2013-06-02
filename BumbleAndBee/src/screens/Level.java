package screens;

import java.util.ArrayList;
import java.util.Random;
import java.lang.*;

import nieuzywane.ManagerKontaktow;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
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
		
		TextureRegion obrazek;
		
		public ImageGroup(TextureRegion coMalowac)
		{
			obrazek = coMalowac;
		}
		public void draw (SpriteBatch batch, float parentAlpha) {
			batch.setColor(getColor());
			batch.draw(obrazek, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
			super.draw(batch, parentAlpha);
		}
		
	    //This method is used to delegate touchDown, mouse, and enter/exit events. 
	    //If this method returns null, those events will not occur on this Actor.
	    /* DEFAULT:
	     * 		if (touchable && this.touchable != Touchable.enabled) return null;
		         return x >= 0 && x < width && y >= 0 && y < height ? this : null;
	     */		
	    @Override
	    public Actor hit(float x, float y, boolean touchable)
	    {
	    	//OverlapTester.overlapRectangles(piante.get(i).bounds,omino.bounds)
	    	return ( (x >= this.getX()-this.getWidth()/2) &&
	    			 (x <= this.getX()+this.getWidth()/2) &&
	    			 (y >= this.getY()-this.getHeight()/2) &&
	    			 (y <= this.getY()+this.getHeight()/2) )	? this : null;
	        //return super.hit(x, y, touchable);
	    }
	    
	}
	
	protected static final int WIDTH_MULTIPLIER = 2;
	protected static final int LEVEL_WIDTH = BASE_WIDTH*WIDTH_MULTIPLIER;
	
	/** monety **/
	private ArrayList<ImageGroup> monety = new ArrayList<ImageGroup>();	

	/** miodki **/
	private ArrayList<ImageGroup> miodki = new ArrayList<ImageGroup>();	
	
	WyswietlaniePrzeciwnikow wyswietlaniePrzeciwnikow = new WyswietlaniePrzeciwnikow();
	IPrzycisk przyciskStrzalu;

	private Stage przesuwaneTło;
	private Stage statystyki;
	
	// kamera jest w pozycji 0 na poczatku poziomu a w pozycji 1 na koncu
	private float pozycjaKameryProc;
	
	// private Camera camera;
	Pszczola pszczola = new Pszczola();
	PostepPoziomu postepPoziomu;
	InterfejsHP interfejsHP = new InterfejsHP();
	long czas = System.nanoTime();
	
	int ileMonet = 0;
	int ileMiodu = 0;
	
	public Level (BumbleAndBee gra,IPrzycisk przycisk)
	{
		super(gra);
		przyciskStrzalu = przycisk;
		przesuwaneTło = new Stage(LEVEL_WIDTH, BASE_HEIGHT, true);
		statystyki = new Stage(BASE_WIDTH, BASE_HEIGHT,true);
		postepPoziomu = new PostepPoziomu();
		statystyki.addActor(postepPoziomu);
		
		//---------------------------WYŚWIETLENIE INFORMACJI O LVL -------------------------------------
		Skin skin = new Skin();
		skin.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		// bez podania nazwy stylu uzyje stylu 'default'
		Label nazwaPoziomu = new Label("Poziom: 1",skin);
		nazwaPoziomu.setX(BASE_WIDTH-nazwaPoziomu.getWidth()-20);
		nazwaPoziomu.setY(BASE_HEIGHT-nazwaPoziomu.getHeight());
		statystyki.addActor(nazwaPoziomu);	
		
		skin.add("mniejszy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI14, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		// uzyj stylu 'mniejszy'
		Label trudnosc = new Label("Poziom trudności: Łatwy",skin,"mniejszy");
		trudnosc.setX(BASE_WIDTH-trudnosc.getWidth()-10);
		trudnosc.setY(BASE_HEIGHT-trudnosc.getHeight()-nazwaPoziomu.getHeight());		
		statystyki.addActor(trudnosc);
		//---------------------------koniec WYŚWIETLENIE INFORMACJI O LVL -------------------------------------		
		
		// nasza estrada przetwarza wydarzenia wejścia i rozdziela na aktorów
		Gdx.input.setInputProcessor(przesuwaneTło);		
        
		
		//camera = new OrthographicCamera(BASE_WIDTH, BASE_HEIGHT);

		
		// we instantiate a new World with a proper gravity vector
		// and tell it to sleep when possible.
		//world = new World(new Vector2(0, -10), true);
		//world.setContactListener(new ManagerKontaktow());
		stworzMiodki ();	
		stworzMonety ();	
		//this.pszczola.dodajDoSwiata(world);
		
		pozycjaKameryProc = 0;
    }
	
	private void stworzMiodki () {
		
		Random rand = new Random();
		TextureRegion miodek = new TextureRegion(new Texture(Gdx.files.internal("data/miod.png")));
		
		int x = 200;
		int y;
		while ( x<LEVEL_WIDTH)
		{
			ImageGroup miodek1 = new ImageGroup(miodek);
			miodki.add(miodek1);
			miodek1.setRotation(30);
			miodek1.setTransform(true);
			
			y = rand.nextInt(BASE_HEIGHT - 200) + 100;	
			// wielkosc miodka ustaw na 50px
			miodek1.setBounds(x, y, 50, 50);
			przesuwaneTło.addActor(miodek1);
			x+=200;
		}
	}
	
	private void stworzMonety () {
	    /*
		//  box 50 wide and high.
		PolygonShape boxPoly = new PolygonShape();
		float monetaW = 50;
		float monetaH = 50;
		boxPoly.setAsBox(monetaW/2f,monetaH/2f);
		*/
		TextureRegion moneta = new TextureRegion(new Texture(Gdx.files.internal("data/moneta.png")));
		
		// next we create the 50 box bodies using the PolygonShape we just
		// defined. This process is similar to the one we used for the ground
		// body. Note that we reuse the polygon for each body fixture.
		int x = 200;
		Random rand = new Random();
		while ( x<LEVEL_WIDTH)
		{
			float y = rand.nextInt(BASE_HEIGHT - 200) + 100;
/*			// ten kod dodaje Boxy monet do swiata world
			// pozwoli nam to na detekcje polizji poprzez metode world.getContactCount
			BodyDef boxBodyDef = new BodyDef();
			// cialo, na ktore nie wplywa grawitacja swiata (no chyba ze monety maja spadac)
			boxBodyDef.type = BodyType.DynamicBody;
			boxBodyDef.position.x = x;
			float y = rand.nextInt(BASE_HEIGHT - 200) + 100;
			boxBodyDef.position.y = y;	
			Body boxBody = world.createBody(boxBodyDef);

			boxBody.createFixture(boxPoly, 1);

			// add the box to our list of boxes
			monety.add(boxBody);
			*/
			
			// to ponizej zapewni nam rysowanie monet
			ImageGroup grupaMoneta = new ImageGroup(moneta);
			monety.add(grupaMoneta);
			grupaMoneta.setRotation(30);
			grupaMoneta.setTransform(true);
					
			grupaMoneta.setBounds(x, y, 50, 50);
			przesuwaneTło.addActor(grupaMoneta);

			x+=200;
		}

		// we are done, all that's left is disposing the boxPoly
		//boxPoly.dispose();
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
		
		// przestawiamy world
		// nie wiem dlaczego 8 i 3
		//world.step(delta, 8, 3);
		
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
				
		przesuwaneTło.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		postepPoziomu.ustawProcent(pozycjaKameryProc);
		
		przesuwaneTło.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		przesuwaneTło.draw();
		
		statystyki.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		statystyki.draw();
		
		// WYSWIETLENIE PSZCZOLY
		this.pszczola.odswierzPozycje(pozycjaKamery- rozdzielczosc.x/2, delta);
		this.pszczola.NarysujMnie();
		
		this.sprawdzKolizje();
		// to wszystko trzeba dodac do statystyki zeby sie samo rysowalo
		
        // DRAW EVERYTHING		
		// RYSOWANIE TEKSTU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": "+ileMonet, 110, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": "+ileMiodu, 260, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		
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
	
	private void sprawdzKolizje()
	{
		for (int i = miodki.size()-1; i>=0; i--)
		{			
			
			Actor hitActor = miodki.get(i).hit( this.pszczola.x, this.pszczola.y,false);
			if (hitActor!=null)
			{
				System.out.println("Hit na miodek");
				hitActor.remove();
				miodki.remove(i);
				ileMiodu++;
			}
		}
		
		for (int i = monety.size()-1; i>=0; i--)
		{			
			
			Actor hitActor = monety.get(i).hit( this.pszczola.x, this.pszczola.y,false);
			if (hitActor!=null)
			{
				System.out.println("Hit na monete");
				hitActor.remove();
				monety.remove(i);
				ileMonet++;
			}
		}	
		
		
		/*for (int i = 0; i < world.getContactCount(); i++) {
			
			Contact contact = world.getContactList().get(i);
			// we only render the contact if it actually touches
			if (contact.isTouching()) {
				System.out.println("Kontakt touch");
			}			
			System.out.println("Kontakt na render");
			System.out.println("Pszczola teraz na pozycji x:" + this.pszczola.x + " y:" + this.pszczola.y);
			
			if (contact.getFixtureA().getBody() == this.pszczola.cialo)
				world.destroyBody(contact.getFixtureB().getBody());
			else
				world.destroyBody(contact.getFixtureA().getBody());
		}
		*/		
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		przesuwaneTło.setViewport(rozdzielczosc.x*2, rozdzielczosc.y, true);
		przesuwaneTło.getCamera().viewportWidth = rozdzielczosc.x;
		przesuwaneTło.getCamera().viewportHeight = rozdzielczosc.y;
		
		float pozycjaKamery = rozdzielczosc.x/2 +  pozycjaKameryProc * (WIDTH_MULTIPLIER-1)*rozdzielczosc.x;
		przesuwaneTło.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		
		for (Actor a : przesuwaneTło.getActors()) {
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
	public void dispose () {
		//world.dispose();
	}

}
