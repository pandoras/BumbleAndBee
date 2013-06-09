package przeciwnicy;

import screens.Warstwa;
import zdazenia.Punktuj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

import modele.AnimowanyObiekt;

public class Trzmiel extends AnimowanyObiekt {

	boolean usuwany = false;
	
	public Trzmiel() 
	{
		super(null);
		obrazek = new TextureRegion(new Texture(Gdx.files.internal("data/trzmiel.png")));
	}
	
	@Override
	public void act(float delta)
	{
		// nasz podstawowy trzmiel nie robi nic specjalnego
		super.act(delta);
		if (!usuwany)
		{
			setX(getX() - delta*100);
		}
	}
	
	@Override
	public void animujNaUsuwanie(int finalDoX, int finalDoYtop, Action implement)
	{	
		animuj(true);
	}	
	
	@Override
	public void animuj(boolean zmniejszZycie)
	{
		usuwany = true;
		obrazek = new TextureRegion(new Texture(Gdx.files.internal("data/bang.png")));
		
		ScaleToAction action1 =  new ScaleToAction();	
		action1.setDuration(2f);
		action1.setScale(0.1f);			 
				 
		// ta akcja musi byc ostatnia bo usuwa aktora na dobre
		Action actionFinal = new Action() {
			  public boolean act( float delta ) {				   				
				   // remove actor on end
				   remove();
				   return true; // returning true consumes the event
				  }
				 } ;
			
		// sekwencja wszystkich akcji
		SequenceAction sekwencja = new SequenceAction();
		
		if (zmniejszZycie)
		{
			Action decrement =  new Action() {
				  public boolean act( float delta ) {
	
					   ((Warstwa)this.getActor().getStage()).powiadom(Punktuj.zycie);
					   return true; // returning true consumes the event
					  }
					 } ;		
			sekwencja.addAction(decrement);
		}
		
		sekwencja.addAction(action1);		
		sekwencja.addAction(actionFinal);		
		
		this.addAction(sekwencja);		
	}
}
