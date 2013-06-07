package przeciwnicy;

import screens.Warstwa;
import zdazenia.Punktuj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import modele.AnimowanyObiekt;
import modele.Pszczola;

public class Grubas extends AnimowanyObiekt {

	Pszczola pszczola;
	boolean usuwany = false;
	// co 10 przejscie act sprawdzam pozycje pszczoly
	int licznikOdtworzen = 0; 
	float przemieszczenieY = 0;
	
	public Grubas(Pszczola sledzonaPszczola) 
	{
		super(null);
		pszczola = sledzonaPszczola;
		obrazek = new TextureRegion(new Texture(Gdx.files.internal("data/trzmiel2.png")));
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		if (!usuwany)
		{
			setX(getX() -  delta*100);
			
			licznikOdtworzen++;
			if (licznikOdtworzen>=10)
			{
				float y = getY();
				float pszczolay = pszczola.getY();
				if (y>pszczolay)
					przemieszczenieY =  -delta*20;
				else if (y<pszczolay)
					przemieszczenieY =  delta*20;
				
				licznikOdtworzen = 0;
			}
			
			setY(getY() +  przemieszczenieY);
		}
	}
	
	
	@Override
	public void animujNaUsuwanie(int finalDoX, int finalDoYtop, Action implement)
	{	
		usuwany = true;
		obrazek = new TextureRegion(new Texture(Gdx.files.internal("data/bang.png")));
		
		ScaleToAction action1 =  new ScaleToAction();	
		action1.setDuration(2f);
		action1.setScale(0.1f);
				 
		Action decrement =  new Action() {
			  public boolean act( float delta ) {

				   ((Warstwa)this.getActor().getStage()).powiadom(Punktuj.zycie);
				   return true; // returning true consumes the event
				  }
				 } ;
				 
		// ta akcja musi byc ostatnia bo usuwa aktora na dobre
		Action actionFinal = new Action() {
			  public boolean act( float delta ) {				   				
				   // remove actor on end
				   remove();
				   return true; // returning true consumes the event
				  }
				 } ;
				 
		SequenceAction sekwencja = new SequenceAction();
		sekwencja.addAction(decrement);
		sekwencja.addAction(action1);		
		sekwencja.addAction(actionFinal);		
		
		this.addAction(sekwencja);		
	}
}
