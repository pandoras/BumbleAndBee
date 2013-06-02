package modele;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

	
	public Pszczola() {
		zadlo = new Zadlo();
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

	public void NarysujMnie() {


		if (Gdx.input.isKeyPressed(Keys.SPACE) && zadlo.pozaEkranem()){
			zadlo.wysun(this);
		}
		if (!zadlo.pozaEkranem()){
			zadlo.wyswietl();
			zadlo.przesun();
			if ( zadlo.pozaEkranem()){
				zadlo.x = -1;
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
