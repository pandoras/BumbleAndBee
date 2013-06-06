package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaPodsumowania extends Warstwa {

	protected static final int BASE_WIDTH = 600;
	protected static final int BASE_HEIGHT = 600;
	
	public WarstwaPodsumowania(BumbleAndBee maingame)
	{
		super(BASE_WIDTH,  BASE_HEIGHT, maingame);
		
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Window window = new Window("Dolecia³eœ do ula!", skin);
		window.defaults().spaceBottom(10);
		window.row().fill().expandX();
		
		window.setX((SkalowalnyEkran.BASE_WIDTH-window.getWidth())/2);
		window.setY((SkalowalnyEkran.BASE_HEIGHT-window.getHeight())/2);		
		addActor(window);	
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.podsumowanie;
	}	
}
