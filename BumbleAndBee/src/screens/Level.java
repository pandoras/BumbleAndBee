package screens;


import zdazenia.Dyrektor;

import com.badlogic.gdx.Gdx;
import com.majapiotr.bumbleandbee.BumbleAndBee;
import com.majapiotr.bumbleandbee.IPrzycisk;


public class Level extends SkalowalnyEkran  {	
	
	protected static final int WIDTH_MULTIPLIER = 3;
	protected static final int LEVEL_WIDTH = BASE_WIDTH*WIDTH_MULTIPLIER;

	// mamy na razie 2 warstwy, menu na pauze albo koniec levelu bedzie 3-cia warstwa
	public WarstwaTla przesuwaneT這;
	public WarstwaStatystyk statystyki;
	public WarstwaPauzy warstwaPauzy;
	public Warstwa warstwaPodsumowania, warstwaSklepu, warstwaPorazki;
	
	// kamera jest w pozycji 0 na poczatku poziomu a w pozycji 1 na koncu
	private float pozycjaKameryProc;
	
	// pozycja kamery w pikselach na poczatku i koncu levelu (trzeba przeliczyc na resize)
	float pozycjaKameryPxStart, pozycjaKameryPxEnd; 
	
	public Dyrektor dyrektor; 
	
	public Level (BumbleAndBee gra)
	{
		super(gra);

		// wszelki input idzie do dyrektora
		dyrektor = new Dyrektor(this);
		
		// warstwa przesuwanego tla i statystyk sa wyswietlane w czasie gry
		statystyki = new WarstwaStatystyk(gra, dyrektor);
		przesuwaneT這 = new WarstwaTla(WIDTH_MULTIPLIER, gra, statystyki.pszczola);
		
		
		// inne warstwy
		warstwaPauzy = new WarstwaPauzy(gra);
		warstwaPodsumowania = new WarstwaPodsumowania(gra);
		warstwaSklepu = new WarstwaSklepu(gra);
		warstwaPorazki = new WarstwaPorazki(gra);
				

		dyrektor.ustawAktywnaWarstwe(statystyki);		
		
		pozycjaKameryProc = 0;
    }
	
	@Override
	public void render(float delta) 
	{
		super.render(delta);	
			
		// odswierz i narysuj wszystkie poziomy (jest tak pozdielione bo na pause nie robi sie act(), tylko draw())		
		
		if (dyrektor.aktywnaWarstwa == TypWarstwy.tlo || dyrektor.aktywnaWarstwa == TypWarstwy.statystyki )
		{
			// odswierz pozycje pszczoly w levelu
			statystyki.pszczola.odswierzPozycje(przesuwaneT這.getCamera().position.x-BASE_WIDTH/2 , delta);			
			przesuwaneT這.sprawdzKolizje(statystyki.pszczola);
			
			przesunKamere(delta);
			przesuwaneT這.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));	
			statystyki.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f), pozycjaKameryProc);
		}
		
		przesuwaneT這.draw();				
		statystyki.draw();
		
		if (dyrektor.aktywnaWarstwa == TypWarstwy.pauza)
		{
			warstwaPauzy.act(delta);
			warstwaPauzy.draw();
		}
		
		else if (dyrektor.aktywnaWarstwa == TypWarstwy.podsumowanie)
		{
			warstwaPodsumowania.act(delta);
			warstwaPodsumowania.draw();
		}
		
		else if (dyrektor.aktywnaWarstwa == TypWarstwy.sklep)
		{
			warstwaSklepu.act(delta);
			warstwaSklepu.draw();
		}
		else if (dyrektor.aktywnaWarstwa == TypWarstwy.porazka)
		{
			warstwaPorazki.act(delta);
			warstwaPorazki.draw();
		}
	}
	
	public void przesunKamere(float delta)
	{
		/*
		 *  OBLICZENIA KAMERY
		 *  
		 *  viewportWidth = przesuwaneT這.getCamera().viewportWidth
		 *  gutterWidth = przesuwaneT這.getGutterWidth()
		 *  translateX - przesuniecie kamery ustawiane poleceniem : przesuwaneT這.getCamera().translate(translateX, 0, 0)
		 *  cameraX - pozycja kamery ktora mozna pobrac: przesuwaneT這.getCamera().position.x
		 *  
		 *  R紟NANIA:
		 *  
		 *  viewportWidth = BASE_WIDTH + 2*gutterWidth
		 *  cameraX = translateX + viewportWidth/2
		 *  
		 *  Dla naszych potrzeb kamera sie ma przesuwac:
		 *    start (jest poczatek levelu po lewej): viewportWidth/2 
		 *    end1 (jest poczatek levelu po prawej): BASE_WIDTH*WIDTH_MULTIPLIER - viewportWidth/2 
		 *    end2 (ostatni ekran levelu zaczyna sie po prawej): BASE_WIDTH*WIDTH_MULTIPLIER - BASE_WIDTH/2 - gutterWidth
		 *  
		 */

		// przesun kamere o troszke pikseli do przodu
		przesuwaneT這.getCamera().translate(delta * 50, 0, 0);
		
		// oblicz pozycje kamery w procentach
		pozycjaKameryProc = (przesuwaneT這.getCamera().position.x - pozycjaKameryPxStart) / (pozycjaKameryPxEnd - pozycjaKameryPxStart);
		if (pozycjaKameryProc>=1f)
		{	
			dyrektor.ustawAktywnaWarstwe(TypWarstwy.podsumowanie);
			return;
		}	
	}

	
	@Override
	public void resize(int width, int height) {
		
		statystyki.setViewport(BASE_WIDTH, BASE_HEIGHT, true);
		przesuwaneT這.setViewport(BASE_WIDTH, BASE_HEIGHT, true);	
		
		// Wysrodkuj kamere ( dla warstwy przesuwnej nie przesuwaj w x)
		statystyki.getCamera().translate(-statystyki.getGutterWidth(), -statystyki.getGutterHeight(), 0);		
		przesuwaneT這.getCamera().translate(0, -przesuwaneT這.getGutterHeight(), 0);
		
		//
		// Policz ponownie pozycje poczatku i konca levelu
		//
		pozycjaKameryPxStart = przesuwaneT這.getCamera().viewportWidth/2;
		//
		// wersja end1 (patrz OBLICZENIA KAMERY):
		//pozycjaKameryPxEnd = BASE_WIDTH*WIDTH_MULTIPLIER - pozycjaKameryPxStart;
		//
		// wersja end2 (patrz OBLICZENIA KAMERY):
		pozycjaKameryPxEnd = BASE_WIDTH*WIDTH_MULTIPLIER - BASE_WIDTH/2;
		
		// Przesun kamere wg procentu postepu poziomu - obliczenie ze wzoru w przesunKamere() 
		przesuwaneT這.getCamera().position.x = ( pozycjaKameryProc * (pozycjaKameryPxEnd - pozycjaKameryPxStart)) + pozycjaKameryPxStart;
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose () {
		//world.dispose();
	}

	public void zacznijNowy() {
		// TODO Auto-generated method stub
		
	}

}
