package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaPauzy extends Warstwa {
		
	public Button buttonSklep, buttonMain, buttonWznow;
	
	public WarstwaPauzy(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		buttonSklep = new TextButton("Sklep", skin);
		buttonMain = new TextButton("Ekran glowny", skin);
		buttonWznow = new TextButton("Wznow", skin);
		
		Window window = new Window("Pauza", skin);
		
		//window.padBottom(10);
		window.defaults().prefWidth(200).spaceBottom(10);
		window.row().fill().expandX();
		
		window.add(buttonSklep).padTop(10).expand();
		window.row();
		window.add(buttonMain).expand();
		window.row();
		window.add(buttonWznow).padBottom(10).expand();	
		window.row();
		window.pack();
		
		window.setX((SkalowalnyEkran.BASE_WIDTH-window.getWidth())/2);
		window.setY((SkalowalnyEkran.BASE_HEIGHT-window.getHeight())/2);
		this.addActor(window);
		
		buttonSklep.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				((Level)gra.getScreen()).dyrektor.ustawAktywnaWarstwe(TypWarstwy.sklep);
			}
		});	
		
		buttonMain.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				gra.setScreen(new MainMenu(gra));
			}
		});	
		
		buttonWznow.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				((Level)gra.getScreen()).dyrektor.ustawAktywnaWarstwe(TypWarstwy.statystyki);
			}
		});			
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.pauza;
	}	
}
