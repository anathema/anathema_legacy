package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.library.trait.IModifiableTrait;

public interface IBeastformAttribute {

  public int getPointCost();

  public IModifiableTrait getTrait();

  public void recalculate();

  public int getAdditionalDots();

}
