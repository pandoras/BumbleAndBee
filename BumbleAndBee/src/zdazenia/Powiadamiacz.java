package zdazenia;

import java.util.HashSet;

import nieuzywane.ISluchaczKlawiszy;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Input.Keys;
/*
KeyListener 
@Override
public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 68) { //it's the 'D' key
        //Move your mario
    }
}
*/
// ta klasa jednoszesnie slucha jak i przekazuje dalej do innych listenerow
public class Powiadamiacz implements InputProcessor {

	// kto slucha zmian punktacji
	private Array<RejestratorPunktacji> sluchaczePunktacji;
	private Array<ISluchaczKlawiszy> sluchaczeKlawiszy;
	
	private HashSet<Integer> sluchaneKlawisze;

	public Powiadamiacz () {
		sluchaczePunktacji = new Array<RejestratorPunktacji>();
		sluchaczeKlawiszy = new Array<ISluchaczKlawiszy>();
		sluchaneKlawisze = new HashSet<Integer>();
	}

	public void dodajSluchaczaPunktacji (RejestratorPunktacji listener) {
		sluchaczePunktacji.add(listener);
	}
	
	public void dodajSluchaczaKlawiszy (ISluchaczKlawiszy listener) {
		sluchaneKlawisze.addAll(listener.czegoSlucham());
		sluchaczeKlawiszy.add(listener);
	}	

	public void powiadom (Punktuj rodzaj) {
		for (RejestratorPunktacji listener : sluchaczePunktacji) {
			listener.naZmianePunktacji(rodzaj);
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (sluchaneKlawisze.contains(keycode))
			for (ISluchaczKlawiszy listener : sluchaczeKlawiszy) {
				listener.zareagujNaKlawisz(keycode);
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
