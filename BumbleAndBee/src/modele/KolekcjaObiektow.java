package modele;

import java.util.ArrayList;
import java.util.Random;

import screens.Warstwa;

import zdazenia.Powiadamiacz;
import zdazenia.Punktuj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;

@SuppressWarnings("serial")
public class KolekcjaObiektow extends ArrayList<AnimowanyObiekt> {

	TextureRegion region;
	float[] wierzcholki;
	int finalDoX = 0;
	int finalDoYtop = 0;	
	Punktuj coPunktowac;
	Pool<Action> akcje;
	
	public KolekcjaObiektow(float[] wierzcholkiKonturu, String sciezkaDoObrazka, int animujDoX, int animujDoYtop, Punktuj co )
	{
		// wywo³anie konstruktora klasy bazowej
		super(); 		
		region = new TextureRegion(new Texture(Gdx.files.internal(sciezkaDoObrazka)));
		wierzcholki = wierzcholkiKonturu;
		
		finalDoX = animujDoX;
		finalDoYtop = animujDoYtop;		
		coPunktowac = co;
		
	}
	
	public void stworz(float poczatkowyX, float odstepX, int szerokoscLevelu, int wysokoscLevelu, Warstwa estrada)
	{
		Random rand = new Random();
		
		int x = 100;
		int y;
		while ( x<szerokoscLevelu)
		{
			AnimowanyObiekt obiekt = new AnimowanyObiekt(region, wierzcholki);
			this.add(obiekt);
			//obiekt.setRotation(30);
			obiekt.setTransform(true);
			
			y = rand.nextInt(wysokoscLevelu - 200) + 100;	
			obiekt.setBounds(x, y, region.getRegionWidth(), region.getRegionHeight());
			estrada.addActor(obiekt);
			x+=200;
		}		
		
		akcje = new Pool<Action>() {
	        protected Action newObject () {
	        	return new Action() {
	  			  public boolean act( float delta ) {

	  				   ((Warstwa)this.getActor().getStage()).powiadom(coPunktowac);
	  				   return true; // returning true consumes the event
	  				  }
	  				 } ;
	        }
		};
	
	}
	
	// sprawdza kolizje z pszczola, usuwa obiekty i zwraca licznik usunietych
	public int sprawdzKolizje(Pszczola pszczola)
	{
		int licznik = 0; 
		for (int i = this.size()-1; i>=0; i--)
		{					
			//Actor hitActor = miodki.get(i).hit( this.pszczola.x, this.pszczola.y,false);
			//if (hitActor!=null)
			if (this.get(i).hit(pszczola))
			{
				animujIUsun(i);
				licznik++;
			}
		}
		return licznik;
	}
	
	private void animujIUsun(int i)
	{
		Action implementuj = akcje.obtain();
		implementuj.setPool(akcje);
		
		this.get(i).animujNaUsuwanie(finalDoX, finalDoYtop, implementuj );
		//this.get(i).remove();
		this.remove(i);		
	}
}
