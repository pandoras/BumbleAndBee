package com.majapiotr.bumbleandbee;

import modele.Pszczola;

import com.badlogic.gdx.scenes.scene2d.Actor;

// interfejs do przycisku strza�u potrzebny w androidzie
public interface IPrzycisk {

	Actor stworzAktoraStrzalu(Pszczola pszczola);
}
