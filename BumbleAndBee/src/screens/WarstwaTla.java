package screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zdazenia.Punktuj;
import inne.PrzechowalniaAssets;
import modele.KolekcjaObiektow;
import modele.Przeciwnicy;
import modele.Pszczola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.majapiotr.bumbleandbee.BumbleAndBee;

public class WarstwaTla extends Warstwa {

	/** monety i miodki **/
	public KolekcjaObiektow monety;	
	public KolekcjaObiektow miodki;
	public Przeciwnicy przeciwnicy;
	int szerokoscPoziomu;
	// jednolita tekstura t³a
	Texture teksturaT³a;
	
	List<Integer> pozycjeKwiatow = new ArrayList<Integer>();
	
	public WarstwaTla(int mnoznikDlugosci, BumbleAndBee maingame, Pszczola bee)
	{
		super(SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci,  SkalowalnyEkran.BASE_HEIGHT, maingame);
		szerokoscPoziomu = SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci;
		
		// zainicjuj teksturê t³a
		Pixmap p = new Pixmap(1, 1, Format.RGBA8888);
		p.setColor(new Color(0.49f, 0.80f, 0.90f, 1));
		p.fillRectangle(0, 0, 1, 1);	
	    teksturaT³a = new Texture(p, Format.RGB888, false);
	    for(int i = 0; i < 3; i++) {
    		Image closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureBgLvl));
			closeImage.setX(0 + 1200 * i);
			closeImage.setY(0);
			this.addActor(closeImage);
	    }
	    
	    // generowanie kwiatow
	    for(int i = 0; i < 25; i++) {
    		Random generatorPozycjiKwiata = new Random();
	    	int pozycjaX = generatorPozycjiKwiata.nextInt(4000);
	    	boolean czyBylKwiat = false;

	    	// sprawdzenie czy na tej pozycji byl juz kwiat
	    	for(int j = 0; j < pozycjeKwiatow.size(); j++)
	    		if(pozycjeKwiatow.get(j) - 100 < pozycjaX && pozycjeKwiatow.get(j) + 100 > pozycjaX) czyBylKwiat = true;
	    	
	    	if(!czyBylKwiat) {
	    		Image flowerImage = new Image(new TextureRegion(PrzechowalniaAssets.textureFlower1));
	    		Random generator = new Random();
		    	switch(generator.nextInt(3)) {
		    		case 0:
		    			flowerImage = new Image(new TextureRegion(PrzechowalniaAssets.textureFlower1));
		    			break;
		    		case 1:
		    			flowerImage = new Image(new TextureRegion(PrzechowalniaAssets.textureFlower2));
		    			break;
		    		case 2:
		    			flowerImage = new Image(new TextureRegion(PrzechowalniaAssets.textureFlower3));
		    			break;
		    		case 3:
		    			flowerImage = new Image(new TextureRegion(PrzechowalniaAssets.textureFlower4));
		    			break;
		    	}
		    	flowerImage.setX(pozycjaX);
				flowerImage.setY(0);
				Random generatorWielkosci = new Random();
				
				flowerImage.scale(generatorWielkosci.nextFloat() / 5);
				pozycjeKwiatow.add(pozycjaX);
				this.addActor(flowerImage);
	    	} else i--;
	    }
		
	    // losowanie trawy i jej wyswietlanie
	    for(int i = 0; i < 11; i++) {
    		Image closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureGrass1));
    		Random generator = new Random();
	    	switch(generator.nextInt(3)) {
	    		case 0:
	    			closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureGrass1));
	    			break;
	    		case 1:
	    			closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureGrass2));
	    			break;
	    		case 2:
	    			closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureGrass3));
	    			break;
	    		case 3:
	    			closeImage = new Image(new TextureRegion(PrzechowalniaAssets.textureGrass4));
	    			break;
	    	}
			closeImage.setX(0 + 357 * i);
			closeImage.setY(0);
			this.addActor(closeImage);
	    }
			
		// inicjujemy monety i miodki
		miodki = new KolekcjaObiektow( new float[] 
				{0.12626907f, 13.13198262f, 
				 6.06091530f, 20.58188262f, 
				 6.06091530f, 23.35978262f, 
				 9.59644920f, 25.25378262f, 
				 15.40482600f, 24.24368262f,
				 20.45558900f, 28.28428262f,
				 28.28427100f, 26.64278262f,
				 27.02158100f, 21.21318262f,
				 35.22907000f, 18.56158262f,
				 33.46130300f, 11.74298262f,
				 23.35977800f, 7.82868262f, 
				 21.46574200f, 1.01018262f, 
				 14.64721200f, 0f,
				 9.09137290f, 3.91438262f, 
				 3.66180300f, 2.52538262f, 
				 0.37880721f, 6.94478262f, 
				 2.14657420f, 9.84898262f}, 
				 "data/miod.png",WarstwaStatystyk.MIOD_X,WarstwaStatystyk.MIOD_Y_TOP, Punktuj.miod);
		miodki.stworz(100, 200, SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci, SkalowalnyEkran.BASE_HEIGHT, this);
		
		monety = new KolekcjaObiektow( null, "data/moneta.png",WarstwaStatystyk.MONETA_X,WarstwaStatystyk.MONETA_Y_TOP, Punktuj.monety);	
		monety.stworz(100, 200, SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci, SkalowalnyEkran.BASE_HEIGHT, this);	
		
		przeciwnicy = new Przeciwnicy(bee);
		przeciwnicy.stworz(800, 200, SkalowalnyEkran.BASE_WIDTH * mnoznikDlugosci, SkalowalnyEkran.BASE_HEIGHT, this);
		
		Image ul = new Image(PrzechowalniaAssets.ul);
		//obrazki[ilosc].setTransform(true);
		ul.setBounds(szerokoscPoziomu-PrzechowalniaAssets.ul.getRegionWidth(), (int)(SkalowalnyEkran.BASE_HEIGHT * 0.5), PrzechowalniaAssets.ul.getRegionWidth(), PrzechowalniaAssets.ul.getRegionHeight());
		this.addActor(ul);
	}
	
	@Override
	public void act(float delta)
	{	
		super.act(delta);
	}
	
	// nadpisujemy rysowanie bo chcemy rysowac t³o sami (moznaby tez dodac aktora ktory bedzie wielkim prostokatem ale...)
	@Override
	public void draw()
	{
		SpriteBatch batch = this.getSpriteBatch();
		batch.begin();
		batch.draw(teksturaT³a, 0, 0, szerokoscPoziomu, SkalowalnyEkran.BASE_HEIGHT) ;
		
		// ostatni ekran
		//batch.draw(Tester.debugTexture,  szerokoscPoziomu-SkalowalnyEkran.BASE_WIDTH, 0, SkalowalnyEkran.BASE_WIDTH, SkalowalnyEkran.BASE_HEIGHT) ;
		batch.end();
		
		super.draw();
	}	
	public void sprawdzKolizje(Pszczola pszczola)
	{
		miodki.sprawdzKolizje(pszczola);
		monety.sprawdzKolizje(pszczola);
		przeciwnicy.sprawdzKolizje(pszczola);
	}	
	
	@Override
	public TypWarstwy pobierzTyp()
	{
		return TypWarstwy.tlo;
	}		
}
