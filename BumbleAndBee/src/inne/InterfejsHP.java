package inne;

import screens.SkalowalnyEkran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class InterfejsHP extends Group {	
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	Texture teksturaZewnetrzna, teksturaHP, teksturaBrakHP;
	public static final int SZEROKOSC = 320;	
	public static final int WYSOKOSC = 30;	
	
	public float procentZdrowia = 0.8f;
	Label labelIle;
	
	public InterfejsHP() {

		// zainicjuj teksturê t³a
		Pixmap p = new Pixmap(1, 1, Format.RGBA8888);
		p.setColor(new Color(0, 0, 0, 1));
		p.fillRectangle(0, 0, 1, 1);	
		teksturaZewnetrzna = new Texture(p, Format.RGB888, false);		
		
		Pixmap p2 = new Pixmap(1, 1, Format.RGBA8888);
		p2.setColor(new Color(0.866f, 0.058f, 0.25f, 1));
		p2.fillRectangle(0, 0, 1, 1);	
		teksturaHP = new Texture(p2, Format.RGB888, false);	
		
		Pixmap p3 = new Pixmap(1, 1, Format.RGBA8888);
		p3.setColor(new Color(0.63f, 0.11f, 0.16f, 1));
		p3.fillRectangle(0, 0, 1, 1);	
		teksturaBrakHP = new Texture(p3, Format.RGB888, false);	
		//this.setBounds(0, 0, SZEROKOSC, WYSOKOSC);

		Skin skin = new Skin();
		skin.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI18, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);

		labelIle = new Label(""+(int)(procentZdrowia*100)+"%",skin);
		labelIle.setX(145);
		labelIle.setY(4);		
		addActor(labelIle);			
	}
	
	 @Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		int szerokoscHP = (int)(procentZdrowia*SZEROKOSC);
		int szerokoscBrakHP = SZEROKOSC - szerokoscHP;
		
		batch.setColor(getColor());
		batch.draw(teksturaZewnetrzna, getX(), getY(), SZEROKOSC, WYSOKOSC);
		batch.draw(teksturaHP, getX()+2, getY()+2, szerokoscHP-2, WYSOKOSC-5);
		batch.draw(teksturaBrakHP, getX()+szerokoscHP, getY()+2, szerokoscBrakHP-2, WYSOKOSC-5);
		super.draw(batch, parentAlpha);
	}
}
