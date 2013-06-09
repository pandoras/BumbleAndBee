package screens;

import java.util.ArrayList;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Scaling;
import com.majapiotr.bumbleandbee.BumbleAndBee;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

public class WarstwaSklepu extends Warstwa {
		
	private static final int CLOSE_X = 990;
	private static final int CLOSE_Y = 560;
	
	private static final int MIOD_X = 220;
	private static final int MIOD_Y = 570;

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
		
		// przygotuj style dla aktorow
		Skin skin1 = new Skin();
		skin1.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin1.add("maly", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI14, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin1.add("mniejszy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI15, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin1.add("duzy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI72, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		
		// liczba miodow
		Image miodImage = new Image(new TextureRegion(PrzechowalniaAssets.miod));
		miodImage.setX(MIOD_X);
		miodImage.setY(MIOD_Y);
		this.addActor(miodImage);
		
		Label liczbaMiodu = new Label("100", skin1);
		liczbaMiodu.setX(MIOD_X + 40);
		liczbaMiodu.setY(MIOD_Y - 5);
		this.addActor(liczbaMiodu);
		
		// przycisk zamknij
		Image closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureSklepZamknij));
		closeImage.setX(CLOSE_X);
		closeImage.setY(CLOSE_Y);
		this.addActor(closeImage);
		closeImage.addListener(new InputListener() {
		      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	 ((Level)gra.getScreen()).dyrektor.ustawAktywnaWarstwe(TypWarstwy.statystyki);
		         return false;
		      }}
		);
		
		// przyciski kup
		for(int i = 0; i < 8; i++) {
			Image kupImage = new Image(new TextureRegion(PrzechowalniaAssets.textureSklepKup));
			kupButtons.add(kupImage);
			kupButtons.get(i).setPosition(275 + (i % 4) * 193, 140 + 200 * (i / 4));
			this.addActor(kupButtons.get(i));
			
			kupButtons.get(i).addListener(new InputListener() {
			      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			    	 ((Level)gra.getScreen()).dyrektor.ustawAktywnaWarstwe(TypWarstwy.statystyki);
			         return false;
			      }}
			);
		}
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.sklep;
	}	
}
