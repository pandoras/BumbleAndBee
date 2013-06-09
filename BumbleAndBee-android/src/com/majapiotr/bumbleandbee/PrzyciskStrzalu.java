package com.majapiotr.bumbleandbee;

import screens.SkalowalnyEkran;
import inne.PrzechowalniaAssets;
import modele.Pszczola;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PrzyciskStrzalu implements IPrzycisk {

	Pszczola pszczola;
	
	@Override
	public Actor stworzAktoraStrzalu(Pszczola pszczolaNaEkranie) {
		// TODO Auto-generated method stub
		
		pszczola = pszczolaNaEkranie;
		
		ImageButtonStyle stylStartu = new ImageButtonStyle();
		stylStartu.imageUp = new TextureRegionDrawable(PrzechowalniaAssets.textureStrzel);
		// tu mozna jeszcze inne style dodac, np stylStartu.imageDown
		ImageButton przycisk = new ImageButton(stylStartu);
		
		przycisk.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y)
			{
				if (pszczola.zadlo.pozaEkranem())
					pszczola.zadlo.wysun();
					
			}});
		int strzalWidth = PrzechowalniaAssets.textureStrzel.getRegionWidth();
		int strzalHeight = PrzechowalniaAssets.textureStrzel.getRegionHeight();
		przycisk.setBounds(SkalowalnyEkran.BASE_WIDTH - strzalWidth - 5, 5, strzalWidth, strzalHeight);
		
		return przycisk;
	}

	
}
