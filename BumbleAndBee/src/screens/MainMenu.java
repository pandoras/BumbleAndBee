package screens;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class MainMenu implements Screen {
	
	BumbleAndBee game;
	
	NarzedziaBitmapy rysujBitmape = new NarzedziaBitmapy();
	
	public MainMenu(BumbleAndBee game) {
		this.game = game;
	}
	
	public int obslugaMyszki() {
		if(Gdx.input.isTouched()) {
			if(Gdx.input.getX() > 759 && Gdx.input.getX() < 1154) {
				if(Gdx.input.getY() > 113 && Gdx.input.getY() < 193) {
					return 1;
				} else if(Gdx.input.getY() > 193 && Gdx.input.getY() < 260) {
					return 2;
				} else if(Gdx.input.getY() > 260 && Gdx.input.getY() < 331) {
					return 3;
				}
			}
		}
		
		return 0;
	}
	
	public void menu(int opcjaMenu) {
		switch (opcjaMenu) {
			case 1: { 
				// START GRY
				game.setScreen(new Level());
				break;
			}
			case 2: { 
				// OPCJE
			}
			case 3: { 
				// EXIT
				game.dispose();
				break;
			}
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// RYSUJE TLO
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteTlo, 0, 0);
		
		// RYSUJE MENU
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteStartGry, 759, 510);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteOpcje, 759, 433);
		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spriteExit, 759, 354);
		
		// OBSLUGA MYSZKI I MENU
		menu(obslugaMyszki());
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