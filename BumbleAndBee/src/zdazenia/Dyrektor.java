package zdazenia;

import screens.Level;
import screens.TypWarstwy;
import screens.Warstwa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Dyrektor implements InputProcessor  {

	Level poziom;
	public TypWarstwy aktywnaWarstwa;
	Actor aktorStrzalu = null;
	
	public Dyrektor(Level obecnyPoziom)
	{
		poziom = obecnyPoziom;
	}
	
	public void ustawAktywnaWarstwe(Warstwa warstwa)
	{
		aktywnaWarstwa = warstwa.pobierzTyp();
		Gdx.input.setInputProcessor(new InputMultiplexer(this, warstwa));
	}
	
	public void ustawAktywnaWarstwe(TypWarstwy typ)
	{
		aktywnaWarstwa = typ;
		switch (aktywnaWarstwa)
		{
			case pauza:
				Gdx.input.setInputProcessor(new InputMultiplexer(poziom.warstwaPauzy, this));
				break;
			case podsumowanie:
				Gdx.input.setInputProcessor(new InputMultiplexer(poziom.warstwaPodsumowania, this));
				break;
			case sklep:
				Gdx.input.setInputProcessor(new InputMultiplexer(poziom.warstwaSklepu, this));
				break;
			case tlo:
				Gdx.input.setInputProcessor(new InputMultiplexer(poziom.przesuwaneT³o, this));
				break;
			case statystyki:
				Gdx.input.setInputProcessor(new InputMultiplexer(poziom.statystyki, this));
				break;				
		}
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		if (aktywnaWarstwa == TypWarstwy.tlo || aktywnaWarstwa == TypWarstwy.statystyki)
		{
			Vector3 touchPos = new Vector3(screenX, screenY, 0);
			poziom.statystyki.getCamera().unproject(touchPos);
			if (aktorStrzalu!=null)
			{
				Vector2 actorPos = new Vector2(touchPos.x, touchPos.y);
				actorPos = aktorStrzalu.stageToLocalCoordinates(actorPos);
				if (aktorStrzalu.hit(actorPos.x, actorPos.y,true)!=null)
					return false;
			}
			poziom.statystyki.pszczola.przemiescWStrone(touchPos.x, touchPos.y);
		}
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (aktywnaWarstwa == TypWarstwy.tlo || aktywnaWarstwa == TypWarstwy.statystyki)
		{		
			poziom.statystyki.pszczola.zatrzymajPrzemieszczenie();
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		if (aktywnaWarstwa == TypWarstwy.tlo || aktywnaWarstwa == TypWarstwy.statystyki)
		{
			if (poziom.statystyki.pszczola.trwaPrzemieszczenie())
			{
				Vector3 touchPos = new Vector3(screenX, screenY, 0);
				poziom.statystyki.getCamera().unproject(touchPos);
				if (aktorStrzalu!=null)
				{
					Vector2 actorPos = new Vector2(touchPos.x, touchPos.y);
					actorPos = aktorStrzalu.stageToLocalCoordinates(actorPos);
					if (aktorStrzalu.hit(actorPos.x, actorPos.y,true)!=null)
						return false;
				}
				poziom.statystyki.pszczola.przemiescWStrone(touchPos.x, touchPos.y);	
			}
		}
		return false;
	}	


	@Override
	public boolean keyDown(int keycode) {

		if (aktywnaWarstwa == TypWarstwy.tlo || aktywnaWarstwa == TypWarstwy.statystyki)
		{
			if (keycode == Keys.ESCAPE)
			{
				ustawAktywnaWarstwe(TypWarstwy.pauza);
				return true;
			}
		}
		else if (aktywnaWarstwa == TypWarstwy.pauza)
		{			
			if (keycode == Keys.ESCAPE)
			{
				ustawAktywnaWarstwe(TypWarstwy.statystyki);
				return true;
			}
		}	
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void ustawAktoraStrzalu(Actor przycisk) {
		// TODO Auto-generated method stub
		aktorStrzalu = przycisk;
	}

}
