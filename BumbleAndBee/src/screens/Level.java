package screens;

import modele.Pszczola;
import inne.InterfejsHP;
import inne.PostepPoziomu;
import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;
import inne.WyswietlaniePrzeciwnikow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


public class Level implements Screen  {	

	WyswietlaniePrzeciwnikow wyswietlaniePrzeciwnikow = new WyswietlaniePrzeciwnikow();
	
	Pszczola pszczola = new Pszczola();
	PostepPoziomu postepPoziomu = new PostepPoziomu();
	InterfejsHP interfejsHP = new InterfejsHP();
	long czas = System.nanoTime();
	
	public String czas() {
		// DZIELE PRZEZ 10^-9 BO NANOTIME
		long sekundy = (System.nanoTime() - czas) / 1000000000;
		int minuty = (int) sekundy / 60;
		
		// WYSWIETLENIE 0 PRZED SEKUNDAMI (GDY < 10)
		if(sekundy % 60 < 10) return minuty + ":0" + sekundy % 60;
		else return minuty + ":" + sekundy % 60;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.15f, 0.58f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// RYSOWANIE TEKSTU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": 100", 110, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI15, ": 3", 260, Gdx.graphics.getHeight() - 45, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
		// WYSWIETLENIE PSZCZOLY
		//rysujBitmape.wyswietlBitmape(spritePszczola, 90, Gdx.graphics.getHeight() - 200);
		this.pszczola.NarysujMnie();
		
		// RYSOWANIE CZASU
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI72, czas(), Gdx.graphics.getWidth() / 2 - 72, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE MONETY, SERC, MIODKU
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMoneta, 80, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePosiadaneHP, 10, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteStraconePosiadaneHP, 45, Gdx.graphics.getHeight() - 70);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteMiod, 220, Gdx.graphics.getHeight() - 70);
		
		// WYŒWIETLENIE INFORMACJI O LVL
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI_Light32, "Poziom: 1", Gdx.graphics.getWidth() - 144, Gdx.graphics.getHeight() - 10, 1f, 1f, 1f, 1f);
		NarzedziaBitmapy.wyswietlText(PrzechowalniaAssets.fontSegoeUI14, "Poziom trudnoœci: £atwy", Gdx.graphics.getWidth() - 160, Gdx.graphics.getHeight() - 40, 1f, 1f, 1f, 1f);
		
		// RYSOWANIE PASKA HP
		interfejsHP.wyswietlHP();
		
		// WYSWIETLA DOLNY PASEK POSTEPU LEVELU
		postepPoziomu.wyswietlPasekPostepu((System.nanoTime() - czas) / 1000000000);
		
		// PRZECIWNICY
		wyswietlaniePrzeciwnikow.obslugaPrzeciwnikow();
	}

	@Override
	public void resize(int width, int height) {
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
	public void dispose() {
		
	}

}
