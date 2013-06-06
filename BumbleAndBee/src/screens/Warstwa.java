package screens;

import zdazenia.Punktuj;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.majapiotr.bumbleandbee.BumbleAndBee;


public class Warstwa extends Stage {

	public BumbleAndBee gra;
	
	public Warstwa(int szerokosc, int wysokosc, BumbleAndBee glownaGra)
	{
		super(szerokosc, wysokosc, false);
		gra = glownaGra;
	}
	
	public void powiadom(Punktuj coPunktowac)
	{
		gra.powiadamiacz.powiadom(coPunktowac);
	}
	
	// w kazdej warstwie trzeba to nadpisac
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.tlo;
	}
}
