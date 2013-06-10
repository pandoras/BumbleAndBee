package com.majapiotr.bumbleandbee;

import modele.Pszczola;
import inne.PrzechowalniaAssets;
import inne.Tester;
import screens.*;
import zdazenia.Dyrektor;
import zdazenia.Powiadamiacz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BumbleAndBee extends Game {
	
	public IPrzycisk przyciskStrzalu;
	public Powiadamiacz powiadamiacz;
	
	public int ileMonet = 0;
	public int ileMiodu = 0;	
	public int nrPoziomu = 1;
	public int liczbaSerduszek = 4;
	
	public BumbleAndBee(IPrzycisk przycisk)
	{
		super();
		
		przyciskStrzalu = przycisk;
		powiadamiacz = new Powiadamiacz();			
	}
	
	@Override
	public void create() {		
		this.setScreen(new SplashScreen(this, false));
		
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
	
	public void zacznijGre()
	{
		ileMonet = 0;
		ileMiodu = 0;	
		nrPoziomu = 1;
		liczbaSerduszek = 4;

		this.getScreen().dispose();
		this.setScreen(new SplashScreen(this, true));
	}
	
	
	public void zacznijNastepnyPoziom()
	{
		nrPoziomu++;

		this.getScreen().dispose();
		this.setScreen(new SplashScreen(this, true));
	}
	
	public void pokazMenu()
	{
		this.getScreen().dispose();
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
