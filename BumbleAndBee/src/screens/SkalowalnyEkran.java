package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class SkalowalnyEkran  implements Screen {

	protected static final int BASE_WIDTH = 1200;
	protected static final int BASE_HEIGHT = 700;
	protected static final float ASPECT_RATIO = (float)BASE_WIDTH/(float)BASE_HEIGHT;	
	
	protected BumbleAndBee gra;
	protected Vector2 przyciecie;
	protected Vector2 rozdzielczosc;
	protected float skala;
	
	public SkalowalnyEkran(final BumbleAndBee maingame)
	{
		gra=maingame;
		rozdzielczosc = new Vector2(BASE_WIDTH, BASE_HEIGHT);
	}
	
	@Override
	public void resize(int width, int height) {		
		// liczymy skale
		setScale(width, height);	
		rozdzielczosc.x = (float)BASE_WIDTH*skala;
		rozdzielczosc.y = (float)BASE_HEIGHT*skala;	
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
		// zwolnij obiekt estrady gdy juz nie uzywamy tej sceny
	}	

	@Override
	public void render(float delta) {
		// clear previous frame
		Gdx.gl.glClearColor(0.15f, 0.58f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// TODO Auto-generated method stub
		
	}
	
	protected void setScale(int width, int height)
	{
	    float aspectRatio = (float)width/(float)height;
	    przyciecie = new Vector2(0f, 0f);

	    if(aspectRatio > ASPECT_RATIO)
	    {
	        skala = (float)height/(float)BASE_HEIGHT;
	        przyciecie.x = (width - BASE_WIDTH*skala)/2f;
	    }
	    else if(aspectRatio < ASPECT_RATIO)
	    {
	    	skala = (float)width/(float)BASE_WIDTH;
	    	przyciecie.y = (height - BASE_HEIGHT*skala)/2f;
	    }
	    else
	    {
	    	skala = (float)width/(float)BASE_WIDTH;
	    }
	}
}
