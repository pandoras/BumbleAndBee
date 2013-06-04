package zdazenia;

import com.badlogic.gdx.utils.Array;

// ta klasa jednoszesnie slucha jak i przekazuje dalej do innych listenerow
public class Powiadamiacz {

	private final Array<RejestratorPunktacji> listeners;

	public Powiadamiacz () {
		listeners = new Array<RejestratorPunktacji>();
	}

	public void addListener (RejestratorPunktacji listener) {
		listeners.add(listener);
	}

	public void powiadom (Punktuj rodzaj) {
		for (RejestratorPunktacji listener : listeners) {
			listener.naZmianePunktacji(rodzaj);
		}
	}
	
	public void update (float delta) {

	}	
}
