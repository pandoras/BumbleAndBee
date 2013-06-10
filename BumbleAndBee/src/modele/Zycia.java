package modele;

import inne.PrzechowalniaAssets;
import screens.Warstwa;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.majapiotr.bumbleandbee.BumbleAndBee;


public class Zycia extends Group {
	
	Image[] obrazki;
	static final int ODLEGLOSC_X = 35;
	BumbleAndBee gra;
	
	public Zycia(int iloscMaksymalna, BumbleAndBee naszaGra)
	{
		gra = naszaGra;
		obrazki = new Image[iloscMaksymalna];
		int ilosc = 0;
		while (ilosc<gra.liczbaSerduszek)
		{
			dodajSerce(ilosc);
			ilosc += 1;
		}
		while (ilosc<iloscMaksymalna)
		{
			dodajPusteSerce(ilosc);
			ilosc += 1;
		}
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	
	public void dodajSerce(int nrSercaOdZero)
	{
		int index = nrSercaOdZero;
		obrazki[index] = new Image(PrzechowalniaAssets.posiadaneZycie);
		//obrazki[ilosc].setTransform(true);
		obrazki[index].setBounds(index*ODLEGLOSC_X, 0, PrzechowalniaAssets.posiadaneZycie.getRegionWidth(), PrzechowalniaAssets.posiadaneZycie.getRegionHeight());
		this.addActor(obrazki[index]);		
	}
	
	public void dodajPusteSerce(int nrSercaOdZero)
	{
		int index = nrSercaOdZero;
		obrazki[index] = new Image(PrzechowalniaAssets.straconeZycie);
		//obrazki[ilosc].setTransform(true);
		obrazki[index].setBounds(index*ODLEGLOSC_X, 0, PrzechowalniaAssets.straconeZycie.getRegionWidth(), PrzechowalniaAssets.straconeZycie.getRegionHeight());
		this.addActor(obrazki[index]);		
	}	
	
	public void increment()
	{
		if (gra.liczbaSerduszek==obrazki.length)
			return;
		gra.liczbaSerduszek++;
		
		// wyrzuc obrazek braku zycia
		if (obrazki[gra.liczbaSerduszek-1]!=null)
			obrazki[gra.liczbaSerduszek-1].remove();
		
		dodajSerce(gra.liczbaSerduszek);
	}
	
	/// zwraca false gdy nie ma juz wiecej zyc 
	public boolean zmniejszIKontynuuj()
	{
		if (gra.liczbaSerduszek==0)
			return false;
		
		gra.liczbaSerduszek--;
		
		// wyrzuc obrazek zycia z ostatniego obrazka
		if (obrazki[gra.liczbaSerduszek]!=null)
			obrazki[gra.liczbaSerduszek].remove();
		
		obrazki[gra.liczbaSerduszek] = new Image(PrzechowalniaAssets.straconeZycie);
		//obrazki[ilosc].setTransform(true);
		obrazki[gra.liczbaSerduszek].setBounds(gra.liczbaSerduszek*ODLEGLOSC_X, 0, PrzechowalniaAssets.straconeZycie.getRegionWidth(), PrzechowalniaAssets.straconeZycie.getRegionHeight());
		this.addActor(obrazki[gra.liczbaSerduszek]);		
		
		if (gra.liczbaSerduszek==0)
			return false;		
		return true;
	}	
}
