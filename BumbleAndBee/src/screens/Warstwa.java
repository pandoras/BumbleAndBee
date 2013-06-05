package screens;

import zdazenia.Punktuj;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.majapiotr.bumbleandbee.BumbleAndBee;


public class Warstwa extends Stage {

	public BumbleAndBee gra;
	
	public Warstwa(int szerokosc, BumbleAndBee glownaGra)
	{
		super(szerokosc, SkalowalnyEkran.BASE_HEIGHT, false);
		gra = glownaGra;
	}
	
	public void powiadom(Punktuj coPunktowac)
	{
		gra.powiadamiacz.powiadom(coPunktowac);
	}
}
