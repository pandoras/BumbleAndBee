package modele;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

public class Zadlo {
	public int x = -1;
	public int y = -1;
	
	public Zadlo() {
		x = -1;
		y = -1;
	}
	
	public void przesun()
	{
		x += 5;
	}
	
	public boolean pozaEkranem()
	{
		if(x<0 || x>1200)
			return true;
		return false;
	}
	
	public void wyswietl()
	{
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteZadlo, this.x, this.y);
	}
	
	public void wysun(Pszczola pszczola)
	{
		x = (int)pszczola.x+80;
		y = (int)pszczola.y+20;		
	}
}
