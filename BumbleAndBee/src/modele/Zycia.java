package modele;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Zycia extends Group {
	
	Image[] obrazki;
	public int ilosc = 0;
	static final int ODLEGLOSC_X = 35;
	
	public Zycia(int iloscPoczatkowa)
	{
		obrazki = new Image[iloscPoczatkowa];
		while (ilosc<iloscPoczatkowa)
			increment();
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	
	public void increment()
	{
		if (ilosc==obrazki.length)
			return;
		ilosc++;
		int index = ilosc-1;
		
		// wyrzuc obrazek braku zycia
		if (obrazki[index]!=null)
			obrazki[index].remove();
		
		obrazki[index] = new Image(PrzechowalniaAssets.posiadaneZycie);
		//obrazki[ilosc].setTransform(true);
		obrazki[index].setBounds(index*ODLEGLOSC_X, 0, PrzechowalniaAssets.posiadaneZycie.getRegionWidth(), PrzechowalniaAssets.posiadaneZycie.getRegionHeight());
		this.addActor(obrazki[index]);

	}
	
	/// zwraca false gdy nie ma juz wiecej zyc 
	public boolean zmniejszIKontynuuj()
	{
		if (ilosc==0)
			return false;
		
		ilosc--;
		
		// wyrzuc obrazek zycia z ostatniego obrazka
		if (obrazki[ilosc]!=null)
			obrazki[ilosc].remove();
		
		obrazki[ilosc] = new Image(PrzechowalniaAssets.straconeZycie);
		//obrazki[ilosc].setTransform(true);
		obrazki[ilosc].setBounds(ilosc*ODLEGLOSC_X, 0, PrzechowalniaAssets.straconeZycie.getRegionWidth(), PrzechowalniaAssets.straconeZycie.getRegionHeight());
		this.addActor(obrazki[ilosc]);		
		
		if (ilosc==0)
			return false;		
		return true;
	}	
}
