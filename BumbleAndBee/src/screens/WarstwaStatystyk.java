package screens;


import modele.InterfejsHP;
import modele.PostepPoziomu;
import modele.Pszczola;
import modele.Zycia;
import zdazenia.Dyrektor;
import zdazenia.Punktuj;
import zdazenia.RejestratorPunktacji;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaStatystyk extends Warstwa implements RejestratorPunktacji {
	
	public static final int ZYCIA_X = 10;
	public static final int ZYCIA_Y_TOP = 70;
	
	public static final int MONETA_X = 180;	
	public static final int ILE_MONET_X = MONETA_X + 35;	
	public static final int MONETA_Y_TOP = ZYCIA_Y_TOP;
	
	public static final int MIOD_X = 260;	
	public static final int ILE_MIODU_X = MIOD_X + 45;	
	public static final int MIOD_Y_TOP = ZYCIA_Y_TOP;	
		
	public static final int ILE_Y_TOP = ZYCIA_Y_TOP-5;	
	
	public static final int CZAS_Y_TOP = 10;
	
	public static final int PASEKHP_X = 2;
	public static final int PASEKHP_Y_TOP = 35;
	
	PostepPoziomu postepPoziomu;
	
	InterfejsHP interfejsHP;
	long czas = System.nanoTime();

	Label labelIleMonet, labelIleMiodu, labelCzas;
	Zycia posiadaneZycia;
	public Pszczola pszczola;		
	
	public Dyrektor dyrektor; 
	
	public WarstwaStatystyk(BumbleAndBee maingame, Dyrektor dyrektorPoziomu)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		dyrektor = dyrektorPoziomu;
		gra.powiadamiacz.dodajSluchaczaPunktacji(this);	
		
		postepPoziomu = new PostepPoziomu();
		addActor(postepPoziomu);	
		
		// przygotuj style dla aktorow
		Skin skin = new Skin();
		skin.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin.add("maly", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI14, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin.add("mniejszy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI15, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin.add("duzy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI72, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		
		//---------------------------WYŒWIETLENIE INFORMACJI O LVL -------------------------------------
		// bez podania nazwy stylu uzyje stylu 'default'
		Label nazwaPoziomu = new Label("Poziom: 1",skin);
		nazwaPoziomu.setX(SkalowalnyEkran.BASE_WIDTH-nazwaPoziomu.getWidth()-20);
		nazwaPoziomu.setY(SkalowalnyEkran.BASE_HEIGHT-nazwaPoziomu.getHeight());
		addActor(nazwaPoziomu);			

		// uzyj stylu 'maly'
		Label trudnosc = new Label("Poziom trudnoœci: £atwy",skin,"maly");
		trudnosc.setX(SkalowalnyEkran.BASE_WIDTH-trudnosc.getWidth()-10);
		trudnosc.setY(SkalowalnyEkran.BASE_HEIGHT-trudnosc.getHeight()-nazwaPoziomu.getHeight());		
		addActor(trudnosc);
		//---------------------------koniec WYŒWIETLENIE INFORMACJI O LVL -------------------------------------		
		
		//--------------------------- WYŒWIETLENIE ZMIENNYCH -------------------------------------
		labelIleMonet = new Label(": 0",skin,"mniejszy");
		labelIleMonet.setX(ILE_MONET_X);
		labelIleMonet.setY(SkalowalnyEkran.BASE_HEIGHT-ILE_Y_TOP);		
		addActor(labelIleMonet);

		labelIleMiodu = new Label(": 0",skin,"mniejszy");
		labelIleMiodu.setX(ILE_MIODU_X);
		labelIleMiodu.setY(SkalowalnyEkran.BASE_HEIGHT-ILE_Y_TOP);		
		addActor(labelIleMiodu);		
		
		Image moneta = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/moneta.png"))));
		moneta.setX(WarstwaStatystyk.MONETA_X);
		moneta.setY(SkalowalnyEkran.BASE_HEIGHT - WarstwaStatystyk.MONETA_Y_TOP);
		addActor(moneta);
		
		Image miod = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/miod.png"))));
		miod.setX(WarstwaStatystyk.MIOD_X);
		miod.setY(SkalowalnyEkran.BASE_HEIGHT - WarstwaStatystyk.MIOD_Y_TOP);
		addActor(miod);		
		
		// zycia
		posiadaneZycia = new Zycia(4);
		posiadaneZycia.setX(ZYCIA_X);
		posiadaneZycia.setY(SkalowalnyEkran.BASE_HEIGHT - ZYCIA_Y_TOP);
		addActor(posiadaneZycia);			
		//--------------------------- koniec WYŒWIETLENIE ZMIENNYCH -------------------------------------
		
		// czas
		labelCzas = new Label(czas(),skin,"duzy");
		labelCzas.setX((SkalowalnyEkran.BASE_WIDTH - labelCzas.getWidth())/2);
		labelCzas.setY(SkalowalnyEkran.BASE_HEIGHT - labelCzas.getHeight() - CZAS_Y_TOP);		
		addActor(labelCzas);		
		
		interfejsHP = new InterfejsHP();
		interfejsHP.setX(PASEKHP_X);
		interfejsHP.setY(SkalowalnyEkran.BASE_HEIGHT - PASEKHP_Y_TOP);
		addActor(interfejsHP);
		
		// pszczole bedziemy rysowac na warstwie statystyk bo sie nie ma przesuwac z kamera
		pszczola = new Pszczola(100, 100);
		this.addActor(pszczola);		
		this.addActor(pszczola.zadlo);
		
		gra.dodajAktoraStrzalu(this,pszczola,dyrektor);
		
		this.setKeyboardFocus(pszczola);	
	}
	
	public void act(float delta, float pozycjaKameryProc)
	{
		labelCzas.setText(czas());
		postepPoziomu.ustawProcent(pozycjaKameryProc);
		super.act(delta);			
	}	

	
	@Override
	public void draw () {
		super.draw();		
		
		//Tester.narysujGraniceObiektu(pszczola);		
		//for (int i = miodki.size()-1; i>=0; i--)	
		//	Tester.narysujGraniceObiektu(miodki.get(i));	
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
		{
			gra.ileMiodu++;
			labelIleMiodu.setText(": "+ gra.ileMiodu);			
		}
		if (rodzaj == Punktuj.monety)
		{
			gra.ileMonet++;
			labelIleMonet.setText(": "+ gra.ileMonet);
		}
		if (rodzaj == Punktuj.zycie)
		{
			interfejsHP.zmniejsz();
			if (interfejsHP.zero())
			{
				if (!posiadaneZycia.zmniejszIKontynuuj())
					dyrektor.ustawAktywnaWarstwe(TypWarstwy.porazka);
				else
					interfejsHP.ustawPelen();
			}
		}		
	}	

	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.statystyki;
	}	

}
