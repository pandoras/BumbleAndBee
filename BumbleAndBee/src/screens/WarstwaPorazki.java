package screens;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaPorazki extends Warstwa {

	//static final int WIDTH = 400;
	//static final int HEIGHT = 300;
	
	public Button buttonNowaGra, buttonMain;
	Label liczbaMiodu;
	Label liczbaMonet;

	public WarstwaPorazki(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		// przygotuj style dla aktorow
		Skin skin1 = new Skin();
		skin1.add("default", new Label.LabelStyle(PrzechowalniaAssets.fontSegoeUI_Light32, new Color(1f, 1f, 1f, 1f)), Label.LabelStyle.class);
		
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		Skin windowskin = new Skin(Gdx.files.internal("skin/window.json"));
		
		Window window = new Window("ZGINALES!!!", windowskin);
		buttonNowaGra = new TextButton("Nowa Gra", skin);
		buttonMain = new TextButton("Ekran glowny", skin);
		
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
		
		window.add(buttonNowaGra).padTop(10).expand().colspan(2).center();
		//buttonNowaGra.setX(30);
		window.row();
		window.pack();
		
		window.add(buttonMain).expand().colspan(2).center();
		//buttonNowaGra.setX(30);
		window.row();
		window.pack();
		
		//window.setBounds((SkalowalnyEkran.BASE_WIDTH-WIDTH)/2,
		//                (SkalowalnyEkran.BASE_HEIGHT-HEIGHT)/2,
		//                 WIDTH, HEIGHT);	
		window.setX((SkalowalnyEkran.BASE_WIDTH-window.getWidth())/2);
		window.setY((SkalowalnyEkran.BASE_HEIGHT-window.getHeight())/2);
		this.addActor(window);	
		
		buttonNowaGra.addListener(new ClickListener() 
		{
			public void clicked (InputEvent event, float x, float y) 
			{
				event.stop();
				
				gra.zacznijGre();
				//((Level)gra.getScreen()).dyrektor.ustawAktywnaWarstwe(TypWarstwy.statystyki);
			}
		});
		buttonMain.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				gra.pokazMenu();
			}
		});	
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
		liczbaMonet.setText(pobierzIloscMonet()+"");
	}

}
