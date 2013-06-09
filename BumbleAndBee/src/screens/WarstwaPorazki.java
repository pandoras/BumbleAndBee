package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaPorazki extends Warstwa {

	static final int WIDTH = 400;
	static final int HEIGHT = 300;
	
	public Button buttonNowaGra;

	public WarstwaPorazki(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		Skin windowskin = new Skin(Gdx.files.internal("skin/window.json"));
		
		Window window = new Window("ZGINALES!!!", windowskin);
		buttonNowaGra = new TextButton("Nowa Gra", skin);
		window.defaults().prefWidth(200).spaceBottom(10);
		window.row().fill().expandX();
		
		window.add(buttonNowaGra).padBottom(10).expand();	
		window.row();
		window.pack();
		
		window.setBounds((SkalowalnyEkran.BASE_WIDTH-WIDTH)/2,
		                 (SkalowalnyEkran.BASE_HEIGHT-HEIGHT)/2,
		                 WIDTH, HEIGHT);		
		addActor(window);	
		
		buttonNowaGra.addListener(new ClickListener() 
		{
			public void clicked (InputEvent event, float x, float y) 
			{
				event.stop();
				gra.zacznijLevel();
				((Level)gra.getScreen()).dyrektor.ustawAktywnaWarstwe(TypWarstwy.statystyki);
			}
		});
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.podsumowanie;
	}	
}
