package modele;

import java.util.Random;

import przeciwnicy.Grubas;
import przeciwnicy.Trzmiel;
import przeciwnicy.Zolnierz;

import screens.Warstwa;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

import zdazenia.Punktuj;

@SuppressWarnings("serial")
public class Przeciwnicy extends KolekcjaObiektow {

	Pszczola pszczola;
	
	public Przeciwnicy(Pszczola sledzonaPszczola)
	{
		super (null, "",0,0,Punktuj.zycie);
		pszczola = sledzonaPszczola;
	}
	
	@Override
	public void stworz(float poczatkowyX, float odstepX, int szerokoscLevelu, int wysokoscLevelu, Warstwa estrada)
	{
		Random rand = new Random();
		
		int x = (int)poczatkowyX;
		int y;
		AnimowanyObiekt obiekt;
		while ( x<szerokoscLevelu*2.5)
		{
			int typTrzmiela = rand.nextInt(3);
			if (typTrzmiela==0)
				obiekt = new Trzmiel();
			else if (typTrzmiela==1)
				obiekt = new Grubas(pszczola);
			else
				obiekt = new Zolnierz();
			this.add(obiekt);

			obiekt.setTransform(true);
		
			y = rand.nextInt(wysokoscLevelu - 200) + 100;	
			obiekt.setBounds(x, y, obiekt.obrazek.getRegionWidth(), obiekt.obrazek.getRegionHeight());
			estrada.addActor(obiekt);
			x+=odstepX;
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
	
	@Override
	public int sprawdzKolizje(Pszczola pszczola)
	{
		int licznik = super.sprawdzKolizje(pszczola);
		for (int i = this.size()-1; i>=0; i--)
		{				
			if (!pszczola.zadlo.pozaEkranem())
				if (this.get(i).hit(pszczola.zadlo))
				{
					this.get(i).animuj( false );
					this.remove(i);
					pszczola.zadlo.schowaj();
				}
		}
		return licznik;
	}
	
}
