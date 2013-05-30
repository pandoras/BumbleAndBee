package inne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PostepPoziomu extends Actor
{
	Sprite spritePszczola;
	TextureRegion linijka;
	float procentLevelu;
	
	public PostepPoziomu() {
		procentLevelu = 0f;
		spritePszczola = NarzedziaBitmapy.wczytajBitmape("data/pszczola.png");
		linijka = new TextureRegion(new Texture(Gdx.files.internal("data/postep.png")));
		setX(30);
		setY(5);
		setWidth(linijka.getRegionWidth());
		setHeight(linijka.getRegionHeight());
	}
	
   @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
	       
		batch.setColor(getColor());
		// linijka jest rysowanya w calym oknie aktora
		batch.draw(linijka, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
			getRotation());
		// pszczole trzeba przeskalowac + przesunac o procent z szerokosci minus szerokosc pszczoly
		float szerokoscPszczoly = spritePszczola.getWidth() * this.getScaleX();
		float wysokoscPszczoly = spritePszczola.getHeight() * this.getScaleY();
		batch.draw(spritePszczola, getX() + procentLevelu * (getWidth() - szerokoscPszczoly), getY(), getOriginX(), getOriginY(), 
				szerokoscPszczoly,	wysokoscPszczoly,
				getScaleX(), getScaleY(), getRotation());		
		super.draw(batch, parentAlpha);
    }
   public void ustawProcent(float procent)
   {
	   procentLevelu = procent;
   }

}

