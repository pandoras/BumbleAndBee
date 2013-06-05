package modele;

import screens.SkalowalnyEkran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;


public class AnimowanyObiekt extends Group implements IObiekt {

	TextureRegion obrazek;
	Polygon granice = null;
	
	// dodatek w X do animacji zeby ladnie wygladalo jak zanika przesuwajac sie w lewo do finalnej pozycji
	// ta wartosc powinna byc dostosowana do szybkosci animacji i szybkosci przesuwu ekranu
	private static final int ANIMACJA_ZAPAS_X = 100;
	
	// stworz grupê. Ramka bêdzie w postaci prostok¹ta
	public AnimowanyObiekt(TextureRegion coMalowac) 
	{
		// wywo³anie konstruktora klasy bazowej
		super(); 
		obrazek = coMalowac;
	}
	
	// stworz grupê z ramk¹ w postaci wielok¹ta
	public AnimowanyObiekt(TextureRegion coMalowac, float[] wierzcholki )
	{
		// wywo³anie konstruktora klasy bazowej
		super(); 
		obrazek = coMalowac;
		if (wierzcholki!=null)
			granice = new Polygon(wierzcholki);
		
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(obrazek, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
			getRotation());
	/*	
		if (animacjaUsuwania!=null && animacjaUsuwania.isAnimationFinished(czasAnimacjiUsuwania))
		{
			this.fire(new Event());
			// Ensure you have a handler for this or the event pool will fill
			// up.
			this.getStage().sendEvent(AppEvents.EVENT_END_EXPLOSION, this);
		}*/
		super.draw(batch, parentAlpha);
	}
	
    //Hit method is used to delegate touchDown, mouse, and enter/exit events. 
    //If this method returns null, those events will not occur on this Actor.
    /* DEFAULT:
     *     public Actor hit(float x, float y, boolean touchable)
     *     {
     * 		  if (touchable && this.touchable != Touchable.enabled) return null;
	 *        return x >= 0 && x < width && y >= 0 && y < height ? this : null;
	 *     }    
     */		
    
	// pobierz pozycje x tego elementu na ekranie (do rysowania w debugu)
	public float ekranowyX()
	{
		Vector3 thisCoordinates = new Vector3(this.getX(), this.getY(), 0);
		this.getStage().getCamera().project(thisCoordinates);
		
		return thisCoordinates.x;
	}
	
    public Rectangle pobierzProstok¹t()
    {
    	return  new Rectangle(this.getX(),this.getY(), this.getWidth(), this.getHeight() );
    }
    
    public boolean hit(IObiekt bee)
    {
    	float[] wierzcholki = pobierzGranice();
    	float[] wierzcholkiPszczoly = bee.pobierzGranice();
    	if (wierzcholki!=null && wierzcholkiPszczoly!=null)
    		return Intersector.overlapConvexPolygons( new Polygon(wierzcholki),new Polygon(wierzcholkiPszczoly));

    	return Intersector.overlapRectangles(pobierzProstok¹t(), bee.pobierzProstok¹t());    	
    }

    // pobierz wierzcholki w koordynatach levelu
	@Override
	public float[] pobierzGranice() {
		if (granice==null)
			return null;
		granice.setPosition(getX(), getY());
		granice.setScale(getScaleX(), getScaleY());
		return granice.getTransformedVertices();
	}
	
	// do rysowania dla testow
	@Override
	public float[] pobierzGraniceEkranowe() {
		if (granice==null)
			return null;		
		granice.setPosition(ekranowyX(), getY());
		granice.setScale(getScaleX(), getScaleY());
		return granice.getTransformedVertices();
	}	
	
	// animacja usuwania z planszy przesuwa aktora do podanej pozycji i wykonuje akcje implement (zwiekszenie jakiegos licznika)
	public void animujNaUsuwanie(int finalDoX, int finalDoYtop, Action implement)
	{	
		// musimy ustalic gdzie ma sie konczyc animacja na przesuwnej warstwie 
		
		// kamera jest na srodku ekranu
		Vector3 srodek = this.getStage().getCamera().position;
		
		float wLewoOdSrodkaX = SkalowalnyEkran.BASE_WIDTH/2 - finalDoX - ANIMACJA_ZAPAS_X;		
		float wGoreOdSrodkaY = SkalowalnyEkran.BASE_HEIGHT/2 - finalDoYtop;
		
		MoveToAction action1 = new MoveToAction();
		action1.setPosition(srodek.x - wLewoOdSrodkaX , srodek.y + wGoreOdSrodkaY);
		// ile bedzie trwalo przelatywanie monety/miodku na ta pozycje
		action1.setDuration(1f);
		
		// zwiekszamy powoli przezroczystosc 
		AlphaAction action2 = new AlphaAction();
		action2.setAlpha(0);
		// czas trwania tej czesci animacji
		action2.setDuration(1f);
		
		// ta akcja musi byc ostatnia bo usuwa aktora na dobre
		Action actionFinal = new Action() {
			  public boolean act( float delta ) {				   				
				   // remove actor on end
				   remove();
				   return true; // returning true consumes the event
				  }
				 } ;

		// stworz sekwencje
		SequenceAction sekwencja = new SequenceAction();
		sekwencja.addAction(action1);
		sekwencja.addAction(action2);
		sekwencja.addAction(implement);
		sekwencja.addAction(actionFinal);		
		
		this.addAction(sekwencja);
	}
    
}
