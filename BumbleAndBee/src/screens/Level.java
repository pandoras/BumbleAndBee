package screens;


import modele.Pszczola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.majapiotr.bumbleandbee.BumbleAndBee;
import com.majapiotr.bumbleandbee.IPrzycisk;


public class Level extends SkalowalnyEkran  {	
	
	protected static final int WIDTH_MULTIPLIER = 2;
	protected static final int LEVEL_WIDTH = BASE_WIDTH*WIDTH_MULTIPLIER;
	
	IPrzycisk przyciskStrzalu;

	// mamy na razie 2 warstwy, menu na pauze albo koniec levelu bedzie 3-cia warstwa
	private WarstwaTla przesuwaneT³o;
	private WarstwaStatystyk statystyki;
	
	// kamera jest w pozycji 0 na poczatku poziomu a w pozycji 1 na koncu
	private float pozycjaKameryProc;
	
	Pszczola pszczola;	
	
	public Level (BumbleAndBee gra,IPrzycisk przycisk)
	{
		super(gra);
		przyciskStrzalu = przycisk;
		przesuwaneT³o = new WarstwaTla(WIDTH_MULTIPLIER, gra);
		statystyki = new WarstwaStatystyk(gra);
		
		pozycjaKameryProc = 0;
		
		pszczola = new Pszczola();
		statystyki.addActor(pszczola);
		
		// TODO: to trzeba zrobic zamiast sprawdzac spacjê w pszczole
		//Gdx.input.setInputProcessor(new Dyrektor());			
    }
	
	@Override
	public void render(float delta) 
	{
		super.render(delta);
		
		float pozycjaKamery = rozdzielczosc.x/2 +  pozycjaKameryProc * (WIDTH_MULTIPLIER-1)*rozdzielczosc.x;
		// przesun kamere
		pozycjaKamery += Gdx.graphics.getDeltaTime()*50;
		
		// odswiez procent
		pozycjaKameryProc = (pozycjaKamery - rozdzielczosc.x/2) / ((WIDTH_MULTIPLIER-1)*rozdzielczosc.x);
		if (pozycjaKameryProc>1)
		{	
			gra.pokazMenu();
			return;
		}	
		
		// odswierz i narysuj wszystkie poziomy 
		przesuwaneT³o.render(Math.min(Gdx.graphics.getDeltaTime(), 1/30f), new Vector3(pozycjaKamery, rozdzielczosc.y/2, 0));	
		statystyki.render(Math.min(Gdx.graphics.getDeltaTime(), 1/30f), pozycjaKameryProc);
		
		// WYSWIETLENIE PSZCZOLY
		this.pszczola.odswierzPozycje(pozycjaKamery- rozdzielczosc.x/2, delta);
		//this.pszczola.NarysujMnie();
		
		przesuwaneT³o.sprawdzKolizje(pszczola);
	}

	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		przesuwaneT³o.setViewport(rozdzielczosc.x*2, rozdzielczosc.y, true);
		przesuwaneT³o.getCamera().viewportWidth = rozdzielczosc.x;
		przesuwaneT³o.getCamera().viewportHeight = rozdzielczosc.y;
		
		float pozycjaKamery = rozdzielczosc.x/2 +  pozycjaKameryProc * (WIDTH_MULTIPLIER-1)*rozdzielczosc.x;
		przesuwaneT³o.getCamera().position.set(pozycjaKamery, rozdzielczosc.y/2, 0);
		
		for (Actor a : przesuwaneT³o.getActors()) {
			a.setScale(skala);
		}	
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
