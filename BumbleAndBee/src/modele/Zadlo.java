package modele;

import screens.SkalowalnyEkran;
import inne.PrzechowalniaAssets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Zadlo extends AnimowanyObiekt {
	
	public float przesunieciex = -1;
	Pszczola pszczola;
	
	public Zadlo(Pszczola pszczolaWlascicielka) {
		super(PrzechowalniaAssets.zadlo);
		// na razie zadlo niewidoczne
		pszczola = pszczolaWlascicielka;
		this.schowaj();
	}
	
	public void przesun(float poczatekEkranu, float deltaTime)
	{
		przesunieciex += 5f;
		this.setX(pszczola.getX()+przesunieciex);
	}
	
	public boolean pozaEkranem()
	{
		if(przesunieciex<0 || (pszczola.ekranowyx + przesunieciex)>SkalowalnyEkran.BASE_WIDTH)
			return true;
		return false;
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(obrazek, pszczola.ekranowyx + przesunieciex, getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
			getRotation());
	}
	
	@Override
	public Rectangle pobierzProstok¹t()
	{
		return new Rectangle(this.getX(),this.getY(), PrzechowalniaAssets.zadlo.getRegionWidth(), PrzechowalniaAssets.zadlo.getRegionHeight());
	}
	
	public void wysun()
	{
		przesunieciex = 80f;
		//this.setBounds((int)pszczola.ekranowyx+80,(int)pszczola.getY()+20,obrazek.getRegionWidth(),obrazek.getRegionHeight());	
		this.setBounds((int)pszczola.getX()+przesunieciex,(int)pszczola.getY()+20,obrazek.getRegionWidth(),obrazek.getRegionHeight());	
	}
	
	public void schowaj()
	{
		przesunieciex = -1;
		this.setBounds(0,0,0,0);
	}

}
