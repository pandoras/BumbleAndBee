package modele;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Pszczola {

	// pozycja do rysowania na ekranie
	float ekranowyx = 100;
	
	// pozycja w levelu
	public float x = ekranowyx;	
	public float y = 100;
	
	private int pixeliNaSekunde = 100;
	Zadlo zadlo;
	public Body cialo = null;
	Polygon granice;

	
	public Pszczola() {
		zadlo = new Zadlo();
		float[]vertice={ 27.27516100f, 0.16148262f,
				 10.89715900f, 10.07748262f,
				 0.79401952f, 11.39798262f,
				 5.96535630f, 17.00718262f,
				 0.11259600f, 22.46338262f,
				 0.31048199f, 34.84568262f,
				 6.50300400f, 40.72468262f,
				 23.02412600f, 40.79568262f,
				 34.49871800f, 31.51668262f,
				 46.39421000f, 31.15698262f,
				 40.88017900f, 37.27868262f,
				 59.18566200f, 32.62378262f,
				 69.01228000f, 36.78968262f,
				 70.93200300f, 33.41478262f,
				 62.51957900f, 30.74498262f,
				 69.82825100f, 20.41978262f,
				 80.98684600f, 17.90618262f,
				 81.42305900f, 11.82418262f,
				 76.26559800f, 8.86548262f,
				 63.63623900f, 12.18108262f,
				 42.55311700f, 0.28308262f};
		granice=new Polygon(vertice);		
	}
	
	public void odswierzPozycje(float poczatekEkranu, float deltaTime)
	{
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && ekranowyx!=0) {
			this.ekranowyx -= deltaTime*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && ekranowyx!=1120) {
			this.ekranowyx += deltaTime*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) && y!=570) {
			this.y += deltaTime*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) && y!=50) {
			this.y -= deltaTime*this.pixeliNaSekunde;
		}	
		
		// pozycja pszczoly w levelu / na scenie
		this.x = poczatekEkranu + this.ekranowyx;
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle(this.x,this.y, PrzechowalniaAssets.spritePszczola.getWidth(), PrzechowalniaAssets.spritePszczola.getHeight());
	}
	
	public Polygon getPolygon()
	{
		granice.setOrigin(x, y);
		return new Polygon(granice.getTransformedVertices());
	}

	public void NarysujMnie() {


		if (Gdx.input.isKeyPressed(Keys.SPACE) && zadlo.pozaEkranem()){
			zadlo.wysun(this);
		}
		if (!zadlo.pozaEkranem()){
			zadlo.wyswietl();
			zadlo.przesun();
			if ( zadlo.pozaEkranem()){
				zadlo.ekranowyx = -1;
			}
		}

		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePszczola, (int)this.ekranowyx, (int)this.y);
		
		/*
		// odswierz pozycje w swiecie, contactManager.FindNewContacts() bedzie wolany
		boolean updateContacts = false;
		if (cialo!=null)
		{
			Vector2 test = cialo.getPosition(); 
			if (this.x != test.x || this.y != test.y)
				cialo.setTransform(this.x, this.y, 0, updateContacts);
		}
		*/
	}
	
	/*
	public void dodajDoSwiata(World world)
	{
	    //  box 50 wide and high.
		PolygonShape boxPoly = new PolygonShape();
		boxPoly.setAsBox(PrzechowalniaAssets.spritePszczola.getWidth()/2f,PrzechowalniaAssets.spritePszczola.getHeight()/2f);

		
		// ten kod dodaje Boxy monet do swiata world
		// pozwoli nam to na detekcje polizji poprzez metode world.getContactCount
		BodyDef boxBodyDef = new BodyDef();
		// cialo, na ktore nie wplywa grawitacja swiata 
		boxBodyDef.type = BodyType.DynamicBody;
		boxBodyDef.position.x = this.x;
		boxBodyDef.position.y = this.y;	
		cialo = world.createBody(boxBodyDef);
		
		System.out.println("Pszczola na pozycji x:" + this.x + " y:" + this.y);
		
		cialo.createFixture(boxPoly, 1);		
	}
	*/

}
