package screens;


import modele.Pszczola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.majapiotr.bumbleandbee.BumbleAndBee;
import com.majapiotr.bumbleandbee.IPrzycisk;


public class Level extends SkalowalnyEkran  {	
	
	protected static final int WIDTH_MULTIPLIER = 3;
	protected static final int LEVEL_WIDTH = BASE_WIDTH*WIDTH_MULTIPLIER;
	
	IPrzycisk przyciskStrzalu;

	// mamy na razie 2 warstwy, menu na pauze albo koniec levelu bedzie 3-cia warstwa
	private WarstwaTla przesuwaneTło;
	private WarstwaStatystyk statystyki;
	
	// kamera jest w pozycji 0 na poczatku poziomu a w pozycji 1 na koncu
	private float pozycjaKameryProc;
	
	// pozycja kamery w pikselach na poczatku i koncu levelu (trzeba przeliczyc na resize)
	float pozycjaKameryPxStart, pozycjaKameryPxEnd; 
			
	Pszczola pszczola;	
	
	public Level (BumbleAndBee gra,IPrzycisk przycisk)
	{
		super(gra);
		przyciskStrzalu = przycisk;
		przesuwaneTło = new WarstwaTla(WIDTH_MULTIPLIER, gra);
		statystyki = new WarstwaStatystyk(gra);
		
		pozycjaKameryProc = 0;
		
		pszczola = new Pszczola(100, 100);
		statystyki.addActor(pszczola);
		
		// TODO: to trzeba zrobic zamiast sprawdzac spację w pszczole
		//Gdx.input.setInputProcessor(new Dyrektor());			
    }
	
	@Override
	public void render(float delta) 
	{
		super.render(delta);

		przesunKamere(delta);
		
		// odswierz i narysuj wszystkie poziomy (jest tak pozdielione bo na pause nie robi sie act(), tylko draw())
		przesuwaneTło.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));	
		przesuwaneTło.draw();
		
		statystyki.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f), pozycjaKameryProc);
		statystyki.draw();
		
		// odswierz pozycje pszczoly w levelu
		this.pszczola.odswierzPozycje(przesuwaneTło.getCamera().position.x-BASE_WIDTH/2 , delta);
		
		przesuwaneTło.sprawdzKolizje(pszczola);
	}
	
	public void przesunKamere(float delta)
	{
		/*
		 *  OBLICZENIA KAMERY
		 *  
		 *  viewportWidth = przesuwaneTło.getCamera().viewportWidth
		 *  gutterWidth = przesuwaneTło.getGutterWidth()
		 *  translateX - przesuniecie kamery ustawiane poleceniem : przesuwaneTło.getCamera().translate(translateX, 0, 0)
		 *  cameraX - pozycja kamery ktora mozna pobrac: przesuwaneTło.getCamera().position.x
		 *  
		 *  RÓWNANIA:
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
		przesuwaneTło.getCamera().translate(delta * 50, 0, 0);
		
		// oblicz pozycje kamery w procentach
		pozycjaKameryProc = (przesuwaneTło.getCamera().position.x - pozycjaKameryPxStart) / (pozycjaKameryPxEnd - pozycjaKameryPxStart);
		if (pozycjaKameryProc>=1f)
		{	
			gra.pokazMenu();
			return;
		}	
	}

	
	@Override
	public void resize(int width, int height) {
		
		statystyki.setViewport(BASE_WIDTH, BASE_HEIGHT, true);
		przesuwaneTło.setViewport(BASE_WIDTH, BASE_HEIGHT, true);	
		
		// Wysrodkuj kamere ( dla warstwy przesuwnej nie przesuwaj w x)
		statystyki.getCamera().translate(-statystyki.getGutterWidth(), -statystyki.getGutterHeight(), 0);		
		przesuwaneTło.getCamera().translate(0, -przesuwaneTło.getGutterHeight(), 0);
		
		//
		// Policz ponownie pozycje poczatku i konca levelu
		//
		pozycjaKameryPxStart = przesuwaneTło.getCamera().viewportWidth/2;
		//
		// wersja end1 (patrz OBLICZENIA KAMERY):
		//pozycjaKameryPxEnd = BASE_WIDTH*WIDTH_MULTIPLIER - pozycjaKameryPxStart;
		//
		// wersja end2 (patrz OBLICZENIA KAMERY):
		pozycjaKameryPxEnd = BASE_WIDTH*WIDTH_MULTIPLIER - BASE_WIDTH/2;
		
		// Przesun kamere wg procentu postepu poziomu - obliczenie ze wzoru w przesunKamere() 
		przesuwaneTło.getCamera().position.x = ( pozycjaKameryProc * (pozycjaKameryPxEnd - pozycjaKameryPxStart)) + pozycjaKameryPxStart;
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

}
