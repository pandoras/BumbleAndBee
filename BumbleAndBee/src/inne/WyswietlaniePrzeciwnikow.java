package inne;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;

import modele.Przeciwnik;

public class WyswietlaniePrzeciwnikow {
	
	static int szybkoscPoruszania = 100;
	Random rand;
	List <Przeciwnik> przeciwnicy = new ArrayList<Przeciwnik>();
	private float czasNaPrzeciwnika = 0;
	private float mnoznikNaSekunde;
	
	/**
	 * 
	 * @param poziomTrudnosci  Poziom trudnosci, zaczynamy od 1, im wiecej tym trudniej
	 */
	public WyswietlaniePrzeciwnikow()
	{
		rand = new Random();
		mnoznikNaSekunde = 1;
	}
	public void act() {
		dodajPrzeciwnika();
		kasujNiewidocznychPrzeciwnikow();
	}
	
	private void dodajPrzeciwnika() {
		// liczymy czas od zera do jedynki, jesli osiagnie 1 to dodajemy i resetujemy do 0
		
		// dodajemy losowa czastke czasu zeby przeciwnicy nie byli w tej samej odleglosci od siebie
		czasNaPrzeciwnika += Gdx.graphics.getDeltaTime()*mnoznikNaSekunde*rand.nextFloat();
		if (czasNaPrzeciwnika>1)
		{
			czasNaPrzeciwnika = 0;
			przeciwnicy.add( new Przeciwnik());		
		}
	}
	
	public void draw() {
		for (int i = 0; i < przeciwnicy.size(); i++) {
			przeciwnicy.get(i).draw();
			przeciwnicy.get(i).przesun(szybkoscPoruszania);
		}
	}
	
	private void kasujNiewidocznychPrzeciwnikow() {
		for (int i = 0; i < przeciwnicy.size(); i++) {
			if(przeciwnicy.get(i).pozaEkranem())
				przeciwnicy.remove(i);
		}
	}
}