package screens;

import zdazenia.Punktuj;
import zdazenia.RejestratorPunktacji;
import zdazenia.Powiadamiacz;
import inne.InterfejsHP;
import inne.NarzedziaBitmapy;
import inne.PostepPoziomu;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaStatystyk extends Warstwa implements RejestratorPunktacji {
	
	public static final int MONETA_X = 80;	
	public static final int MONETA_Y_TOP = 70;
	
	public static final int MIOD_X = 220;	
	public static final int MIOD_Y_TOP = 70;	
	
	PostepPoziomu postepPoziomu;
	
	InterfejsHP interfejsHP = new InterfejsHP();
	long czas = System.nanoTime();
	
	public int ileMonet = 0;
	public int ileMiodu = 0;	
	
	public WarstwaStatystyk(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH, maingame);
		
		gra.powiadamiacz.addListener(this);
		
		postepPoziomu = new PostepPoziomu();
		addActor(postepPoziomu);	
		
		//---------------------------WYŒWIETLENIE INFORMACJI O LVL -------------------------------------
		Skin skin = new Skin();
		skin.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		// bez podania nazwy stylu uzyje stylu 'default'
		Label nazwaPoziomu = new Label("Poziom: 1",skin);
		nazwaPoziomu.setX(SkalowalnyEkran.BASE_WIDTH-nazwaPoziomu.getWidth()-20);
		nazwaPoziomu.setY(SkalowalnyEkran.BASE_HEIGHT-nazwaPoziomu.getHeight());
		addActor(nazwaPoziomu);	
		
		skin.add("mniejszy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI14, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		// uzyj stylu 'mniejszy'
		Label trudnosc = new Label("Poziom trudnoœci: £atwy",skin,"mniejszy");
		trudnosc.setX(SkalowalnyEkran.BASE_WIDTH-trudnosc.getWidth()-10);
		trudnosc.setY(SkalowalnyEkran.BASE_HEIGHT-trudnosc.getHeight()-nazwaPoziomu.getHeight());		
		addActor(trudnosc);
		//---------------------------koniec WYŒWIETLENIE INFORMACJI O LVL -------------------------------------		
	}
	
	public void render(float delta, float pozycjaKameryProc)
	{
		postepPoziomu.ustawProcent(pozycjaKameryProc);
		this.act(delta);		
		this.draw();		
		
		//Tester.narysujGraniceObiektu(pszczola);		
		//for (int i = miodki.size()-1; i>=0; i--)	
		//	Tester.narysujGraniceObiektu(miodki.get(i));	
		
		// to wszystko trzeba dodac do Stage zeby sie samo rysowalo
		
		// RYSOWANIE TEKSTU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": "+ileMonet, 110, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": "+ileMiodu, 260, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
				
		// RYSOWANIE MONETY, SERC, MIODKU
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMoneta, WarstwaStatystyk.MONETA_X, Gdx.graphics.getHeight() - WarstwaStatystyk.MONETA_Y_TOP);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePosiadaneHP, 10, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteStraconePosiadaneHP, 45, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMiod, WarstwaStatystyk.MIOD_X, Gdx.graphics.getHeight() -  WarstwaStatystyk.MIOD_Y_TOP);
			
		// RYSOWANIE PASKA HP
		interfejsHP.wyswietlHP();		
	}
	
	
	public String czas() {
		// DZIELE PRZEZ 10^-9 BO NANOTIME
		long sekundy = (System.nanoTime() - czas) / 1000000000;
		int minuty = (int) sekundy / 60;
		
		// WYSWIETLENIE 0 PRZED SEKUNDAMI (GDY < 10)
		if(sekundy % 60 < 10) return minuty + ":0" + sekundy % 60;
		else return minuty + ":" + sekundy % 60;
	}

	@Override
	public void naZmianePunktacji(Punktuj rodzaj) {
		
		if (rodzaj == Punktuj.miod)
			ileMiodu++;
		if (rodzaj == Punktuj.monety)
			ileMonet++;
		
	}	

	
}
