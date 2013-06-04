package modele;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public interface IObiekt {

	// granice w koordynatach levelu
	float[] pobierzGranice();
	// granice w koordynatach ekranu (do rysowania na batch)
	float[] pobierzGraniceEkranowe();
	
	// prostokąt graniczny w koordynatach levelu
	Rectangle pobierzProstokąt();
	
}
