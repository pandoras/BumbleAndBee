package modele;

import java.util.HashSet;

import screens.SkalowalnyEkran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Pszczola extends AnimowanyObiekt implements EventListener  {

	// pozycja do rysowania na ekranie
	float ekranowyx = 100;
	
	static final int MIN_Y = 50;
	static final int MAX_Y = SkalowalnyEkran.BASE_HEIGHT-120;
	static final int MIN_X = 10;
	static final int MAX_X = SkalowalnyEkran.BASE_WIDTH-100;
	
	private int pixeliNaSekunde = 100;
	public Zadlo zadlo;
	public Body cialo = null;
	Polygon granice;
	
	// jezeli ten wektor jest niezerowy to znaczy ze trzeba sie przemiescic
	Vector2 trwaPrzemieszczenie; 
	
	@SuppressWarnings("serial")
	public final HashSet<Integer> klawisze = new HashSet<Integer>() {{ 
		add(Keys.DPAD_LEFT);
		add(Keys.DPAD_RIGHT);
		add(Keys.DPAD_UP);
		add(Keys.DPAD_DOWN);
		add(Keys.SPACE); }} ;
	
	public Pszczola(float startx, float starty) {
		    
		super (new TextureRegion(new Texture(Gdx.files.internal("data/pszczola.png"))));
		
		this.setBounds(startx, starty, obrazek.getRegionWidth(), obrazek.getRegionHeight());
		// AnimowanyObiekt ma domyslnie Touchable wylaczone
		this.setTouchable(Touchable.enabled);
		zadlo = new Zadlo(this);
		
		// granice pszczoly
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
		
		trwaPrzemieszczenie = new Vector2(0,0);
		this.addListener(this);
	}
	
	public void odswierzPozycje(float poczatekEkranu, float deltaTime)
	{
		float y = this.getY() + trwaPrzemieszczenie.y * deltaTime * this.pixeliNaSekunde;
		if (MIN_Y<=y && y<=MAX_Y )
			this.setY( y );
		
		float nowyx = this.ekranowyx + trwaPrzemieszczenie.x * deltaTime * this.pixeliNaSekunde;
		if (MIN_X<=nowyx && nowyx<=MAX_X )
			this.ekranowyx = nowyx;
		
		// pozycja pszczoly w levelu / na scenie
		this.setX( poczatekEkranu + this.ekranowyx);

		
		// if a finger is down, set the sprite's x/y coordinate.
		if (Gdx.input.isTouched()) {
			// the unproject method takes a Vector3 in window coordinates (origin in
			// upper left corner, y-axis pointing down) and transforms it to world
			// coordinates.
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.getStage().getCamera().unproject(touchPos);
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE) && zadlo.pozaEkranem()){
			zadlo.wysun();
		}	
		else if (!zadlo.pozaEkranem()){
			zadlo.przesun(poczatekEkranu, deltaTime);
			if ( zadlo.pozaEkranem()){
				zadlo.schowaj();
			}
		}		
	}
	
	@Override
	public Rectangle pobierzProstok¹t()
	{
		return new Rectangle(this.getX(),this.getY(), obrazek.getRegionWidth(), obrazek.getRegionHeight());
	}
	
	@Override
	public float[] pobierzGraniceEkranowe()
	{
		granice.setPosition(ekranowyx,this.getY());
		return granice.getTransformedVertices();
	}	
	
	@Override
	public float[] pobierzGranice()
	{
		granice.setPosition(this.getX(), this.getY());
		//granice.setScale(this.obrazek.getScaleX(), PrzechowalniaAssets.spritePszczola.getScaleY());
		return granice.getTransformedVertices();
	}

	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(obrazek, ekranowyx, getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
			getRotation());
	}

	@Override
	public boolean handle(Event event) {
		
		if (event instanceof InputEvent) 
		{
			InputEvent inputEvent = (InputEvent)event;
			Type typZdarzenia = inputEvent.getType();
			if (typZdarzenia==Type.touchDown || typZdarzenia==Type.touchDragged)
			{
				trwaPrzemieszczenie.x = 1;
				//System.out.println("Tounch "+inputEvent.getStageX()+" "+inputEvent.getStageY());
			}
			else
				obsluzKlawisz( inputEvent.getType(), inputEvent.getKeyCode());
			return false;
		}
		return false;
	}
	
	public void obsluzKlawisz( Type eventType, int klawisz) {
		
		if (eventType == Type.keyDown) {
					
			if (klawisz == Keys.DPAD_UP)
				trwaPrzemieszczenie.y = 1;
			else if (klawisz == Keys.DPAD_DOWN)
				trwaPrzemieszczenie.y = -1;
			else if (klawisz == Keys.DPAD_RIGHT)
				trwaPrzemieszczenie.x = 1;
			else if (klawisz == Keys.DPAD_LEFT)
				trwaPrzemieszczenie.x = -1;				
		}
		else if (eventType == Type.keyUp) {
			
			if (klawisz == Keys.DPAD_UP)
			{
				if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
					trwaPrzemieszczenie.y = -1;
				else
					trwaPrzemieszczenie.y = 0;
			}
			else if (klawisz == Keys.DPAD_DOWN)
			{
				if (Gdx.input.isKeyPressed(Keys.DPAD_UP))
					trwaPrzemieszczenie.y = 1;
				else
					trwaPrzemieszczenie.y = 0;
			}
			else if (klawisz == Keys.DPAD_RIGHT)
			{
				if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
					trwaPrzemieszczenie.x = -1;
				else				
					trwaPrzemieszczenie.x = 0;
			}
			else if (klawisz == Keys.DPAD_LEFT)
			{
				if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
					trwaPrzemieszczenie.x = 1;
				else				
					trwaPrzemieszczenie.x = 0;
			}			
		}
	}
	
	public void przemiescWStrone(float x, float y)
	{
		if (x>ekranowyx)
			trwaPrzemieszczenie.x = 1;
		else if (x<ekranowyx)
			trwaPrzemieszczenie.x = -1;
		if (y>getY())
			trwaPrzemieszczenie.y = 1;
		else if (y<getY())
			trwaPrzemieszczenie.y = -1;		
	}

	public void zatrzymajPrzemieszczenie()
	{
		trwaPrzemieszczenie.x = 0;
		trwaPrzemieszczenie.y = 0;
	}
	
	public boolean trwaPrzemieszczenie()
	{
		if (trwaPrzemieszczenie.x != 0 || trwaPrzemieszczenie.y != 0)
			return true;
		return false;
	}

}
