package nieuzywane;

import java.util.HashSet;

public interface ISluchaczKlawiszy {

	void zareagujNaKlawisz(int keycode);
	
	HashSet<Integer> czegoSlucham();
}
