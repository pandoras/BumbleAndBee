package com.majapiotr.bumbleandbee;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Bumble & Bee";
		cfg.useGL20 = true;
		cfg.width = 1200;
		cfg.height = 700;
		
		new LwjglApplication(new BumbleAndBee(new PustyPrzycisk()), cfg);
	}
}
