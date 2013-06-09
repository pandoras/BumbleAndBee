package screens;

import java.util.ArrayList;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaSklepu extends Warstwa {
		
	private static final int CLOSE_X = 990;
	private static final int CLOSE_Y = 560;

	private static final int KUP_X = 500;
	private static final int KUP_Y = 400;
	
	ArrayList<Image> kupButtons = new ArrayList<Image>();
	
	public WarstwaSklepu(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Table window = new Table(skin);
		window.size(900, 525);
		window.setX((SkalowalnyEkran.BASE_WIDTH-window.getWidth())/2);
		window.setY((SkalowalnyEkran.BASE_HEIGHT-window.getHeight())/2);
		this.addActor(window);
		
		// tlo okna
		Image backgroundImage = new Image(new TextureRegion(PrzechowalniaAssets.textureSklep));		
		backgroundImage.setScaling(Scaling.fill);
		backgroundImage.setAlign(Align.center);
		window.add(backgroundImage);
		
		// przycisk zamknij
		Image closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureSklepZamknij));
		closeImage.setX(CLOSE_X);
		closeImage.setY(CLOSE_Y);
		this.addActor(closeImage);
		
		// przyciski kup
		for(int i = 0; i < 8; i++) {
			Image kupImage = new Image(new TextureRegion(PrzechowalniaAssets.textureSklepKup));
			kupButtons.add(kupImage);
			//kupButtons.get(i).setPosition(x, y);
		}
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.sklep;
	}	
}
