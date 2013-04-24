package inne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PostepPoziomu {
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	Sprite spritePszczola = new Sprite();
	
	public PostepPoziomu() {
		spritePszczola = NarzedziaBitmapy.wczytajBitmape("data/pszczola.png");
	}
	
	public void wyswietlPasekPostepu(long sekundy) {
		wyswietlLiniePaska();
		wyswietlPszczolePaska(sekundy);
	}
	
	public void wyswietlLiniePaska() {		
		// WYŒWIETLENIE PASKA POSTEPU GRY
		shapeRenderer.begin(ShapeType.FilledRectangle);
			shapeRenderer.setColor(1, 1, 1, 1);
			for(int i = 0; i < Gdx.graphics.getWidth() / 47; i++) {
				shapeRenderer.filledRect(i * 45 + 40, 20, 30, 3);
			}
			
			shapeRenderer.filledRect(30, 5, 3, 35);
			shapeRenderer.filledRect(Gdx.graphics.getWidth() - 40, 5, 3, 35);
		shapeRenderer.end();
	}
	
	public void wyswietlPszczolePaska(long sekundy) {
		NarzedziaBitmapy.wyswietlBitmape(spritePszczola, 5 + Gdx.graphics.getWidth() / 120 * (int)sekundy, 5);
	}
}
