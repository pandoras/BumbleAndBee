package screens;

import zdazenia.Punktuj;
import inne.WyswietlaniePrzeciwnikow;
import modele.KolekcjaObiektow;
import modele.Pszczola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaTla extends Warstwa {

	/** monety i miodki **/
	public KolekcjaObiektow monety;	
	public KolekcjaObiektow miodki;
	WyswietlaniePrzeciwnikow wyswietlaniePrzeciwnikow;
	
	public WarstwaTla(int mnoznikDlugosci, BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci, maingame);
		
		wyswietlaniePrzeciwnikow = new WyswietlaniePrzeciwnikow();
		
		// inicjujemy monety i miodki
		miodki = new KolekcjaObiektow( new float[] 
				{0.12626907f, 13.13198262f, 
				 6.06091530f, 20.58188262f, 
				 6.06091530f, 23.35978262f, 
				 9.59644920f, 25.25378262f, 
				 15.40482600f, 24.24368262f,
				 20.45558900f, 28.28428262f,
				 28.28427100f, 26.64278262f,
				 27.02158100f, 21.21318262f,
				 35.22907000f, 18.56158262f,
				 33.46130300f, 11.74298262f,
				 23.35977800f, 7.82868262f, 
				 21.46574200f, 1.01018262f, 
				 14.64721200f, 0f,
				 9.09137290f, 3.91438262f, 
				 3.66180300f, 2.52538262f, 
				 0.37880721f, 6.94478262f, 
				 2.14657420f, 9.84898262f}, 
				 "data/miod.png",WarstwaStatystyk.MIOD_X,WarstwaStatystyk.MIOD_Y_TOP, Punktuj.miod);
		miodki.stworz(100, 200, SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci, SkalowalnyEkran.BASE_HEIGHT, this);
		
		monety = new KolekcjaObiektow( null, "data/moneta.png",WarstwaStatystyk.MONETA_X,WarstwaStatystyk.MONETA_Y_TOP, Punktuj.monety);	
		monety.stworz(100, 200, SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci, SkalowalnyEkran.BASE_HEIGHT, this);		
	}
	
	public void render(float delta, Vector3 pozycjaKamery)
	{
		this.getCamera().position.set(pozycjaKamery);
		
		this.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		this.draw();	
		
		// PRZECIWNICY
		wyswietlaniePrzeciwnikow.obslugaPrzeciwnikow();		
	}
	
	public void sprawdzKolizje(Pszczola pszczola)
	{
		miodki.sprawdzKolizje(pszczola);
		monety.sprawdzKolizje(pszczola);			
	}	
	
}
