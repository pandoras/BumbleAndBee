package screens;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class OknoOpcji extends Window {

	private static final int OPCJE_WIDTH = 300;
	private static final int OPCJE_HEIGHT = 200;	
	
	private static final int CLOSE_X = (SkalowalnyEkran.BASE_HEIGHT + OPCJE_WIDTH)/2;
	private static final int CLOSE_Y = 560;
	
	private static final  Skin windowskin = new Skin(Gdx.files.internal("skin/window.json"));
	Image closeImage;
	OknoOpcji oknoOpcji;
	
	public OknoOpcji(BumbleAndBee gra, Stage warstwa)
	{
		super("OPCJE", windowskin);
		oknoOpcji = this;
		this.setLayoutEnabled(false);
		
		this.setWidth(OPCJE_WIDTH);
		this.setHeight(OPCJE_HEIGHT);
		
		this.setX((SkalowalnyEkran.BASE_WIDTH-OPCJE_WIDTH)/2);
		this.setY((SkalowalnyEkran.BASE_HEIGHT-OPCJE_HEIGHT)/2);	
		
		// przycisk zamknij
		closeImage = new Image(PrzechowalniaAssets.textureSklepZamknij);
		//closeImage.setX((SkalowalnyEkran.BASE_WIDTH +OPCJE_WIDTH)/2 - PrzechowalniaAssets.textureSklepZamknij.getRegionWidth());
		//closeImage.setY((SkalowalnyEkran.BASE_HEIGHT + OPCJE_HEIGHT)/2 - PrzechowalniaAssets.textureSklepZamknij.getRegionHeight());

		closeImage.setX(OPCJE_WIDTH - PrzechowalniaAssets.textureSklepZamknij.getRegionWidth());
		closeImage.setY(OPCJE_HEIGHT - PrzechowalniaAssets.textureSklepZamknij.getRegionHeight());

		warstwa.addActor(this);
		this.addActor(closeImage);
		closeImage.addListener(new InputListener() {
		      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	  closeImage.remove();
		    	  oknoOpcji.remove();
		         return false;
		      }}
		);
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		batch.draw(PrzechowalniaAssets.textureSklepZamknij,
				(SkalowalnyEkran.BASE_WIDTH -OPCJE_WIDTH)/2 + closeImage.getX(), 
				(SkalowalnyEkran.BASE_HEIGHT -OPCJE_HEIGHT)/2 + closeImage.getY(), 
				closeImage.getOriginX(), closeImage.getOriginY(),
				closeImage.getWidth(), closeImage.getHeight(), 
				closeImage.getScaleX(), closeImage.getScaleY(), closeImage.getRotation());
	}
}
