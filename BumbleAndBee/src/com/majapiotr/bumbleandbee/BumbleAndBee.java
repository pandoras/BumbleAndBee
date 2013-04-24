package com.majapiotr.bumbleandbee;

import inne.PrzechowalniaAssets;
import screens.MainMenu;

import com.badlogic.gdx.Game;

public class BumbleAndBee extends Game {	
	@Override
	public void create() {		
		PrzechowalniaAssets.load();
		setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
