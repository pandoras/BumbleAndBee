package inne;

import screens.Level;
import screens.MainMenu;
import screens.TypWarstwy;
import screens.Warstwa;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class Dyrektor implements InputProcessor {

	Level poziom;
	public TypWarstwy aktywnaWarstwa;
	
	public Dyrektor(Level obecnyPoziom)
	{
		poziom = obecnyPoziom;
	}
	
	public void ustawAktywnaWarstwe(Warstwa warstwa)
	{
		aktywnaWarstwa = warstwa.pobierzTyp();
	}
	
	public void ustawAktywnaWarstwe(TypWarstwy typ)
	{
		aktywnaWarstwa = typ;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		if (aktywnaWarstwa == TypWarstwy.tlo || aktywnaWarstwa == TypWarstwy.statystyki)
		{
			if (poziom.pszczola.klawisze.contains(keycode))
			{
				poziom.pszczola.obsluzKlawisz(Type.keyDown, keycode);
				return true;
			}	
		
			if (keycode== Keys.ESCAPE)
			{
				ustawAktywnaWarstwe(TypWarstwy.pauza);
				//poziom.gra.pokazMenu();
			}
			return false;
		}
		if (aktywnaWarstwa == TypWarstwy.pauza)
		{
			if (keycode== Keys.ESCAPE)
				ustawAktywnaWarstwe(TypWarstwy.tlo);	
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		// puszczenie klawisza na pszczolce obslugujemy na wszystkich warstwach
		if (poziom.pszczola.klawisze.contains(keycode))
		{
			poziom.pszczola.obsluzKlawisz(Type.keyUp, keycode);
			return true;
		}	
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (aktywnaWarstwa == TypWarstwy.pauza)
		{
			if (poziom.warstwaPauzy.buttonMain.hit(screenX, screenY, true)!=null)
				poziom.gra.pokazMenu();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
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

}
