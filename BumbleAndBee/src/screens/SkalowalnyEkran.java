package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class SkalowalnyEkran  implements Screen {

	public static final int BASE_WIDTH = 1200;
	public static final int BASE_HEIGHT = 700;
	
	public BumbleAndBee gra;
	
	public SkalowalnyEkran(final BumbleAndBee maingame)
	{
		gra=maingame;
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
	
	@Override
	public void resize(int width, int height) {
	}		

	@Override
	public void render(float delta) {
		// clear previous frame
		Gdx.gl.glClearColor(0.05f, 0.28f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
	}

}
