package modele;

import screens.SkalowalnyEkran;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inne.NarzedziaBitmapy;
import inne.PrzechowalniaAssets;

public class Zadlo {
	public int ekranowyx = -1;
	public int y = -1;
	
	public Zadlo() {
		ekranowyx = -1;
		y = -1;
	}
	
	public void przesun()
	{
		ekranowyx += 5;
	}
	
	public boolean pozaEkranem()
	{
		if(ekranowyx<0 || ekranowyx>SkalowalnyEkran.BASE_WIDTH)
			return true;
		return false;
	}
	
	public void draw(SpriteBatch batch)
	{	
		PrzechowalniaAssets.spriteZadlo.setPosition(this.ekranowyx, y);
		PrzechowalniaAssets.spriteZadlo.draw(batch);
	}
	
	public void wysun(Pszczola pszczola)
	{
		ekranowyx = (int)pszczola.ekranowyx+80;
		y = (int)pszczola.getY()+20;	
	}
}
