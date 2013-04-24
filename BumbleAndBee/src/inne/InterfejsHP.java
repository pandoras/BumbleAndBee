package inne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class InterfejsHP {	
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	BitmapFont fontSegoeUI18 = new BitmapFont();
	
	public InterfejsHP() {
		fontSegoeUI18 = NarzedziaBitmapy.wczytajFont(18, "SegoeUI");
	}
	
	public void wyswietlHP() {
		
		// WYŒWIETLENIE PASKA HP
		shapeRenderer.begin(ShapeType.FilledRectangle);
			shapeRenderer.setColor(0, 0, 0, 1);
			shapeRenderer.filledRect(8, Gdx.graphics.getHeight() - 37, 314, 30);
			shapeRenderer.setColor(0.866f, 0.058f, 0.25f, 1);
			shapeRenderer.filledRect(10, Gdx.graphics.getHeight() - 35, 280, 25);
			shapeRenderer.setColor(0.63f, 0.11f, 0.16f, 1);
			shapeRenderer.filledRect(280, Gdx.graphics.getHeight() - 35, 320 - 280, 25);
		shapeRenderer.end();
		
		// WYŒWIETLENIE TEKSTU NA HP
		NarzedziaBitmapy.wyswietlText(fontSegoeUI18, "100%", 145, Gdx.graphics.getHeight() - 14, 1f, 1f, 1f, 1f);
	}
}
