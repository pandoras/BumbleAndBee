package screens;

import java.util.ArrayList;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class MainMenu extends SkalowalnyEkran {
	
	private static final int BUTTON_WIDTH = 395;
	private static final int BUTTON_HEIGHT = 77;
	
	
	// InputProcessor, wype³nia ca³y ekran
	private Stage estrada;
	Window window;
	Image closeImage;
	
	Table tablicaPrzyciskow;
	ArrayList<Image> images = new ArrayList<Image>();
	
	NarzedziaBitmapy rysujBitmape = new NarzedziaBitmapy();
	
	public MainMenu(final BumbleAndBee game) 	{
		super(game);

		estrada = new Stage(0, 0, false);
		// nasza estrada przetwarza wydarzenia wejœcia i rozdziela na aktorów
		Gdx.input.setInputProcessor(estrada);

		// ³adujemy t³o
		Image image = new Image(new TextureRegion(PrzechowalniaAssets.textureMenu));		
		image.setScaling(Scaling.fill);
		image.setBounds(0, 0, BASE_WIDTH, BASE_HEIGHT);
		image.setAlign(Align.center);
		estrada.addActor(image);
		images.add(image);
		
		tablicaPrzyciskow = new Table();	
		// rysuj linie tablicy dla debuga
		//tablicaPrzyciskow.debug();
		
		// nie mozna uzywac tablicaPrzyciskow.setFillParent(true); bo 
		//   potem dziwnie sie zachowuje przy zmianie wielkosci ekranu
		
		estrada.addActor(tablicaPrzyciskow);	
		tablicaPrzyciskow.setSize(BUTTON_WIDTH, 3*BUTTON_HEIGHT);
		
		for (int i = 0; i < PrzechowalniaAssets.teksturyPrzyciskow.size(); i++)
		{
			ImageButtonStyle stylStartu = new ImageButtonStyle();
			stylStartu.imageUp = new TextureRegionDrawable(new TextureRegion(PrzechowalniaAssets.teksturyPrzyciskow.get(i)));
			// tu mozna jeszcze inne style dodac, np stylStartu.imageDown
			ImageButton przycisk = new ImageButton(stylStartu);			
			switch (i)
			{
				case PrzechowalniaAssets.przyciskStart:
					przycisk.addListener(new ClickListener(){
						public void clicked(InputEvent event, float x, float y)
						{
							event.stop();
							game.zacznijGre();
						}});					
					break;
				case PrzechowalniaAssets.przyciskWyjscie:
					przycisk.addListener(new ClickListener(){
						public void clicked(InputEvent event, float x, float y)
						{
							event.stop();
							Gdx.app.exit();
						}});					
					break;
				case PrzechowalniaAssets.przyciskOpcje:
					przycisk.addListener(new ClickListener(){
						public void clicked(InputEvent event, float x, float y)
						{
							event.stop();
							((MainMenu)game.getScreen()).pokazOpcje();
						}});					
					break;					
				default:
					break;
			}
			
			tablicaPrzyciskow.add(przycisk);

			tablicaPrzyciskow.row();			
		}
		
		tablicaPrzyciskow.pack();
		tablicaPrzyciskow.setX(BASE_WIDTH-BUTTON_WIDTH-46);
		tablicaPrzyciskow.setY(BASE_HEIGHT-3*BUTTON_HEIGHT-115);		
	}
	

	protected void pokazOpcje() {
		OknoOpcji opcje = new OknoOpcji(gra, estrada);
		//estrada.addActor(opcje);

	}


	@Override
	public void render(float delta) {
		super.render(delta);
		
		estrada.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		estrada.draw();
		// rysuj linie tablicy dla debuga
		Table.drawDebug(estrada);		
	}

	@Override
	public void resize(int width, int height) {
		
		//super.resize(width, height);
		
		estrada.setViewport(BASE_WIDTH, BASE_HEIGHT, true);
		estrada.getCamera().translate(-estrada.getGutterWidth(), -estrada.getGutterHeight(), 0);

	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		// zwolnij obiekt estrady gdy juz nie uzywamy tej sceny
		estrada.dispose();
	}

}