package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaPodsumowania extends Warstwa {

	static final int WIDTH = 400;
	static final int HEIGHT = 300;

	public WarstwaPodsumowania(BumbleAndBee maingame)
	{
		super(SkalowalnyEkran.BASE_WIDTH,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		
		Skin skin = new Skin(Gdx.files.internal("skin/window.json"));
		Window window = new Window("DOLECIALES / ZGINALES", skin);
		window.defaults().prefWidth(200).spaceBottom(10);
		window.row().fill().expandX();
		
		window.setBounds((SkalowalnyEkran.BASE_WIDTH-WIDTH)/2,
		                 (SkalowalnyEkran.BASE_HEIGHT-HEIGHT)/2,
		                 WIDTH, HEIGHT);		
		addActor(window);	
	}
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.podsumowanie;
	}	
}
