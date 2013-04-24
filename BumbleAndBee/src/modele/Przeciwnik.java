package modele;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Przeciwnik {
		
	public int y;
	public int x = Gdx.graphics.getWidth();
	public float szybkosc = 2;
	
	public Przeciwnik() {		
		Random rand = new Random();
		y = rand.nextInt(Gdx.graphics.getHeight() - 200) + 100;
	}
	
	public Przeciwnik(Przeciwnik kopiaPrzeciwnik) {
		
	}
	
	public void wyswietl()
	{
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePrzeciwnik, x, y);
	}

	/**
	 * Funkcja do sprawdzania czy przeciwnik jest teraz wyswietlany
	 * @return True jesli nie ma na ekranie
	 */
	public boolean pozaEkranem()
	{
		if(x+PrzechowalniaAssets.spritePrzeciwnik.getWidth()<0)
			return true;
		return false;
	}
	
	public void przesun(int pikseliNaSekunde)
	{
		x -= Gdx.graphics.getDeltaTime()*pikseliNaSekunde;		
	}
}