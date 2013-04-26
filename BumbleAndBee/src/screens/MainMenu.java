package screens;

import java.util.ArrayList;
import java.util.List;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.esotericsoftware.tablelayout.Cell;
import com.esotericsoftware.tablelayout.Value;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class MainMenu extends SkalowalnyEkran {
	
	// InputProcessor, wype³nia ca³y ekran
	private Stage estrada;
	
	Table tablicaPrzyciskow;
	ArrayList<Image> images = new ArrayList<Image>();
	
	NarzedziaBitmapy rysujBitmape = new NarzedziaBitmapy();
	
	public MainMenu(final BumbleAndBee game) 	{
		super(game);
		estrada = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		// nasza estrada przetwarza wydarzenia wejœcia i rozdziela na aktorów
		Gdx.input.setInputProcessor(estrada);

		// ³adujemy t³o
		Image image = new Image(new TextureRegion(PrzechowalniaAssets.textureMenu));		
		//image.setScaling(Scaling.fill);
		image.setBounds(0, 0, BASE_WIDTH, BASE_HEIGHT);
		image.setAlign(Align.center);
		estrada.addActor(image);
		images.add(image);
		
		tablicaPrzyciskow = new Table();	
		// rysuj linie tablicy dla debuga
		//tablicaPrzyciskow.debug();

		estrada.addActor(tablicaPrzyciskow);		
		
		for (int i = 0; i < PrzechowalniaAssets.teksturyPrzyciskow.size(); i++)
		{
			ImageButtonStyle stylStartu = new ImageButtonStyle();
			stylStartu.imageUp = new TextureRegionDrawable(new TextureRegion(PrzechowalniaAssets.teksturyPrzyciskow.get(i)));
			//stylStartu.imageDown =
			ImageButton przycisk = new ImageButton(stylStartu);
			// dodanie obslugi zdazenia
			switch (i)
			{
				case PrzechowalniaAssets.przyciskStart:
					przycisk.addListener(new ClickListener(){
						public void clicked(InputEvent event, float x, float y)
						{
							event.stop();
							game.zacznijLevel();
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
				default:
					break;
			}
			tablicaPrzyciskow.add(przycisk);
			przycisk.setTransform(true);
			tablicaPrzyciskow.row();			
		}
		

		tablicaPrzyciskow.right().top();//.padTop(Value.percentHeight((float) 0.15)).padRight(Value.percentWidth((float) 0.0385));
		tablicaPrzyciskow.pack();
		tablicaPrzyciskow.setOrigin(tablicaPrzyciskow.getWidth(),tablicaPrzyciskow.getHeight());
		
	}
	

	@Override
	public void render(float delta) {
		super.render(delta);
		
		estrada.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		estrada.draw();
		// rysuj linie tablicy dla debuga
		//Table.drawDebug(estrada);		
	}

	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);
			
		// ustawiamy wielkosc estrady, skalujac j¹ z zachowaniem stosunku wys/szer
		estrada.setViewport(rozdzielczosc.x, rozdzielczosc.y, true);
	
		// to potrzebne tylko dla przybadku, gdzy ekran nie pasuje do stosunku wys/szer
		// przesuwany trochê kamerê na estradzie ¿eby nie by³a w rogu
		// tylko mia³a równe czarne prostok¹ty u góry i na dole (b¹dŸ te¿ na lewo/prawo)  
		// Gutter to w³aœnie jeden z takich "wype³niaczy"
		estrada.getCamera().translate(-przyciecie.x, -przyciecie.y, 0);

		// skalujemy obrazki
		for (int i = 0; i < images.size(); i++) {
			Image img = (Image)images.get(i);
			img.setScale(skala);
		}		
		
		tablicaPrzyciskow.setTransform(true);
		//tablicaPrzyciskow.setOrigin(tablicaPrzyciskow.getPrefWidth() / 2, tablicaPrzyciskow.getPrefHeight() / 2);
		tablicaPrzyciskow.setScale(skala);
		
		// skalowanie tabeli i odsuniecia od gornego prawego rogu, bo sama sie nie skaluje dobrze
		float paddingx = 46*skala;
		float paddingy = 110*skala;
		tablicaPrzyciskow.setX( rozdzielczosc.x-tablicaPrzyciskow.getWidth()-paddingx);
		tablicaPrzyciskow.setY( rozdzielczosc.y-tablicaPrzyciskow.getHeight()-paddingy);
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