package com.majapiotr.bumbleandbee;

import inne.PrzechowalniaAssets;
import screens.Level;
import screens.MainMenu;

import com.badlogic.gdx.Game;

public class BumbleAndBee extends Game {
	
	IPrzycisk przyciskStrzalu;
	
	public BumbleAndBee(IPrzycisk przycisk)
	{
		super();
		przyciskStrzalu = przycisk;
	}
	
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
	
	public void zacznijLevel()
	{
		setScreen(new Level(this, przyciskStrzalu));
	}
	
	public void pokazMenu()
	{
		setScreen(new MainMenu(this));
	}
}
