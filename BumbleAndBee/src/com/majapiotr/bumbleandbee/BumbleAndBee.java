package com.majapiotr.bumbleandbee;

import inne.PrzechowalniaAssets;
import inne.Tester;
import screens.*;
import zdazenia.Powiadamiacz;

import com.badlogic.gdx.Game;

public class BumbleAndBee extends Game {
	
	IPrzycisk przyciskStrzalu;
	public Powiadamiacz powiadamiacz;
	
	public BumbleAndBee(IPrzycisk przycisk)
	{
		super();
		
		przyciskStrzalu = przycisk;
		powiadamiacz = new Powiadamiacz();			
	}
	
	@Override
	public void create() {		
		this.setScreen(new SplashScreen(this));
		
		PrzechowalniaAssets.load();
		Tester.init();
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
