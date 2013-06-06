package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaSklepu extends Warstwa {
		
	public WarstwaSklepu(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Window window = new Window("Sklep", skin);
		window.setX((SkalowalnyEkran.BASE_WIDTH-window.getWidth())/2);
		window.setY((SkalowalnyEkran.BASE_HEIGHT-window.getHeight())/2);
		this.addActor(window);
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.sklep;
	}	
}
