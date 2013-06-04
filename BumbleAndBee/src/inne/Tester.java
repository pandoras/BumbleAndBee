package inne;

import modele.IObiekt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tester {

	public static Pixmap debugPixel;
	public static Texture debugTexture;
	public static TextureRegion debugRegion;

	public static PolygonSpriteBatch debugBatch;
	
	public static void init()
	{
		// for marking position
		debugPixel = new Pixmap(1, 1, Format.RGBA8888);
		debugPixel.setColor(Color.RED);
		debugPixel.fillRectangle(0, 0, 1, 1);
		
	    debugTexture = new Texture(debugPixel, Format.RGB888, false);	
	    debugRegion = new TextureRegion(debugTexture);
	    
	    debugBatch = new PolygonSpriteBatch();
	    
	}
	
	// do testowania obiektuw na ekranie
	// uwaga! obiekt musi miec dobrze obilczone granice ekranowe czylo odswierzony ekranowy x
	public static void narysujGraniceObiektu(IObiekt obiekt)
	{
		debugBatch.begin();
		debugBatch.draw(new PolygonRegion(debugRegion, obiekt.pobierzGraniceEkranowe()), 0, 0);
		debugBatch.end();				
	}
}
