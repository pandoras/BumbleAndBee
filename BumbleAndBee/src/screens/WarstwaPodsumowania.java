package screens;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaPodsumowania extends Warstwa {

	//static final int WIDTH = 400;
	//static final int HEIGHT = 300;
	
	public Button buttonMain, buttonNowyPoziom;
	
	Label liczbaMiodu;
	Label liczbaMonet;

	public WarstwaPodsumowania(BumbleAndBee maingame)
	{		
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		// przygotuj style dla aktorow
		Skin skin1 = new Skin();
		skin1.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin1.add("maly", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI14, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin1.add("mniejszy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI15, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		skin1.add("duzy", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI72, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		
		
		//Skin skin = new Skin(Gdx.files.internal("skin/window.json"));
		//Window window = new Window("DOLECIALES / ZGINALES", skin);
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Skin windowskin = new Skin(Gdx.files.internal("skin/window.json"));
		
		buttonNowyPoziom = new TextButton("Nowy poziom", skin);
		buttonMain = new TextButton("Ekran glowny", skin);
		
		Window window = new Window("DOLECIALES!!", windowskin);
		
		window.defaults().prefWidth(200).spaceBottom(10);
		window.row().fill().expandX();
		
		Image miodImage = new Image(new TextureRegion(PrzechowalniaAssets.miod));
		Image monetaImage = new Image(new TextureRegion(PrzechowalniaAssets.moneta));
		
		liczbaMiodu = new Label(pobierzIloscMiodu()+"", skin1);
		liczbaMonet = new Label(pobierzIloscMonet()+"", skin1);
		//liczbaMiodu.setX(MIOD_X + 40);
		//liczbaMiodu.setY(MIOD_Y - 5);
		window.add(miodImage).maxSize(32, 32).center();
		window.add(liczbaMiodu);
		window.row();
		
		window.add(monetaImage).maxSize(32, 32).center();
		window.add(liczbaMonet);
		window.row();
		
		window.add(buttonNowyPoziom).padTop(10).expand().colspan(2).center();
		window.row();
		window.add(buttonMain).expand().colspan(2).center();
		window.row();
		window.pack();
		
		window.setX((SkalowalnyEkran.BASE_WIDTH-window.getWidth())/2);
		window.setY((SkalowalnyEkran.BASE_HEIGHT-window.getHeight())/2);
		this.addActor(window);
		
		buttonMain.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				gra.pokazMenu();
			}
		});	
		
		buttonNowyPoziom.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				gra.zacznijNastepnyPoziom();
			}
		});			
		
		//window.setBounds((SkalowalnyEkran.BASE_WIDTH-WIDTH)/2,
		                 //(SkalowalnyEkran.BASE_HEIGHT-HEIGHT)/2,
		                 //WIDTH, HEIGHT);		
		//addActor(window);	
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.podsumowanie;
	}
	private int pobierzIloscMiodu()
	{
		return gra.ileMiodu;
	}	
	private int pobierzIloscMonet()
	{
		return gra.ileMonet;
	}	
	public void act(float delta)
	{
		liczbaMiodu.setText(pobierzIloscMiodu()+"");
		liczbaMonet.setText(pobierzIloscMiodu()+"");
	}
}
