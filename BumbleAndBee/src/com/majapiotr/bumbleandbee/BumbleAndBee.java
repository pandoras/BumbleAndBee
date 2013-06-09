package com.majapiotr.bumbleandbee;

import modele.Pszczola;
import inne.PrzechowalniaAssets;
import inne.Tester;
import screens.*;
import zdazenia.Dyrektor;
import zdazenia.Powiadamiacz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BumbleAndBee extends Game {
	
	public IPrzycisk przyciskStrzalu;
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
		Screen s = getScreen();
		if (s instanceof Level)
			((Level)s).zacznijNowy();
		else
			setScreen(new Level(this));
	}
	
	public void pokazMenu()
	{
		setScreen(new MainMenu(this));
	}
	
	public void dodajAktoraStrzalu(Warstwa warstwa, Pszczola pszczola, Dyrektor dyrektor)
	{
		Actor przycisk = przyciskStrzalu.stworzAktoraStrzalu(pszczola);
		if (przycisk!=null)
		{
			warstwa.addActor(przycisk);
			dyrektor.ustawAktoraStrzalu(przycisk);
		}
	}
}
