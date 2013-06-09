package screens;

import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class Sklep extends SkalowalnyEkran {

	private static final int CLOSE_X = 1128;
	private static final int CLOSE_Y = 637;
	
	// InputProcessor, wype³nia ca³y ekran
	private Stage estrada;
	
	public Sklep(final BumbleAndBee game) {
		super(game);
		
		estrada = new Stage(0, 0, false);
		// nasza estrada przetwarza wydarzenia wejœcia i rozdziela na aktorów
		Gdx.input.setInputProcessor(estrada);
		
		// ³adujemy t³o
		Image image = new Image(new TextureRegion(PrzechowalniaAssets.textureSklep));		
		image.setScaling(Scaling.fill);
		image.setBounds(0, 0, BASE_WIDTH, BASE_HEIGHT);
		image.setAlign(Align.center);
		estrada.addActor(image);
	}
	
	@Override
	public void render(float delta) 
	{
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
	public void dispose () {
		//world.dispose();
	}
	
}
