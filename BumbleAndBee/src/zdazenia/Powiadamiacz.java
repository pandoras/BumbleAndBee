package zdazenia;

import screens.WarstwaStatystyk;

import com.badlogic.gdx.utils.Array;

// ta klasa jednoszesnie slucha jak i przekazuje dalej do innych listenerow
public class Powiadamiacz  {

	// kto slucha zmian punktacji
	private Array<RejestratorPunktacji> sluchaczePunktacji;


	public Powiadamiacz () {
		sluchaczePunktacji = new Array<RejestratorPunktacji>();
	}

	public void dodajSluchaczaPunktacji (RejestratorPunktacji listener) {
		sluchaczePunktacji.add(listener);

	}

	public void powiadom (Punktuj rodzaj) {
		for (RejestratorPunktacji listener : sluchaczePunktacji) {
			listener.naZmianePunktacji(rodzaj);
		}
	}

	public void usunSluchaczaPunktacji(WarstwaStatystyk statystyki) {
		sluchaczePunktacji.removeValue(statystyki, true);

	}

}
