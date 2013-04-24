package modele;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Pszczola {

	public float x = 100;
	public float y = 100;
	private int pixeliNaSekunde = 100;
	Zadlo zadlo;
	
	public Pszczola() {
		zadlo = new Zadlo();
	}

	public void NarysujMnie() {

		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && x!=0) {
			this.x -= Gdx.graphics.getDeltaTime()*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && x!=1120) {
			this.x += Gdx.graphics.getDeltaTime()*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) && y!=570) {
			this.y += Gdx.graphics.getDeltaTime()*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) && y!=50) {
			this.y -= Gdx.graphics.getDeltaTime()*this.pixeliNaSekunde;
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) && zadlo.pozaEkranem()){
			zadlo.wysun(this);
		}
		if (!zadlo.pozaEkranem()){
			zadlo.wyswietl();
			zadlo.przesun();
			if ( zadlo.pozaEkranem()){
				zadlo.x = -1;
			}
		}

		NarzedziaBitmapy.wyswietlBitmape(PrzechowalniaAssets.spritePszczola, (int)this.x, (int)this.y);

	}

}
