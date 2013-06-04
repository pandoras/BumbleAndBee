package modele;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

public class Zadlo {
	public int ekranowyx = -1;
	public int y = -1;
	
	public Zadlo() {
		ekranowyx = -1;
		y = -1;
	}
	
	public void przesun()
	{
		ekranowyx += 5;
	}
	
	public boolean pozaEkranem()
	{
		if(ekranowyx<0 || ekranowyx>1200)
			return true;
		return false;
	}
	
	public void wyswietl()
	{
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteZadlo, this.ekranowyx, this.y);
	}
	
	public void wysun(Pszczola pszczola)
	{
		ekranowyx = (int)pszczola.ekranowyx+80;
		y = (int)pszczola.getY()+20;		
	}
}
