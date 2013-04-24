package inne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NarzedziaBitmapy {
	
	public static Sprite wczytajBitmape(String sciezka) {	
		Texture texture = new Texture(sciezka);
		Sprite sprite = new Sprite(texture);	
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return sprite;
	}
	
	public static BitmapFont wczytajFont(int wielkoscCzcionki, String rodzajCzcionki) {
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/" + rodzajCzcionki + "_" + wielkoscCzcionki + ".fnt"),
		Gdx.files.internal("fonts/" + rodzajCzcionki + "_" + wielkoscCzcionki + ".png"), false);
		
		return font;
	}
	
	public static void wyswietlText(BitmapFont bitmapFont, String tekst, int x, int y, float red, float green, float blue, float alpha) {
		bitmapFont.setColor(red, green, blue, alpha);
		PrzechowalniaAssets.spriteBatch.begin();
		bitmapFont.draw(PrzechowalniaAssets.spriteBatch, tekst, x, y);
		PrzechowalniaAssets.spriteBatch.end();
	}
	
	public static void wyswietlBitmape(Sprite sprite, int x, int y) {		
		
		PrzechowalniaAssets.spriteBatch.begin();
		sprite.setPosition(x, y);
		sprite.draw(PrzechowalniaAssets.spriteBatch);
		PrzechowalniaAssets.spriteBatch.end();
	}
}
